package pe.upc.edu.alquiler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parametro")
public class Parametro{

	@Id
	@EmbeddedId
	private ParametroLlave parametroLlave;
	
	@Column(name="rangoInicial")
	private Double rangoInicial;
	
	@Column(name="rangoFinal")
	private Double rangoFinal;
	
	@Column(name="valorNumerico")
	private Double valorNumerico;
	
	@Column(name="valorTexto")
	private String valorTexto;
	
	@Column(name="vigencia")
	private Date vigencia;
	
	@Column(name="estado")
	private Integer estado;

	public Double getRangoInicial() {
		return rangoInicial;
	}

	public void setRangoInicial(Double rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	public Double getRangoFinal() {
		return rangoFinal;
	}

	public void setRangoFinal(Double rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	public Double getValorNumerico() {
		return valorNumerico;
	}

	public void setValorNumerico(Double valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public String getValorTexto() {
		return valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

	public Date getVigencia() {
		return vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}
