package cl.pro.music.rest.api.viewmodel.model;

import java.util.List;

import lombok.Data;

@Data
public class PagoProducto {
	
	private List<ProductoPago> listaProductos;
	private Integer montoTotal;
	private Integer codMoneda;
	private Integer codPago; 
	private DatosComprador datosComprador;
	private Long codNumeroTarjeta;
	private Integer montoPagadoComprador;

}
