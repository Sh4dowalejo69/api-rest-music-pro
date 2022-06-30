package cl.pro.music.rest.api.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(schema = "musicpro", name="VENTA")
public class VentaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_VENTA")
	private Integer idVenta;
	
	@Column(name="ID_COMPRADOR")
	private Integer idComprador;
	
	@Column(name="FECHA_VENTA")
	private Date fechaVenta;
	
	@Column(name="ID_TIPO_PAGO")
	private Integer idTipoPago;
	
	@Column(name="TOTAL_A_PAGAR_DESC")
	private Integer totalAPagarDesc;
	
	@JoinColumn(name="ID_MONEDA")
	@ManyToOne(optional=false, cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private MonedaEntity moneda;

	
	@PrePersist
	protected void prePersist() {
		
		this.fechaVenta = new Date(System.currentTimeMillis());
	}
	
}
