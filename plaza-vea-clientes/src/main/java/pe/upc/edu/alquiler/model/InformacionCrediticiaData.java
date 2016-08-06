package pe.upc.edu.alquiler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="informacionCrediticiaData")
public class InformacionCrediticiaData {

	@Id
	@Column(name="pk_idLocatario")    
	private Long idLocatario;
	
	@Column(name="calificacionAbr")
	private String calificacionAbr;
	
	@Column(name="calificacion")
	private String calificacion;
	
	@Column(name="score")
	private Double score;
	
	@Column(name="deudaAcumulada")
	private Double deudaAcumulada;
	

	public Long getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(Long idLocatario) {
		this.idLocatario = idLocatario;
	}

	public String getCalificacionAbr() {
		return calificacionAbr;
	}

	public void setCalificacionAbr(String calificacionAbr) {
		this.calificacionAbr = calificacionAbr;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getDeudaAcumulada() {
		return deudaAcumulada;
	}

	public void setDeudaAcumulada(Double deudaAcumulada) {
		this.deudaAcumulada = deudaAcumulada;
	}		

}
