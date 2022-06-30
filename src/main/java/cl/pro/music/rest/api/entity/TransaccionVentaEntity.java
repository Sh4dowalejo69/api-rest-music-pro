package cl.pro.music.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema="musicpro", name="TRANSACCION_VENTA")
public class TransaccionVentaEntity {

	@Id
	@Column(name="ID_TRANSA_VENTA")
	private String idTransaVenta;
	
	@Column(name="ID_VENTA")
	private Integer idVenta;
	
	@Column(name="ID_MONEDA")
	private Integer idMoneda;
	
	@Column(name="TIPO_PAGO")
	private String tipoPago;

	@Column(name="ESTADO_TRANSA")
	private String estadoTransaccion;
	
	@Column(name="MONTO_VENTA_DINERO_COMPR")
	private Integer montoVentaDCompra;
	
	@Column(name="MONTO_CANCELADO_DINERO_COMPR")
	private Integer montoCanceladoVentaDCompra;
	
	@Column(name="MONTO_VUELTO_DINERO_COMPR")
	private Integer montoVueltoDineroCompr;
	
}
