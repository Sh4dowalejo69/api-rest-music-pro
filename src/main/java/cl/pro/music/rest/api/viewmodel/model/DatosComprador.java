package cl.pro.music.rest.api.viewmodel.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DatosComprador {
	
	private Integer rut;
	private String dvRut;
	@NotNull(message = "El nombre no puede ser null")
	private String nombreComp;
	private String segNombreComp;
	@NotNull(message = "El apellido paterno no puede ser null")
	private String apellidoPComp;
	@NotNull(message = "El apellido materno no puede ser null")
	private String apellidoMComp;
	@NotNull(message = "El direccion no puede ser null")
	private String direccionComp;
	
	
}
