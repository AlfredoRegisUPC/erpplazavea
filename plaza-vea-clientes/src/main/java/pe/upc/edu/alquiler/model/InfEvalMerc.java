package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class InfEvalMerc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idInfEvalMerc")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idInfEvalMerc;
	
	@Column(name="idEvaluacion")
	private Long idEvaluacion;
	
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

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
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

	
}
