package cl.pro.music.rest.api.viewmodel.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PagoProducto {
	
	@NotEmpty(message = "La lista de productos no puede estar vacia")
	@NotNull(message = "La lista de productos no puede estar nula")
	private List<ProductoPago> listaProductos;
	
	@NotNull(message = "El codigo del moneda puede ser nulo")
	private Integer codMoneda;
	@NotNull(message = "El codigo del pago no puede ser nulo")
	private Integer codPago; 
	@Valid
	private DatosComprador datosComprador;
	private Long codNumeroTarjeta;
	@NotNull(message = "Monto Comprador no puede ser nulo")
	private Integer montoPagadoComprador;

}
