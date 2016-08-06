package pe.upc.edu.alquiler.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class ParametroLlave implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="pk_idParametro")
	private Long idParametro;
	
	@Column(name="pk_nroParametro")
	private Long nroParametro;
}
