package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="locatarioMBean")
@RequestScoped
@Component
public class LocatarioMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{locatarioBean}")
	private Locatario locatario = new  Locatario();
	
	@Autowired
	private AlquilerService alquilerService;
	
	public LocatarioMBean() {
	}
	
	public void getObtenerLocatario() {
		System.out.println("en getObtenerLocatario, el RUC es:" + locatario.getRuc());
		try {
			String ruc = locatario.getRuc();
			Locatario locatarioBd = this.alquilerService.obtenerLocatario(locatario.getRuc());
			if(locatarioBd == null){
				locatario.setRuc(ruc);
			}
			else{
				this.locatario = locatarioBd;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int registrarLocatario() {
		int regLoc = 0;
		try {
			regLoc = this.alquilerService.registrarLocatario(locatario);
			if(regLoc > 0)
			this.locatario = this.alquilerService.obtenerLocatario(locatario.getRuc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}


	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}
	
	

}
