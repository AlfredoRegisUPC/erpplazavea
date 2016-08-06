package pe.upc.edu.alquiler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="criterio")
public class Criterio {
	
	@Id
	@Column(name="pk_idCriterio")
	private Long idCriterio;
	
	@Column(name="nombreCriterio")
	private String nombreCriterio;
	
	@OneToOne
	@JoinColumns({@JoinColumn(name="fk_idParametro"),@JoinColumn(name="fk_nroParametro")})	
	private Parametro parametro;

	public Long getIdCriterio() {
		return idCriterio;
	}

	public void setIdCriterio(Long idCriterio) {
		this.idCriterio = idCriterio;
	}

	public String getNombreCriterio() {
		return nombreCriterio;
	}

	public void setNombreCriterio(String nombreCriterio) {
		this.nombreCriterio = nombreCriterio;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}
	
}
