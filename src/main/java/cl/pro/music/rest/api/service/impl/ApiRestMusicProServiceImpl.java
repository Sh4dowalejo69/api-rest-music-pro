package cl.pro.music.rest.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.pro.music.rest.api.entity.MonedaEntity;
import cl.pro.music.rest.api.entity.ProductoEntity;
import cl.pro.music.rest.api.entity.VentaEntity;
import cl.pro.music.rest.api.repository.IMonedaRepository;
import cl.pro.music.rest.api.repository.IProductoRepository;
import cl.pro.music.rest.api.repository.IVentaRepository;
import cl.pro.music.rest.api.service.IApiRestMusicProService;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;

@Service
public class ApiRestMusicProServiceImpl implements IApiRestMusicProService{
	
	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Autowired
	private IVentaRepository iVentaRepository;

	@Autowired
	private IMonedaRepository iMonedaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public List<ProductoDTO> obtenerListadoCategoria() {
		List<ProductoEntity> listaProducto = iProductoRepository.findAll();
		return listaProducto.stream().map(producto -> modelMapper.map(producto, ProductoDTO.class)).collect(Collectors.toList());
	}


	@Override
	public ProductoDTO obtenerProductoPorId(Integer id) {
		ProductoEntity producto = iProductoRepository.findById(id).orElse(null);
		return modelMapper.map(producto,ProductoDTO.class);
	}


	@Override
	public Void realizaPagoProductos(PagoProducto pagoProducto){
		
		List<Integer> listaIdsProdSinStock = new ArrayList<Integer>();
		
	
		try {
			
			if(pagoProducto.getMontoTotal() > pagoProducto.getMontoPagadoComprador()) {
				System.out.println("El monto pagado es inferior al monto total");
			}
			listaIdsProdSinStock = actualizarStockProductos(pagoProducto);
			
			realizaVenta(pagoProducto);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private void realizaVenta(PagoProducto pagoProducto) {
		
		VentaEntity venta = new VentaEntity();
		MonedaEntity moneda = iMonedaRepository.findById(pagoProducto.getCodMoneda()).orElse(null);		
		venta.setIdTipoPago(pagoProducto.getCodPago());
		venta.setTotalAPagarDesc(obtenerDescuento(pagoProducto));
		venta.setTotalAPagar(pagoProducto.getMontoTotal());
		venta.setMoneda(moneda);
		venta = iVentaRepository.saveAndFlush(venta);
		
		
		
		
		
	}
	
	
	private List<Integer> actualizarStockProductos(PagoProducto productos) {
		List<Integer> listaCodProdSinStock = new ArrayList<Integer>();
		productos.getListaProductos().stream().forEach(producto->{
			ProductoEntity result = iProductoRepository.findById(producto.getIdProducto()).orElse(null);
			if(result != null) {
				if(result.getCantidadProducto()>1) {
					result.setCantidadProducto(result.getCantidadProducto()-producto.getCantidadProducto());
					iProductoRepository.saveAndFlush(result);	
				} else {
					listaCodProdSinStock.add(producto.getIdProducto());	
				}
			}
		});
		
		return listaCodProdSinStock;
	}
	
	private void detalleVenta(VentaEntity venta, PagoProducto productos) {
		
		
		
		
	}
	
	private Integer obtenerDescuento(PagoProducto productos) {
		
		Map<String, Integer> valor = new HashMap<>();
		valor.put("descuento", 0);

		productos.getListaProductos().stream().forEach(producto->{
			ProductoEntity result = iProductoRepository.findById(producto.getIdProducto()).orElse(null);
			if(result != null) {
				Integer descuento = valor.get("descuento");
				descuento +=result.getValorProducto()-(result.getValorProducto() * result.getDescuentoProductoPorc() / 100);	
				valor.put("descuento", descuento);
			}
		});
		
		return valor.get("descuento");
	}
	
 
}
