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
	
	@Column(name="codInfEstEmp")
	private String codInfEstEmp;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEvaluacion")
	private Evaluacion evaluacion;
	
	@Column(name="resultado")
	private String resultado;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="totalScore")
	private Double totalScore;
	
	@OneToOne(fetch = FetchType.LAZY)	
	private Sunat sunat;
	
	@OneToOne(fetch = FetchType.LAZY)	
	private InformacionCrediticia informacionCrediticia;
	
	public InformacionCrediticia getInformacionCrediticia() {
		return informacionCrediticia;
	}

	public void setInformacionCrediticia(InformacionCrediticia informacionCrediticia) {
		this.informacionCrediticia = informacionCrediticia;
	}

	public Sunat getSunat() {
		return sunat;
	}

	public void setSunat(Sunat sunat) {
		this.sunat = sunat;
	}

	public Long getIdInfEstEmp() {
		return idInfEstEmp;
	}

	public void setIdInfEstEmp(Long idInfEstEmp) {
		this.idInfEstEmp = idInfEstEmp;
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

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public String getCodInfEstEmp() {
		return codInfEstEmp;
	}

	public void setCodInfEstEmp(String codInfEstEmp) {
		this.codInfEstEmp = codInfEstEmp;
	}

	
	
}
