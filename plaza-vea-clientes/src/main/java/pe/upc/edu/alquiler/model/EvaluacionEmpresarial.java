package pe.upc.edu.alquiler.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="evaluacionEmpresarial")
public class EvaluacionEmpresarial {

	@Id
	@Column(name="pk_idEvaluacion")    
	private Long idEvaluacion;
			
	@OneToOne(fetch = FetchType.LAZY)	
	private Evaluacion evaluacion;
	
	@OneToOne(fetch = FetchType.LAZY)	
	private Sunat sunat;
	
	//@OneToOne(fetch = FetchType.LAZY)
	@OneToOne(mappedBy = "evaluacionEmpresarial", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private InformacionCrediticia informacionCrediticia;
	
	@Column(name="codInfEstEmp")
	private String codInfEstEmp;

	@Column(name="resultado")
	private String resultado;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="estado")
	private Integer estado;

	@Column(name="totalScore")
	private Double totalScore;
	
	@Column(name="fecha")	
	private Date fecha;
	
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	
	public Sunat getSunat() {
		return sunat;
	}

	public void setSunat(Sunat sunat) {
		this.sunat = sunat;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getCodInfEstEmp() {
		return codInfEstEmp;
	}

	public void setCodInfEstEmp(String codInfEstEmp) {
		this.codInfEstEmp = codInfEstEmp;
	}

	public InformacionCrediticia getInformacionCrediticia() {
		return informacionCrediticia;
	}

	public void setInformacionCrediticia(InformacionCrediticia informacionCrediticia) {
		this.informacionCrediticia = informacionCrediticia;
	}
}
