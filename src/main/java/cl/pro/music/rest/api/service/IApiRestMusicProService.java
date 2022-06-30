package cl.pro.music.rest.api.service;

import java.util.List;

import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;

public interface IApiRestMusicProService {

	List<ProductoDTO> obtenerListadoCategoria();
	
	ProductoDTO obtenerProductoPorId(Integer id);
	
	VoucherTransaccionDTO realizaPagoProductos(PagoProducto pagoProducto);
}
