package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CriterioRedSocialLlave implements Serializable{

	private static final long serialVersionUID = 1L;
	/*
	@OneToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="pk_idRedSocial")	
	private RedSocial redSocial;
	*/
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pk_idRedSocial")
	//@JoinColumns({@JoinColumn(name="pk_idCriterio"),@JoinColumn(name="pk_idRedSocial")})	
	private RedSocial redSocial;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pk_idCriterio")	
	private Criterio criterio;

	public RedSocial getRedSocial() {
		return redSocial;
	}

	public void setRedSocial(RedSocial redSocial) {
		this.redSocial = redSocial;
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}
	
}
