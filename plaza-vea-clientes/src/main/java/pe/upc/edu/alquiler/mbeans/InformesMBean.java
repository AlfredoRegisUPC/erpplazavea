package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfEvalMerc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="informesMBean")
@RequestScoped
@Component
//@ViewScoped
public class InformesMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private InfEstEmp infEstEmp;
	private InfSanciones infSanciones;
	private InfEvalMerc infEvalMerc;
	
	private Evaluacion evaluacion;
	@Autowired
	private AlquilerService alquilerService;
	
	public InformesMBean() {
	}
	
	/*@PostConstruct
    public void init() {
		try {
			this.infEstEmp = this.alquilerService.obtenerInfEstEmp(evaluacion.getIdEvaluacion());
			this.infSanciones = this.alquilerService.obtenerInfSanciones(evaluacion.getIdEvaluacion());
			this.infEvalMerc = this.alquilerService.obtenerInfEvalMerc(evaluacion.getIdEvaluacion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(infEstEmp.getEstado());
		System.out.println(infSanciones.getEstado());
		System.out.println(infEvalMerc.getEstado());
		
    }*/
	
	public void getObtenerInformes(long idEvaluacion) {
		try {
			
			this.infEstEmp = this.alquilerService.obtenerInfEstEmp(idEvaluacion);
			this.infSanciones = this.alquilerService.obtenerInfSanciones(idEvaluacion);
			this.infEvalMerc = this.alquilerService.obtenerInfEvalMerc(idEvaluacion);
			
			InformesMBean infMBean = new InformesMBean();
			infMBean.setInfEstEmp(infEstEmp);
			infMBean.setInfSanciones(infSanciones);
			infMBean.setInfEvalMerc(infEvalMerc);
			
			Map<String,Object> options = new HashMap<String, Object>();
	        options.put("resizable", true);
	        options.put("draggable", true);
	        options.put("modal", true);
	        RequestContext.getCurrentInstance().openDialog("informes", options, null);
	    
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public InfEstEmp getInfEstEmp() {
		return infEstEmp;
	}

	public void setInfEstEmp(InfEstEmp infEstEmp) {
		this.infEstEmp = infEstEmp;
	}

	public InfSanciones getInfSanciones() {
		return infSanciones;
	}

	public void setInfSanciones(InfSanciones infSanciones) {
		this.infSanciones = infSanciones;
	}

	public InfEvalMerc getInfEvalMerc() {
		return infEvalMerc;
	}

	public void setInfEvalMerc(InfEvalMerc infEvalMerc) {
		this.infEvalMerc = infEvalMerc;
	}


	

}
