package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.requisito", cascade=CascadeType.ALL)
	private Set<EntregaDoc> entregaDoc = new HashSet<EntregaDoc>(0);

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

	public Set<EntregaDoc> getEntregaDoc() {
		return entregaDoc;
	}

	public void setEntregaDoc(Set<EntregaDoc> entregaDoc) {
		this.entregaDoc = entregaDoc;
	}



	
	
}
