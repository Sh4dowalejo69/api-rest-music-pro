package cl.pro.music.rest.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cl.pro.music.rest.api.service.IApiRestMusicProService;
import cl.pro.music.rest.api.viewmodel.dto.CategoriaDTO;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Api Music Pro")
@RestController
public class ApiRestMusicProControllerImpl implements IApiRestMusicController{
	
	private Logger log = LoggerFactory.getLogger(ApiRestMusicProControllerImpl.class); 
	
	@Autowired
	private IApiRestMusicProService iApiRestMusicProService;
	
	@Override
	public ResponseEntity<List<ProductoDTO>> listaDeProductos() {
		log.info("Inicia busqueda de productos");
		return new ResponseEntity<>(iApiRestMusicProService.obtenerListadoProductos(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductoDTO> obtenerProductoPorId(Integer id) {
		return new ResponseEntity<>(iApiRestMusicProService.obtenerProductoPorId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VoucherTransaccionDTO> pagoProductos( PagoProducto pagoProducto) {
		return new ResponseEntity<>(iApiRestMusicProService.realizaPagoProductos(pagoProducto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<CategoriaDTO>> listaDeCategoria() {
		return new ResponseEntity<>(iApiRestMusicProService.obtenerCategoriaProductos(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductoDTO>> listaDeProductosPorCategoria(Integer idCategoria) {
		return new ResponseEntity<>(iApiRestMusicProService.obtenerListadoProductosPorCategoria(idCategoria),HttpStatus.OK);
	}

}
