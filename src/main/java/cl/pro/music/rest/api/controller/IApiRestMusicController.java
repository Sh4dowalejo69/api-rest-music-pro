package cl.pro.music.rest.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import io.swagger.annotations.ApiOperation;

@RequestMapping("")
public interface IApiRestMusicController {
	
	@ApiOperation(value="listaProductos",nickname="listaProductos",notes="Obtiene el listados de los productos de la tienda music pro",tags={"api-music-pro"})
	@GetMapping(value="listaProductos", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ProductoDTO>> listaDeProductos();
	
	
	@ApiOperation(value="listaProductos",nickname="listaProductos",notes="Obtener producto por id",tags={"api-music-pro"})
	@GetMapping(value="findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer id);
	
}
