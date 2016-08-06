package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.EntregaDoc;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="entregaDocMBean")
@RequestScoped
@Component
public class EntregaDocMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private AlquilerService alquilerService;
	
	public EntregaDocMBean() {
	}
	

	
	public int registrarEntregaDoc(EntregaDoc requisito) {

		try {
			this.alquilerService.registrarEntregaDoc(requisito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	

}
