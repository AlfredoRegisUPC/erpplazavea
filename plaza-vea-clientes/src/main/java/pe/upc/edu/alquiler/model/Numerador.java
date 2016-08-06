package pe.upc.edu.alquiler.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="numerador")
public class Numerador {

	@Id
	@EmbeddedId
	private NumeradorLlave numeradorLlave;
	
	@Column(name="secuencia")
	private Integer secuencia;

	public NumeradorLlave getNumeradorLlave() {
		return numeradorLlave;
	}

	public void setNumeradorLlave(NumeradorLlave numeradorLlave) {
		this.numeradorLlave = numeradorLlave;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	
	
}
