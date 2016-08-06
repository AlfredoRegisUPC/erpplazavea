package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnalisisRedSocialLlave implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//@OneToOne(cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn(name="pk_idEvaluacion")
	//@JoinColumn(name="pk_idEvaluacion")	
	//private EvaluacionRedSocial evaluacionRedesSociales;

	
	//@OneToOne(cascade=CascadeType.ALL) 
	//@JoinColumn(name="pk_idRedSocial")	
	//private RedSocial redSocial;

	
	//@OneToOne(cascade=CascadeType.ALL) 
	//@JoinColumn(name="pk_idCriterio")	
	//private Criterio criterio;	
	
	@Column(name="pk_idEvaluacion")
	private Long idEvaluacion;
	
	@Column(name="pk_idRedSocial")
	private Integer idRedSocial;
	
	@Column(name="pk_idCriterio")
	private Long idCriterio;

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public Integer getIdRedSocial() {
		return idRedSocial;
	}

	public void setIdRedSocial(Integer idRedSocial) {
		this.idRedSocial = idRedSocial;
	}

	public Long getIdCriterio() {
		return idCriterio;
	}

	public void setIdCriterio(Long idCriterio) {
		this.idCriterio = idCriterio;
	}
}
