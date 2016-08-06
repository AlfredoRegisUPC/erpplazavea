package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipoRedSocial")
public class TipoRedSocial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pk_idTipoRedSocial")
	private Integer idTipoRedSocial;
	
	public Integer getIdTipoRedSocial() {
		return idTipoRedSocial;
	}

	public void setIdTipoRedSocial(Integer idTipoRedSocial) {
		this.idTipoRedSocial = idTipoRedSocial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="estado")
	private Integer estado;
}
