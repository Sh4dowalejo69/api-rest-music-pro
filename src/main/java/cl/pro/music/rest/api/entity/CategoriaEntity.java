package cl.pro.music.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(schema = "musicpro", name="CATEGORIA_PRODUCTO")
public class CategoriaEntity {
		
	@Id
	@Column(name="ID_CATEGORIA")
	private Integer idCategoria;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
}
