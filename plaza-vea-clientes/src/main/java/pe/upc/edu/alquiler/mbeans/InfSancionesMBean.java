package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="infSancionesMBean")
@RequestScoped
@Component
public class InfSancionesMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InfSanciones infSanciones;
	private List<InfSancionesMBean> listaInfSanciones;
	
	
	@Autowired
	private AlquilerService alquilerService;
	
	public InfSancionesMBean() {
	}
	
	public int getObtenerInfSanciones(long idEvaluacion) {
		try {
			this.infSanciones= this.alquilerService.obtenerInfSanciones(idEvaluacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public int registrarInfEstEmp(InfSanciones infSanciones) {
		try {
			//this.alquilerService.registrarInfEstEmp(infEstEmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public InfSanciones getInfSanciones() {
		return infSanciones;
	}

	public void setInfSanciones(InfSanciones infSanciones) {
		this.infSanciones = infSanciones;
	}

	public List<InfSancionesMBean> getListaInfSanciones() {
		return listaInfSanciones;
	}

	public void setListaInfSanciones(List<InfSancionesMBean> listaInfSanciones) {
		this.listaInfSanciones = listaInfSanciones;
	}

	
	
}
