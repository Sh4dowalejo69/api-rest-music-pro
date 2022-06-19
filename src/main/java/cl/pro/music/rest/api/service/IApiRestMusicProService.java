package cl.pro.music.rest.api.service;

import java.util.List;

import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;

public interface IApiRestMusicProService {

	List<ProductoDTO> obtenerListadoCategoria();
	
	ProductoDTO obtenerProductoPorId(Integer id);
}
