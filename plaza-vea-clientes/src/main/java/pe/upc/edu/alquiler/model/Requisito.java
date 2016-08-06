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
@Table(name="requisito")
public class Requisito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idRequisito")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRequisito;
	
	@Column(name="codRequisito")
	private String codRequisito;
	
	@Column(name="detalle")
	private String detalle;
	
	@Column(name="vigencia")
	private String vigencia;

	public Long getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(Long idRequisito) {
		this.idRequisito = idRequisito;
	}

	public String getCodRequisito() {
		return codRequisito;
	}

	public void setCodRequisito(String codRequisito) {
		this.codRequisito = codRequisito;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	
	
}
