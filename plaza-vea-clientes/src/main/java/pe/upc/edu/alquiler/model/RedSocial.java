package pe.upc.edu.alquiler.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="redSocial")
public class RedSocial implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pk_idRedSocial")
	private Integer idRedSocial;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="fk_tipoMedio")
	private TipoRedSocial tipoRedSocial;
	
	@Column(name="estado")
	private Integer estado;

	@OneToMany(mappedBy = "crsLlave.redSocial", fetch=FetchType.EAGER, cascade = CascadeType.ALL)	
	private Set<CriterioRedSocial> criteriosRedSocial;
	
	
	public Integer getIdRedSocial() {
		return idRedSocial;
	}

	public void setIdRedSocial(Integer idRedSocial) {
		this.idRedSocial = idRedSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoRedSocial getTipoMedio() {
		return tipoRedSocial;
	}

	public void setTipoMedio(TipoRedSocial tipoMedio) {
		this.tipoRedSocial = tipoMedio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public TipoRedSocial getTipoRedSocial() {
		return tipoRedSocial;
	}

	public void setTipoRedSocial(TipoRedSocial tipoRedSocial) {
		this.tipoRedSocial = tipoRedSocial;
	}

	public Set<CriterioRedSocial> getCriteriosRedSocial() {
		return criteriosRedSocial;
	}

	public void setCriteriosRedSocial(Set<CriterioRedSocial> criteriosRedSocial) {
		this.criteriosRedSocial = criteriosRedSocial;
	}

}
