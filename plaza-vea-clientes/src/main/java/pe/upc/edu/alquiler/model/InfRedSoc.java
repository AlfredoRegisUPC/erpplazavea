package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * CREATE TABLE `plazaveadb`.`local` (
 *  `idlocal` INT NOT NULL AUTO_INCREMENT,
 * `numeroLocal` INT NOT NULL,
 * `ubigeo` VARCHAR(6) NOT NULL,
 * `fechaApertura` DATE NOT NULL,
 * PRIMARY KEY (`idlocal`));
 * @author fest
 *
 */

@Entity
@Table(name="infEvalMerc")
public class InfRedSoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idInfEvalMerc")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idInfEvalMerc;
	
	@Column(name="codInfRedSoc")
	private String codInfRedSoc;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEvaluacion")
	private Evaluacion evaluacion;
	
	
	@Column(name="tipoRecep")
	private String tipoRecep;
	
	@Column(name="fuente")
	private String fuente;
	
	@Column(name="tipoMedio")
	private String tipoMedio;
	
	@Column(name="calificacion")
	private String calificacion;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	
	@Column(name="estado")
	private String estado;

	public Long getIdInfEvalMerc() {
		return idInfEvalMerc;
	}

	public void setIdInfEvalMerc(Long idInfEvalMerc) {
		this.idInfEvalMerc = idInfEvalMerc;
	}
	
	public String getTipoRecep() {
		return tipoRecep;
	}

	public void setTipoRecep(String tipoRecep) {
		this.tipoRecep = tipoRecep;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getTipoMedio() {
		return tipoMedio;
	}

	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public String getCodInfRedSoc() {
		return codInfRedSoc;
	}

	public void setCodInfRedSoc(String codInfRedSoc) {
		this.codInfRedSoc = codInfRedSoc;
	}

	
}
