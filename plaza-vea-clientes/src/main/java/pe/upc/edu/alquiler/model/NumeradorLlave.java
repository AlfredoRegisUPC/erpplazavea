package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class NumeradorLlave implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pk_idLocatario")	
	private Locatario locatario;
		
	@Column(name="ano",length=4)
	private Integer ano;

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	
}
