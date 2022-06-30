package cl.pro.music.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cl.pro.music.rest.api.service.IApiRestMusicProService;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="api-music-pro")
@RestController
public class ApiRestMusicProControllerImpl implements IApiRestMusicController{

	@Autowired
	private IApiRestMusicProService iApiRestMusicProService;
	
	@Override
	public ResponseEntity<List<ProductoDTO>> listaDeProductos() {
		return new ResponseEntity<>(iApiRestMusicProService.obtenerListadoCategoria(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductoDTO> obtenerProductoPorId(Integer id) {
		
		System.out.println("Id Producto: "+ id);
		return new ResponseEntity<>(iApiRestMusicProService.obtenerProductoPorId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VoucherTransaccionDTO> pagoProductos( PagoProducto pagoProducto) {
		return new ResponseEntity<>(iApiRestMusicProService.realizaPagoProductos(pagoProducto), HttpStatus.CREATED);
	}

}
