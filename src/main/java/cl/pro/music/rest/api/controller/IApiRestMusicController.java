package cl.pro.music.rest.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.pro.music.rest.api.viewmodel.dto.CategoriaDTO;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;
import cl.pro.music.rest.api.viewmodel.dto.VoucherTransaccionDTO;
import cl.pro.music.rest.api.viewmodel.model.PagoProducto;
import io.swagger.annotations.ApiOperation;

@RequestMapping("")
@Validated
public interface IApiRestMusicController {
	
	@ApiOperation(value="Obtiene el listados de los productos de la tienda music pro",nickname="listaProductos",notes="Obtiene el listados de los productos de la tienda music pro",tags={"Api Music Pro"})
	@GetMapping(value="listaProductos", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ProductoDTO>> listaDeProductos();
	
	@ApiOperation(value="Obtiene el categoria de productos",nickname="listaCategoria",notes="Obtiene el categoria de productos",tags={"Api Music Pro"})
	@GetMapping(value="listaCategoria", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<CategoriaDTO>> listaDeCategoria();
	
	@ApiOperation(value="Obtiene el categoria de productos",nickname="listaCategoria",notes="Obtiene el categoria de productos",tags={"Api Music Pro"})
	@GetMapping(value="listaProductosPorCategoria/{idCategoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ProductoDTO>> listaDeProductosPorCategoria(@PathVariable Integer idCategoria);
	
	
	
	@ApiOperation(value="Obtener producto por id",nickname="findById",notes="Obtener producto por id",tags={"Api Music Pro"})
	@GetMapping(value="productoFindById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Integer id);
	
	
	@ApiOperation(value="Pago de producto/s de un cliente Music Pro", nickname="pagoProductos", notes="Pago de producto/s",tags={"Api Music Pro"})
	@PostMapping(value="pagoProductos", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<VoucherTransaccionDTO> pagoProductos(@Valid @RequestBody  PagoProducto pagoProducto);
}