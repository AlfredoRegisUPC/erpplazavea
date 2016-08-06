package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.ApiRedSocial;
import pe.upc.edu.alquiler.model.CriterioRedSocial;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.EvaluacionRedSocial;
import pe.upc.edu.alquiler.model.InfRedSoc;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="infRedSocMBean", eager=true)
//@RequestScoped
@SessionScoped
@Component
public class InfRedSocMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private InfRedSoc infRedSoc = new InfRedSoc();
	private List<InfRedSoc> listaInfRedSoc;

	private EvaluacionRedSocial evaRedSocial = new EvaluacionRedSocial();
	private Evaluacion evaluacion;
	private ApiRedSocial apiRedSocial = new ApiRedSocial();	
	
	private Set<CriterioRedSocial> listaCriterioRedSocial;			
	private String numSegFab;
	private Long idEval;
	
	@Autowired
	private AlquilerService alquilerService;
	
	
	
	@PostConstruct
    public void init() {
		this.apiRedSocial = new ApiRedSocial();
	}	
	public InfRedSocMBean() {
		this.apiRedSocial = new ApiRedSocial();
	}
	
	public int calcularScore(Long idEvaluacion){
		listaCriterioRedSocial = new HashSet<CriterioRedSocial>();
		
		FacesContext context = FacesContext.getCurrentInstance();		
	
		try{
			
			Map<Double, Object> mapDatosRedesSociales = new HashMap<Double, Object>();
			Map<Double, Double> mapCriterioDato = new HashMap<Double, Double>();
			
			//Valores Facebook
			if (apiRedSocial.getNumSegFab()!=null) mapCriterioDato.put(1.0,(double)apiRedSocial.getNumSegFab());
			if (apiRedSocial.getComBue()!=null) mapCriterioDato.put(2.0,(double)apiRedSocial.getComBue());
			if (apiRedSocial.getComMal()!=null) mapCriterioDato.put(3.0,(double)apiRedSocial.getComMal()*-1.0);
			if (apiRedSocial.getPubFab()!=null) mapCriterioDato.put(4.0,(double)apiRedSocial.getPubFab());
			mapDatosRedesSociales.put(1.0, mapCriterioDato);						
			
			//Valores Twitter
			mapCriterioDato = new HashMap<Double, Double>();
			if (apiRedSocial.getNumSegTwi()!=null) mapCriterioDato.put(1.0,(double)apiRedSocial.getNumSegTwi());
			if (apiRedSocial.getMenTwiBue()!=null) mapCriterioDato.put(2.0,(double)apiRedSocial.getMenTwiBue());
			if (apiRedSocial.getMenTwiMal()!=null) mapCriterioDato.put(3.0,(double)apiRedSocial.getMenTwiMal()*-1.0);
			if (apiRedSocial.getPubTwi()!=null) mapCriterioDato.put(4.0,(double)apiRedSocial.getPubTwi());
			mapDatosRedesSociales.put(2.0, mapCriterioDato);
			
			//Valores Instagram
			mapCriterioDato = new HashMap<Double, Double>();
			if (apiRedSocial.getNumSegIns()!=null) mapCriterioDato.put(1.0,(double)apiRedSocial.getNumSegIns());
			if (apiRedSocial.getPubIns()!=null) mapCriterioDato.put(4.0,(double)apiRedSocial.getPubIns());
			mapDatosRedesSociales.put(3.0, mapCriterioDato);
			
			//Envío a hacer el Cálculo
			EvaluacionRedSocial evaRedSocialService = new EvaluacionRedSocial();
			evaRedSocialService = this.alquilerService.calcularScore(idEvaluacion, mapDatosRedesSociales);
			
			//evaRedSocial.setNivelAceptacion(evaRedSocialService.getNivelAceptacion());
			if(evaRedSocial.getComentario()!=null || evaRedSocial.getComentario()!="")evaRedSocialService.setComentario(evaRedSocial.getComentario());
			evaRedSocial = evaRedSocialService;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}
		
	public String registrarInfRedSoc(Long idEvaluacion) {			
		
		int idInfRedSoc = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {

			int valorInt = calcularScore(idEvaluacion);
			
			evaRedSocial.setEvaluacion(evaluacion);
			evaRedSocial.setFecha(new Date());
			evaRedSocial.setIdEvaluacion(idEvaluacion);
			
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfMerc:estado");
			evaRedSocial.setEstado(Integer.parseInt(value));
			value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:formInfMerc:comentario");
			evaRedSocial.setComentario(value);
			
			idInfRedSoc  = this.alquilerService.registrarEvaluacionRedSocial(evaRedSocial);
			
			if(idInfRedSoc>0){
				
				if(evaRedSocial.getEstado()==4)
					this.alquilerService.actualizarEstadoEvaluacion(evaRedSocial.getEvaluacion().getIdEvaluacion(), evaRedSocial.getEvaluacion().getSolicitud().getIdSolicitud(),"En Proceso");
				else if(evaRedSocial.getEstado()==5){
					this.alquilerService.actualizarEstadoEvaluacion(evaRedSocial.getEvaluacion().getIdEvaluacion(), evaRedSocial.getEvaluacion().getIdEvaluacion(),"Rechazada");
				}
				
				context.addMessage(null, new FacesMessage("Información",  "El informe de redes sociales fue registrado satisfactoriamente") );
			}
			else{
				context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro del informe de redes sociales") );
			}
			
	 
	        
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro del informe de redes sociales") );
		}
	       context.getExternalContext().getFlash().setKeepMessages(true);
		
		apiRedSocial = new ApiRedSocial();
		evaluacion   = new Evaluacion();
		evaRedSocial = new EvaluacionRedSocial();
			
		return "evaluaciones";
	}
	
	public String cancelaRegInfRedSoc(){
		apiRedSocial = new ApiRedSocial();
		evaluacion   = new Evaluacion();
		evaRedSocial = new EvaluacionRedSocial();
		
		return "evaluaciones";
	}

	public List<InfRedSoc> getListaInfRedSoc() {
		return listaInfRedSoc;
	}

	public void setListaInfRedSoc(List<InfRedSoc> listaInfRedSoc) {
		this.listaInfRedSoc = listaInfRedSoc;
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
			idEval = evaluacion.getIdEvaluacion();
			this.apiRedSocial = new ApiRedSocial();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public EvaluacionRedSocial getEvaRedSocial() {
		return evaRedSocial;
	}

	public void setEvaRedSocial(EvaluacionRedSocial evaRedSocial) {
		this.evaRedSocial = evaRedSocial;
	}

	public ApiRedSocial getApiRedSocial() {
		return apiRedSocial;
	}

	public void setApiRedSocial(ApiRedSocial apiRedSocial) {
		this.apiRedSocial = apiRedSocial;
	}

	public String getNumSegFab() {
		return numSegFab;
	}

	public void setNumSegFab(String numSegFab) {
		this.numSegFab = numSegFab;
	}
	
}
