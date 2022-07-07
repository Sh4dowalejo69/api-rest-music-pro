package cl.pro.music.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "musicpro", name="PRODUCTO")
public class ProductoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PRODUCTO")
	private Integer idProducto;
	
	@Column(name="TITULO_PRODUCTO")
	private String tituloProducto;
	
	@Column(name="DESCRIPCION_PRODUCT")
	private String descripcionProducto;
	
	@Column(name="CANTIDAD_PRODUCT")
	private Integer cantidadProducto;
	
	@Column(name="VALOR_PRODUCTO")
	private Integer valorProducto;
	
	@Column(name="DESCUENTO_PRODUCTO_PORC")
	private Integer descuentoProductoPorc;
	
	@Column(name="URL_IMAGEN")
	private String urlImagen;
	
	@Column(name="ID_CATEGORIA")
	private Integer idCategoria;
	
	@Column(name="ID_MARCA")
	private Integer idMarca;
	
	

}
