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
@Table(name="infSanciones")
public class InfSanciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idInfSanciones")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idInfSanciones;
	
	@Column(name="idEvaluacion")
	private Long idEvaluacion;
	
	@Column(name="antBancario")
	private String antBancario;
	
	@Column(name="estadoBanc")
	private String estadoBanc;
	
	@Column(name="montoDeuda")
	private Double montoDeuda;
	
	@Column(name="fechaDeuda")
	@Type(type="date")
	private Date fechaDeuda;
	
	@Column(name="estadoInfocorp")
	private String estadoInfocorp;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	
	@Column(name="estado")
	private String estado;

	public Long getIdInfSanciones() {
		return idInfSanciones;
	}

	public void setIdInfSanciones(Long idInfSanciones) {
		this.idInfSanciones = idInfSanciones;
	}

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getAntBancario() {
		return antBancario;
	}

	public void setAntBancario(String antBancario) {
		this.antBancario = antBancario;
	}

	public String getEstadoBanc() {
		return estadoBanc;
	}

	public void setEstadoBanc(String estadoBanc) {
		this.estadoBanc = estadoBanc;
	}

	public Double getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(Double montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public Date getFechaDeuda() {
		return fechaDeuda;
	}

	public void setFechaDeuda(Date fechaDeuda) {
		this.fechaDeuda = fechaDeuda;
	}

	public String getEstadoInfocorp() {
		return estadoInfocorp;
	}

	public void setEstadoInfocorp(String estadoInfocorp) {
		this.estadoInfocorp = estadoInfocorp;
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
