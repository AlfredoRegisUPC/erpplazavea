package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="omisionTributariaData")
public class OmisionTributariaData implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pk_idOmisionData")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idOmisionData;

	@Id
	@OneToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="pk_idLocatario")	
	private SunatData sunatData;	
		
	@Column(name="periodo")
	private String periodo;
	
	@Column(name="omisionDescripcion")
	private String omisionDescripcion;
		
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Long getIdOmisionData() {
		return idOmisionData;
	}

	public void setIdOmisionData(Long idOmisionData) {
		this.idOmisionData = idOmisionData;
	}

	public String getOmisionDescripcion() {
		return omisionDescripcion;
	}

	public void setOmisionDescripcion(String omisionDescripcion) {
		this.omisionDescripcion = omisionDescripcion;
	}
	
}
