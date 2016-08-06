package pe.upc.edu.alquiler.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="evaluacionRedSocial")
public class EvaluacionRedSocial {
			
	@Id
	@Column(name="pk_idEvaluacion")    
	private Long idEvaluacion;
	
	@OneToOne(fetch = FetchType.LAZY)	
	private Evaluacion evaluacion;
	
	@Column(name="codEvaRedSoc")	//Código de la Evaluación de Redes Sociales
	private String codEvaRedSoc;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="nivelAceptacion")
	private String nivelAceptacion;

	@Column(name="fecha")	
	private Date fecha;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="estado")
	private Integer estado;	

	//@OneToMany(mappedBy = "arsLlave.evaluacionRedesSociales", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OneToMany
    @JoinColumn(name="pk_idEvaluacion")
	private List<AnalisisPorRedSocial> listaAnalisisPorRedSocial;
	
	//=====================
	//	GETTERS & SETTERS
	//=====================
	
	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public String getCodEvaRedSoc() {
		return codEvaRedSoc;
	}

	public void setCodEvaRedSoc(String codEvaRedSoc) {
		this.codEvaRedSoc = codEvaRedSoc;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNivelAceptacion() {
		return nivelAceptacion;
	}

	public void setNivelAceptacion(String nivelAceptacion) {
		this.nivelAceptacion = nivelAceptacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<AnalisisPorRedSocial> getListaAnalisisPorRedSocial() {
		return listaAnalisisPorRedSocial;
	}

	public void setListaAnalisisPorRedSocial(
			List<AnalisisPorRedSocial> listaAnalisisPorRedSocial) {
		this.listaAnalisisPorRedSocial = listaAnalisisPorRedSocial;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
