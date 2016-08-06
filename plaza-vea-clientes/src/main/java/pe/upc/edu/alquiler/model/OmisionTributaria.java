package pe.upc.edu.alquiler.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="omisionTributaria")
//@IdClass(OmisionLlave.class)
public class OmisionTributaria {//implements Serializable{
	
	//private static final long serialVersionUID = 1L;

	/*
	@EmbeddedId
	//@ManyToOne
	//@JoinColumns({@JoinColumn(name="pk_fechaConsulta"),@JoinColumn(name="pk_idEvaluacion")})
	private OmisionLlave llaveOmision;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="pk_fechaConsulta"),@JoinColumn(name="pk_idEvaluacion")})	
	private Sunat sunat;
	*/
	@Id
	@Column(name="pk_idOmision")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idOmision;
	
	//@Id
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="pk_idEvaluacion")	
	private Sunat sunat;	
			
	public Sunat getSunat() {
		return sunat;
	}

	public void setSunat(Sunat sunat) {
		this.sunat = sunat;
	}

	@Column(name="periodo")
	private String periodo;

	@Column(name="omisionDescripcion")
	private String omisionDescripcion;

	/*
	@ManyToOne
	@JoinColumns({@JoinColumn(name="pk_fechaConsulta"),@JoinColumn(name="pk_idEvaluacion")})	
	private Sunat sunat;
	*/

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Long getIdOmision() {
		return idOmision;
	}

	public void setIdOmision(Long idOmision) {
		this.idOmision = idOmision;
	}

	public String getOmisionDescripcion() {
		return omisionDescripcion;
	}

	public void setOmisionDescripcion(String omisionDescripcion) {
		this.omisionDescripcion = omisionDescripcion;
	}

	
}
