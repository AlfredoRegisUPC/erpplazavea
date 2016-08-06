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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import pe.upc.edu.cliente.model.Local;

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
@Table(name="locacion")
public class Locacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="idLocacion")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLocacion;
	
	/*@Column(name="idLocal")
	private Long idLocal;*/
	
	@Column(name="dimensiones")
	private String dimensiones;
	
	@Column(name="ubicacion")
	private String ubicacion;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="estado")
	private String estado;
	
	@ManyToOne
    @JoinColumn(name = "idLocal")
	private Local local;
	
	@OneToMany(mappedBy = "locacion", cascade = CascadeType.ALL)
	private Set<Solicitud> solicitudes;

	public Long getIdLocacion() {
		return idLocacion;
	}

	public void setIdLocacion(Long idLocacion) {
		this.idLocacion = idLocacion;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Set<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}


	
}
