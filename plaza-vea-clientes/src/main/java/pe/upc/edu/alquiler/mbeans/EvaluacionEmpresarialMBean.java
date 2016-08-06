package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.EvaluacionEmpresarial;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="evaluacionEmpresarialMBean")
@RequestScoped
//@SessionScoped
@Component
public class EvaluacionEmpresarialMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EvaluacionEmpresarial evaEmpresarial = new EvaluacionEmpresarial();
	private Evaluacion evaluacion;
	private String cadena;
	private String menSunat;
	private String menInfocorp;
	  	
	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	@Autowired
	private AlquilerService alquilerService;
	
	public void getObtener(){
		System.out.println("Dentro de getObtener");
	}
	
	public void getObtenerEvaluacionEmpresarial(long idEvaluacion) {
		System.out.println("Dentro de obtenerEvaluacionEmpresarial("+idEvaluacion+")");

		try {			
			this.setEvaEmpresarial(this.alquilerService.calcularEvaluacionEmpresarial(idEvaluacion));			
			System.out.println("Número de idEvaluación:"+this.evaEmpresarial.getIdEvaluacion());
			if(evaEmpresarial.getSunat()==null)
				menSunat = "Error en la consulta a SUNAT";
			if(evaEmpresarial.getInformacionCrediticia()==null)
				menInfocorp = "Error en la consulta de la información crediticia";						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String registrarInfEstEmp() {
		
		int idEvaEmp = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			evaEmpresarial.setEvaluacion(evaluacion);
			evaEmpresarial.setFecha(new Date());
			
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:estado");
			evaEmpresarial.setEstado(Integer.parseInt(value));			
			value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:comentario");
			evaEmpresarial.setComentario(value);
			/*
			evaEmpresarial.setComentario(this.cadena);
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:estado");
			evaEmpresarial.setEstado(Integer.parseInt(value));
			value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:comentario");
			evaEmpresarial.setComentario(value);

			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:comentario");
			System.out.println("=V=> Comentario en Evaluación Empresarial (value): "+value);
			System.out.println("=B=> Comentario en Evaluación Empresarial:"+evaEmpresarial.getComentario());
			value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:estado");
			System.out.println("=S=> Comentario en Evaluación Empresarial en String: "+getCadena());
			value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfEstEmp:sinBean");
			System.out.println("=V(sinBean)=> Comentario en Evaluación Empresarial en String: "+value);
			*/
			
			idEvaEmp = this.alquilerService.registrarEvaluacionEmpresarial(evaEmpresarial);
			
			if(idEvaEmp>0){
				if(evaEmpresarial.getEstado()==4)
					this.alquilerService.actualizarEstadoEvaluacion(evaEmpresarial.getEvaluacion().getIdEvaluacion(), evaEmpresarial.getEvaluacion().getSolicitud().getIdSolicitud(),"Pendiente");
				else if(evaEmpresarial.getEstado()==5){
					this.alquilerService.actualizarEstadoEvaluacion(evaEmpresarial.getEvaluacion().getIdEvaluacion(), evaEmpresarial.getEvaluacion().getIdEvaluacion(),"Rechazada");
				}
				context.addMessage(null, new FacesMessage("Información",  "El informe de estado empresarial fue registrado satisfactoriamente") );
			}
			else{
				context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro del informe de estado empresarial") );	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro del informe de estado empresarial") );
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		evaEmpresarial = new EvaluacionEmpresarial();
		menSunat = "";
		menInfocorp = "";
		return "evaluaciones";		
	}
	
	public String cancelaRegInfEstEmp(){

		evaEmpresarial = new EvaluacionEmpresarial();
		evaluacion = new Evaluacion();
		menSunat = "";
		menInfocorp = "";
		
		return "evaluaciones";
	}

	public EvaluacionEmpresarial getEvaEmpresarial() {
		return evaEmpresarial;
	}

	public void setEvaEmpresarial(EvaluacionEmpresarial evaEmpresarial) {
		this.evaEmpresarial = evaEmpresarial;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}	
	
	public void showSelectedEval(ActionEvent event){
		try {
			//this.evaluacion = this.alquilerService.obtenerEvaluacion(idEvaluacion);
			this.evaluacion = (Evaluacion) event.getComponent().getAttributes().get("eval");
			System.out.println("Evaluacion: " + evaluacion.getIdEvaluacion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getMenSunat() {
		return menSunat;
	}

	public void setMenSunat(String menSunat) {
		this.menSunat = menSunat;
	}

	public String getMenInfocorp() {
		return menInfocorp;
	}

	public void setMenInfocorp(String menInfocorp) {
		this.menInfocorp = menInfocorp;
	}

}