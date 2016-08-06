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
@Table(name="infEstEmp")
public class InfEstEmp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idInfEstEmp")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idInfEstEmp;
	
	@Column(name="idEvaluacion")
	private Long idEvaluacion;
	
	@Column(name="actEconPrin")
	private String actEconPrin;
	
	@Column(name="repLegal")
	private String repLegal;
	
	@Column(name="omisiones")
	private String omisiones;
	
	@Column(name="estadoContri")
	private String estadoContri;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	
	@Column(name="estado")
	private String estado;

	public Long getIdInfEstEmp() {
		return idInfEstEmp;
	}

	public void setIdInfEstEmp(Long idInfEstEmp) {
		this.idInfEstEmp = idInfEstEmp;
	}

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getActEconPrin() {
		return actEconPrin;
	}

	public void setActEconPrin(String actEconPrin) {
		this.actEconPrin = actEconPrin;
	}

	public String getRepLegal() {
		return repLegal;
	}

	public void setRepLegal(String repLegal) {
		this.repLegal = repLegal;
	}

	public String getOmisiones() {
		return omisiones;
	}

	public void setOmisiones(String omisiones) {
		this.omisiones = omisiones;
	}

	public String getEstadoContri() {
		return estadoContri;
	}

	public void setEstadoContri(String estadoContri) {
		this.estadoContri = estadoContri;
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
