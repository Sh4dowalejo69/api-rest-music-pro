package cl.pro.music.rest.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.pro.music.rest.api.entity.CompradorEntity;
import cl.pro.music.rest.api.entity.DetalleVentaEntity;
import cl.pro.music.rest.api.entity.MonedaEntity;
import cl.pro.music.rest.api.entity.ProductoEntity;
import cl.pro.music.rest.api.entity.TransaccionVentaEntity;
import cl.pro.music.rest.api.entity.VentaEntity;
import cl.pro.music.rest.api.repository.ICompradorRepository;
import cl.pro.music.rest.api.repository.IDetalleVentaRepository;
import cl.pro.music.rest.api.repository.IMonedaRepository;
import cl.pro.music.rest.api.repository.IProductoRepository;
import cl.pro.music.rest.api.repository.ITransaccionVentaRepository;
import cl.pro.music.rest.api.repository.IVentaRepository;
import cl.pro.music.rest.api.service.IApiRestMusicProService;
import cl.pro.music.rest.api.utils.FuncionesUtiles;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.error.exceptions.MontoVentaInferior;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;
import cl.pro.music.rest.api.viewmodel.model.ProductoPago;

@Service
public class ApiRestMusicProServiceImpl implements IApiRestMusicProService {

	@Autowired
	private IProductoRepository iProductoRepository;

	@Autowired
	private IVentaRepository iVentaRepository;

	@Autowired
	private IMonedaRepository iMonedaRepository;

	@Autowired
	private IDetalleVentaRepository iDetalleVentaRepository;

	@Autowired
	private ICompradorRepository iCompradorRepository;
	
	@Autowired
	private ITransaccionVentaRepository iTransaccionVentaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductoDTO> obtenerListadoCategoria() {
		List<ProductoEntity> listaProducto = iProductoRepository.findAll();
		return listaProducto.stream().map(producto -> modelMapper.map(producto, ProductoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductoDTO obtenerProductoPorId(Integer id) {
		ProductoEntity producto = iProductoRepository.findById(id).orElse(null);
		return modelMapper.map(producto, ProductoDTO.class);
	}

	@Override
	public VoucherTransaccionDTO realizaPagoProductos(PagoProducto pagoProducto) {

		actualizarStockProductos(pagoProducto);
		

		return realizaVenta(pagoProducto);
	}

	private VoucherTransaccionDTO realizaVenta(PagoProducto pagoProducto) {

		VentaEntity venta = new VentaEntity();
		MonedaEntity moneda = iMonedaRepository.findById(pagoProducto.getCodMoneda()).orElse(null);
		CompradorEntity comprador = guardarComprador(pagoProducto);
		venta.setIdComprador(comprador.getIdComprador());
		venta.setIdTipoPago(pagoProducto.getCodPago());
		if(obtenerDescuento(pagoProducto) > pagoProducto.getMontoPagadoComprador()) {
			throw new MontoVentaInferior("El monto del comprador es "+pagoProducto.getMontoPagadoComprador()+ " a la venta" + obtenerDescuento(pagoProducto));
		}
		venta.setTotalAPagarDesc(obtenerDescuento(pagoProducto));
		venta.setMoneda(moneda);
		venta = iVentaRepository.saveAndFlush(venta);
		detalleVenta(venta, pagoProducto);
		TransaccionVentaEntity transaccion = transaccionVenta(pagoProducto,venta);
		
		VoucherTransaccionDTO voucher = modelMapper.map(transaccion, VoucherTransaccionDTO.class);
		voucher.setMonedaDescripcion(new FuncionesUtiles().descripcionMoneda(voucher.getIdMoneda()));
		return voucher;
	}

	private CompradorEntity guardarComprador(PagoProducto pagoProducto) {
		CompradorEntity comprador = new CompradorEntity();
		comprador.setRut(pagoProducto.getDatosComprador().getRut());
		comprador.setDvRut(pagoProducto.getDatosComprador().getDvRut());
		comprador.setNombreComp(pagoProducto.getDatosComprador().getNombreComp());
		comprador.setSegNombreComp(pagoProducto.getDatosComprador().getSegNombreComp());
		comprador.setApelliPaternoComp(pagoProducto.getDatosComprador().getApellidoPComp());
		comprador.setApelliMaternoComp(pagoProducto.getDatosComprador().getApellidoMComp());
		comprador.setDireccionComp(pagoProducto.getDatosComprador().getDireccionComp());
		return iCompradorRepository.saveAndFlush(comprador);
	}
	
	private TransaccionVentaEntity transaccionVenta(PagoProducto producto, VentaEntity venta) {
		System.out.println("Objeto Producto: " + producto);
		FuncionesUtiles funcionUtils = new FuncionesUtiles();
		TransaccionVentaEntity transaccion = new TransaccionVentaEntity();
		transaccion.setIdTransaVenta(funcionUtils.getRandomHexString(14));
		transaccion.setIdVenta(venta.getIdVenta());
		transaccion.setIdMoneda(producto.getCodMoneda());
		transaccion.setTipoPago(funcionUtils.descripcionFormaPago(producto.getCodPago()));
		
		transaccion.setEstadoTransaccion("APROBADO");
		transaccion.setMontoVentaDCompra(venta.getTotalAPagarDesc());
		transaccion.setMontoCanceladoVentaDCompra(producto.getMontoPagadoComprador());
		
		if(venta.getTotalAPagarDesc()==producto.getMontoPagadoComprador()) {
			transaccion.setMontoVueltoDineroCompr(0);			
		}
		if(producto.getMontoPagadoComprador() > venta.getTotalAPagarDesc()) {
			transaccion.setMontoVueltoDineroCompr(producto.getMontoPagadoComprador()-venta.getTotalAPagarDesc());			
		}
		return iTransaccionVentaRepository.saveAndFlush(transaccion);
		
	}

	private List<Integer> actualizarStockProductos(PagoProducto productos) {
		List<Integer> listaCodProdSinStock = new ArrayList<Integer>();
		productos.getListaProductos().stream().forEach(producto -> {
			ProductoEntity result = iProductoRepository.findById(producto.getIdProducto()).orElse(null);
			if (result != null) {
				if (result.getCantidadProducto() > 1) {
					result.setCantidadProducto(result.getCantidadProducto() - producto.getCantidadProducto());
					iProductoRepository.saveAndFlush(result);
				} else {
					listaCodProdSinStock.add(producto.getIdProducto());
				}
			}
		});

		return listaCodProdSinStock;
	}

	private void detalleVenta(VentaEntity venta, PagoProducto productos) {

		List<DetalleVentaEntity> listaInsertProductos = new ArrayList<>();
		for (ProductoPago producto : productos.getListaProductos()) {
			ProductoEntity result = iProductoRepository.findById(producto.getIdProducto()).orElse(null);
			if (result != null) {
				DetalleVentaEntity detalleVenta = new DetalleVentaEntity();
				detalleVenta.setIdVenta(venta.getIdVenta());
				detalleVenta.setProducto(result.getIdProducto());
				detalleVenta.setCantidadProducto(producto.getCantidadProducto());
				detalleVenta.setMontoProducto(result.getValorProducto());
				detalleVenta.setDescuentoProducto(result.getValorProducto()- (result.getValorProducto() * result.getDescuentoProductoPorc() / 100));
				listaInsertProductos.add(detalleVenta);
			}
		}
		iDetalleVentaRepository.saveAllAndFlush(listaInsertProductos);
	}

	private Integer obtenerDescuento(PagoProducto productos) {

		Map<String, Integer> valor = new HashMap<>();
		valor.put("descuento", 0);
		productos.getListaProductos().stream().forEach(producto -> {
			ProductoEntity result = iProductoRepository.findById(producto.getIdProducto()).orElse(null);
			if (result != null) {
				Integer descuento = valor.get("descuento");
				descuento += result.getValorProducto()
						- (result.getValorProducto() * result.getDescuentoProductoPorc() / 100);
				valor.put("descuento", descuento);
			}
		});

		return valor.get("descuento");
	}

}
