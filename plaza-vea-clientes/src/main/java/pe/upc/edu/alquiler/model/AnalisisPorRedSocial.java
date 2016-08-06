package pe.upc.edu.alquiler.model;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="analisisRedSocial")
public class AnalisisPorRedSocial {
		
	@Id
	@EmbeddedId
	private AnalisisRedSocialLlave arsLlave = new AnalisisRedSocialLlave();
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="dato")
	private Double dato;
	
	public AnalisisPorRedSocial(){}
	
	public AnalisisPorRedSocial(Long idEvaluacion, Integer idRedSocial, Long idCriterio,
									Double valor, Double dato){
		this.arsLlave.setIdEvaluacion(idEvaluacion);
		this.arsLlave.setIdRedSocial(idRedSocial);
		this.arsLlave.setIdCriterio(idCriterio);
		this.valor = valor;
		this.dato  = dato;
	}
	//=====================
	//	GETTERS & SETTERS
	//=====================
	
	public AnalisisRedSocialLlave getArsLlave() {
		return arsLlave;
	}

	public void setArsLlave(AnalisisRedSocialLlave arsLlave) {
		this.arsLlave = arsLlave;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDato() {
		return dato;
	}

	public void setDato(Double dato) {
		this.dato = dato;
	}

}
