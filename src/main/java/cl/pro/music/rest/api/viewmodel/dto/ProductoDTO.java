package cl.pro.music.rest.api.viewmodel.dto;

import lombok.Data;

@Data
public class ProductoDTO {

	private Integer idProducto;
	private String descripcionProducto;
	private Integer cantidadProducto;
	private Integer valorProducto;
	private Integer idCategoria;
	private Integer idMarca;
}
