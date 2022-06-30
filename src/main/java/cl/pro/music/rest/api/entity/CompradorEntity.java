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
@Table(schema = "musicpro", name="COMPRADOR")
public class CompradorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_COMPRADOR")
	private Integer idComprador;
	
	@Column(name="RUT")
	private Integer rut;
	
	@Column(name="DV_RUT")
	private String dvRut;
	
	@Column(name="NOMBRE_COMP")
	private String nombreComp;
	
	@Column(name="SEG_NOMBRE_COMP")
	private String segNombreComp;
	
	@Column(name="APELLI_PATERNO_COMP")
	private String apelliPaternoComp;
	
	@Column(name="APELLI_MATERNO_COMP")
	private String apelliMaternoComp;

	@Column(name="DIRECCION_COMP")
	private String direccionComp;
	
}
