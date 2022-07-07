package cl.pro.music.rest.api.service;

import java.util.List;

import cl.pro.music.rest.api.viewmodel.dto.CategoriaDTO;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;

public interface IApiRestMusicProService {

	List<CategoriaDTO> obtenerCategoriaProductos();
	
	List<ProductoDTO> obtenerListadoProductos();
	
	List<ProductoDTO> obtenerListadoProductosPorCategoria(Integer idCategoria);
	
	ProductoDTO obtenerProductoPorId(Integer id);
	
	VoucherTransaccionDTO realizaPagoProductos(PagoProducto pagoProducto);
}
