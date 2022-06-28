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
@Table(schema = "musicpro", name="MONEDA")
public class MonedaEntity {
	
	@Id
	@Column(name="ID_MONEDA")
	private Integer idMoneda;
	
	@Column(name="MONEDA_DESCRIP")
	private String monedaDescrip;

}
