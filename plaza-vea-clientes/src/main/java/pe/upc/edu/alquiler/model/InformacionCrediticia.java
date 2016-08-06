package pe.upc.edu.alquiler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="informacionCrediticia")
public class InformacionCrediticia {

	@Id
	@Column(name="pk_idEvaluacion")    
	private Long idEvaluacion;

	@OneToOne(fetch = FetchType.LAZY)
	//@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="pk_idEvaluacion")
	private EvaluacionEmpresarial evaluacionEmpresarial;
	//private InfEstEmp infEstEmp;
	
	@Column(name="calificacionAbr")
	private String calificacionAbr;
	
	@Column(name="calificacion")
	private String calificacion;
	
	@Column(name="valorCalificacion")
	private Double valorCalificacion;
	
	@Column(name="score")
	private Double score;
	
	@Column(name="deudaAcumulada")
	private Double deudaAcumulada;

	@Column(name="valorDeuda")
	private Double valorDeuda;
	
	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public Double getValorCalificacion() {
		return valorCalificacion;
	}

	public void setValorCalificacion(Double valorCalificacion) {
		this.valorCalificacion = valorCalificacion;
	}

	public Double getValorDeuda() {
		return valorDeuda;
	}

	public void setValorDeuda(Double valorDeuda) {
		this.valorDeuda = valorDeuda;
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
