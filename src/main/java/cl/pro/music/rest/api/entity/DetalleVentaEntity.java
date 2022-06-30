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
@Table(schema = "musicpro", name="DETALLE_VENTA")
public class DetalleVentaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;

	@Column(name="ID_VENTA")
	private Integer idVenta;
	
	@Column(name="ID_PRODUCTO")
	private Integer producto;
	
	@Column(name="CANTIDAD_PRODUCTO")
	private Integer cantidadProducto;
	
	@Column(name="MONTO_PRODUCTO")
	private Integer montoProducto;
	
	@Column(name="DESCUENTO_PRODUCTO")
	private Integer descuentoProducto;
	 
}
