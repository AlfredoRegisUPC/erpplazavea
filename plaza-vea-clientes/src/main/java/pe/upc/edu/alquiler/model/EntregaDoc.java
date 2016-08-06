package pe.upc.edu.alquiler.model;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@AssociationOverrides({
	@AssociationOverride(name = "solicitud", 
		joinColumns = @JoinColumn(name = "idSolicitud")),
	@AssociationOverride(name = "requisito", 
		joinColumns = @JoinColumn(name = "idRequisito")) })
public class EntregaDoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
/*	@Id
    @Column(name="idEntregaDoc")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntregaDoc;
	
    @Column(name="idRequisito")
	private Long idRequisito;
	@ManyToOne
    @JoinColumn(name = "idRequisito")
	private Requisito requisito;
	
	@Column(name="idSolicitud")
	private String idSolicitud;
    @ManyToOne
    @JoinColumn(name = "idSolicitud")
	private Solicitud solicitud;*/
	
	@EmbeddedId
	private SolReqId pk = new SolReqId();
	
	@Column(name="fechaEntrega")
	@Type(type="date")
	private Date fechaEntrega;

	@Column(name="estado")
	private String estado;
	
	
	public SolReqId getPk() {
		return pk;
	}

	public void setPk(SolReqId pk) {
		this.pk = pk;
	}

	
	@Transient
	public Solicitud getSolicitud() {
		return getPk().getSolicitud();
	}

	public void setSolicitud(Solicitud solicitud) {
		getPk().setSolicitud(solicitud);
	}

	@Transient
	public Requisito getRequisito() {
		return getPk().getRequisito();
	}

	public void setRequisito(Requisito requisito) {
		getPk().setRequisito(requisito);
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
