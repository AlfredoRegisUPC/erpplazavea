package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

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
@Table(name="locatario")
public class Locatario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idLocatario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLocatario;
	
	@Column(name="ruc")
	@Size(min=11,max=11)
	private String ruc;
	
	@Column(name="razonSocial")
	private String razonSocial;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;

	@Column(name="representante")
	private String representante;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="facebook")
	private String facebook;
	
	@Column(name="twitter")
	private String twitter;
	
	@Column(name="instagram")
	private String instagram;
	
	@Column(name="siglas",length=6)
	private String siglas;
	
	@OneToMany(mappedBy = "locatario", cascade = CascadeType.ALL)
	private Set<Solicitud> solicitudes;

	public Long getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(Long idLocatario) {
		this.idLocatario = idLocatario;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	
	
	
}
