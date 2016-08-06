package pe.upc.edu.alquiler.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sunat")
public class Sunat {

	@Id
	@Column(name="pk_idEvaluacion")    
	private Long idEvaluacion;
	
	@OneToOne(fetch = FetchType.LAZY)
	//private InfEstEmp infEstEmp;	
	private EvaluacionEmpresarial evaluacionEmpresarial;
	
	@Column(name="actividadEconomica")
	private String actividadEconomica;	
	
	@Column(name="condicion")
	private String condicion;
	
	@Column(name="tipoContribuyente")
	private String tipoContribuyente;		

	@Column(name="valorCondicion")
	private Double valorCondicion;
		
	@Column(name="valorActividadEconomica")
	private Double valorActividadEconomica;
	
	@Column(name="valorTipoContribuyente")
	private Double valorTipoContribuyente;
	
	@Column(name="valorOmisiones")
	private Double valorOmisiones;

	@OneToMany(mappedBy = "sunat", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<OmisionTributaria> detalleOmisiones;

	public Long getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(Long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public EvaluacionEmpresarial getEvaluacionEmpresarial() {
		return evaluacionEmpresarial;
	}

	public void setEvaluacionEmpresarial(EvaluacionEmpresarial evaluacionEmpresarial) {
		this.evaluacionEmpresarial = evaluacionEmpresarial;
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

	public Double getValorCondicion() {
		return valorCondicion;
	}

	public void setValorCondicion(Double valorCondicion) {
		this.valorCondicion = valorCondicion;
	}

	public Double getValorActividadEconomica() {
		return valorActividadEconomica;
	}

	public void setValorActividadEconomica(Double valorActividadEconomica) {
		this.valorActividadEconomica = valorActividadEconomica;
	}

	public Double getValorTipoContribuyente() {
		return valorTipoContribuyente;
	}

	public void setValorTipoContribuyente(Double valorTipoContribuyente) {
		this.valorTipoContribuyente = valorTipoContribuyente;
	}

	public Double getValorOmisiones() {
		return valorOmisiones;
	}

	public void setValorOmisiones(Double valorOmisiones) {
		this.valorOmisiones = valorOmisiones;
	}

	public Set<OmisionTributaria> getDetalleOmisiones() {
		return detalleOmisiones;
	}

	public void setDetalleOmisiones(Set<OmisionTributaria> detalleOmisiones) {
		this.detalleOmisiones = detalleOmisiones;
	}
	
	

}
