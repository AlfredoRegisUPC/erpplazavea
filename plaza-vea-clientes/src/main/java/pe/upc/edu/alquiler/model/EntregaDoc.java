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
@Table(name="entregaDoc")
public class EntregaDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idEntregaDoc")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntregaDoc;
	
    @Column(name="idRequisito")
	private Long idRequisito;
	
	@Column(name="idSolicitud")
	private String idSolicitud;
	
	@Column(name="fechaEntrega")
	@Type(type="date")
	private Date fechaEntrega;

	@Column(name="estado")
	private String estado;
	
	
	

	public Long getIdEntregaDoc() {
		return idEntregaDoc;
	}

	public void setIdEntregaDoc(Long idEntregaDoc) {
		this.idEntregaDoc = idEntregaDoc;
	}

	public Long getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(Long idRequisito) {
		this.idRequisito = idRequisito;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
