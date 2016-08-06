package pe.upc.edu.alquiler.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="criterioRedSocial")
public class CriterioRedSocial {
	
	@Id
	@EmbeddedId
	private CriterioRedSocialLlave crsLlave;

	
	public CriterioRedSocialLlave getCrsLlave() {
		return crsLlave;
	}

	public void setCrsLlave(CriterioRedSocialLlave crsLlave) {
		this.crsLlave = crsLlave;
	}

}
