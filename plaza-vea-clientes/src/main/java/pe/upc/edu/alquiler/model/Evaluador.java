package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name="evaluador")
public class Evaluador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idEvaluador")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEvaluador;
	
	//@OneToOne
    //@JoinColumn(name = "idColaborador")
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Colaborador colaborador;
	
	@Column(name="actividad")
	private String actividad;
	
	@Column(name="fechaAct")
	@Type(type="date")
	private Date fechaAct;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="cantidadEval")
	private Integer cantidadEval;
	
	@OneToMany(mappedBy = "evaluador", cascade = CascadeType.ALL)
	private Set<Evaluacion> evaluaciones;

	public Long getIdEvaluador() {
		return idEvaluador;
	}

	public void setIdEvaluador(Long idEvaluador) {
		this.idEvaluador = idEvaluador;
	}

	

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public Date getFechaAct() {
		return fechaAct;
	}

	public void setFechaAct(Date fechaAct) {
		this.fechaAct = fechaAct;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public Integer getCantidadEval() {
		return cantidadEval;
	}

	public void setCantidadEval(Integer cantidadEval) {
		this.cantidadEval = cantidadEval;
	}

	
	
	
}
