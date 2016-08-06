package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="solicitud")
public class Solicitud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idSolicitud")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSolicitud;
	
	@Column(name="codSolicitud")
	private String codSolicitud;
	
	@Column(name="fecha")
	@Type(type="date")
	private Date fecha;
	
	@Column(name="solicitante")
	private String solicitante;
	
	@Column(name="estado")
	private String estado;
	
	@ManyToOne
    @JoinColumn(name = "idLocacion")
	private Locacion locacion;

	/*@Column(name="idLocacion")
	private Long idLocacion;*/
	
	@ManyToOne
    @JoinColumn(name = "idLocatario")
	private Locatario locatario;

	@Column(name="usuarioCreacion")
	private String usuarioCreacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.solicitud", cascade=CascadeType.ALL)
	private Set<EntregaDoc> entregaDoc = new HashSet<EntregaDoc>(0);



	public Long getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getCodSolicitud() {
		return codSolicitud;
	}

	public void setCodSolicitud(String codSolicitud) {
		this.codSolicitud = codSolicitud;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	public Set<EntregaDoc> getEntregaDoc() {
		return entregaDoc;
	}

	public void setEntregaDoc(Set<EntregaDoc> entregaDoc) {
		this.entregaDoc = entregaDoc;
	}


	
	
	
}
