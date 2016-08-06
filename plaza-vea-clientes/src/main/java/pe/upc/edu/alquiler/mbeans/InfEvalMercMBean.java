package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="infEvalMercMBean")
@RequestScoped
@Component
public class InfEvalMercMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InfEstEmp infEstEmp;
	private List<InfEstEmp> listaInfEstEmp;
	
	
	@Autowired
	private AlquilerService alquilerService;
	
	public InfEvalMercMBean() {
	}
	
	public int getObtenerInfEstEmp(long idEvaluacion) {
		try {
			this.infEstEmp = this.alquilerService.obtenerInfEstEmp(idEvaluacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public int registrarInfEstEmp(InfEstEmp infEstEmp) {
		try {
			//this.alquilerService.registrarInfEstEmp(infEstEmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public InfEstEmp getInfEstEmp() {
		return infEstEmp;
	}

	public void setInfEstEmp(InfEstEmp infEstEmp) {
		this.infEstEmp = infEstEmp;
	}

	public List<InfEstEmp> getListaInfEstEmp() {
		return listaInfEstEmp;
	}

	public void setListaInfEstEmp(List<InfEstEmp> listaInfEstEmp) {
		this.listaInfEstEmp = listaInfEstEmp;
	}

	
}
