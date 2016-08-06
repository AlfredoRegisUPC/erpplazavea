package pe.upc.edu.alquiler.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sunatData")
public class SunatData {

	@Id
	@Column(name="pk_idLocatario")    
	private Long idLocatario;
		
	@Column(name="actividadEconomica")
	private String actividadEconomica;	
	
	@Column(name="condicion")
	private String condicion;
	
	@Column(name="tipoContribuyente")
	private String tipoContribuyente;


	@OneToMany(mappedBy = "sunat", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OmisionTributaria> detalleOmisiones;
	
	public List<OmisionTributaria> getDetalleOmisiones() {
		return detalleOmisiones;
	}
	
	public Long getIdLocatario() {
		return idLocatario;
	}

	public void setIdLocatario(Long idLocatario) {
		this.idLocatario = idLocatario;
	}

	public void setDetalleOmisiones(List<OmisionTributaria> detalleOmisiones) {
		this.detalleOmisiones = detalleOmisiones;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getTipoContribuyente() {
		return tipoContribuyente;
	}

	public void setTipoContribuyente(String tipoContribuyente) {
		this.tipoContribuyente = tipoContribuyente;
	}

}
