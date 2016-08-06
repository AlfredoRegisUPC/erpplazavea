package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.EntregaDoc;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.EvaluacionEmpresarial;
import pe.upc.edu.alquiler.model.EvaluacionRedSocial;
import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.model.Requisito;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="solicitudMBean")
@RequestScoped
@Component
public class SolicitudMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Solicitud> listaSolicitudes;
	private Solicitud solicitud= new Solicitud();;
	
	@PostConstruct
    public void init() {
		solicitud = new Solicitud();
	}
	
	private Locatario locatario = new Locatario();
	//private String[] selectedRequisitos = null;
	private Requisito[] selectedRequisitos = null;
	private Locacion locacion = new Locacion();
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private Boolean disabled = true;
	
	private Date solFecIni;
	private Date solFecFin;
	private String solRuc;
	private String solRazSoc;
	private String solEstado;
	
	
	private Evaluacion evaluacion;
	private EvaluacionEmpresarial infEstEmp;
	private EvaluacionRedSocial infRedSoc;
	
	private String menEval;
	private String menInfEstEmp;
	private String menInfRedSoc;
	
	@Autowired
	private AlquilerService alquilerService;
	
	public SolicitudMBean() {
		
	}
	
	public int getObtenerSolicitudes() {
		try {
			//this.listaSolicitudes = this.alquilerService.listarSolicitudes();
			this.listaSolicitudes = this.alquilerService.listarSolicitudesFiltro(solRuc, solRazSoc, solFecIni, solFecFin, solEstado);
			solRuc = null;
			solRazSoc = null;
			solFecIni = null;
			solFecFin = null;
			solEstado = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	
	public String registraSolicitud() {
		solicitud= new Solicitud();
		int idSolicitud = 0;
		int idLocatario = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		if(selectedRequisitos.length!=4){
			context.addMessage(null, new FacesMessage("Error",  "Debe seleccionar toda la documentación necesaria") );
			return "solicitud";
		}
		try {
			if(locatario!=null && solicitud!=null && locacion !=null && selectedRequisitos!=null){
			
			if(locatario.getIdLocatario()==null){			
				idLocatario = this.alquilerService.registrarLocatario(locatario);
				solicitud.setLocatario(this.alquilerService.obtenerLocatario(locatario.getRuc()));
			}
			else{
				solicitud.setLocatario(locatario);
				idLocatario = Integer.parseInt(String.valueOf(locatario.getIdLocatario()));
			}
			solicitud.setLocacion(locacion);
			solicitud.setEstado("Registrada");
			solicitud.setFecha(new Date());
			solicitud.setUsuarioCreacion("RECEPCIONISTA");
			
			//
			solicitud.setSolicitante("Solicitante");
			//
			
			idSolicitud =  this.alquilerService.registrarSolicitud(solicitud);
			if(idSolicitud > 0){
				//Al registrar una solicitud se registra la entrega de documentos
				solicitud.setIdSolicitud((long) idSolicitud);
				for (int i = 0; i < selectedRequisitos.length; i++) {
					EntregaDoc reqEntrega = new EntregaDoc();
					reqEntrega.setRequisito(selectedRequisitos[i]);
					reqEntrega.setSolicitud(solicitud);
					reqEntrega.setFechaEntrega(new Date());
					reqEntrega.setEstado("Entregado");
					this.alquilerService.registrarEntregaDoc(reqEntrega);
				}				
				
				if(locatario.getRuc().length()>=12){
					context.addMessage(null, new FacesMessage("Información",  "En número de RUC solo debe contener 11 dígitos") );
					return null;
				}

				if(idSolicitud>0 && idLocatario>0)
					context.addMessage(null, new FacesMessage("Información",  "La solicitud fue registrada satisfactoriamente") );
				else
					context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro de la solicitud") );
				
		        context.getExternalContext().getFlash().setKeepMessages(true);
		  
		        
		        locatario = new Locatario();
				locacion = new Locacion();
				solicitud = new Solicitud();
				selectedRequisitos = null;

			}
			}
			else{
			context.addMessage(null, new FacesMessage("Información",  "Los datos están incompletos") );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "solicitudes";
	}
	
	public String cancelaSolicitud(){
		if(locatario!=null || solicitud!=null || locacion !=null || selectedRequisitos!=null){
		locatario = new Locatario();
		locacion = new Locacion();
		solicitud = new Solicitud();
	    selectedRequisitos = null;
		}
		return "solicitudes";
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
	public List<Solicitud> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<Solicitud> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", detail);
        FacesContext.getCurrentInstance().addMessage("Información", message);
    }

	
	
	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}



	public Requisito[] getSelectedRequisitos() {
		return selectedRequisitos;
	}

	public void setSelectedRequisitos(Requisito[] selectedRequisitos) {
		this.selectedRequisitos = selectedRequisitos;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}
	
	public void showSelectedSol(ActionEvent event){
		try {
			//this.evaluacion = this.alquilerService.obtenerEvaluacion(idEvaluacion);
			this.solicitud = (Solicitud) event.getComponent().getAttributes().get("sol");
			System.out.println("Solicitud: " + solicitud.getIdSolicitud());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showSolicitud(ActionEvent event){
		try {
			menEval = "";
			menInfEstEmp = "";
			menInfRedSoc = "";
			
			this.solicitud = (Solicitud) event.getComponent().getAttributes().get("sol");
			System.out.println("Solicitud: " + solicitud.getIdSolicitud());
			this.evaluacion = this.alquilerService.obtenerEvaluacionSol(solicitud.getIdSolicitud());
			if(evaluacion==null){
				menEval = "La solicitud no tiene evaluación registrada";
				menInfEstEmp = "La solicitud no cuenta con Informe de Estado Empresarial registrado";
				menInfRedSoc = "La solicitud no cuenta Informe de Redes Sociales registrado";
			}
			else{
				this.infEstEmp = this.alquilerService.obtenerEvaluacionEmpresarial(evaluacion.getIdEvaluacion());
				if(infEstEmp==null)
					menInfEstEmp = "La solicitud no tiene informe de estado empresarial registrado";
				else{
					infEstEmp.setCodInfEstEmp(solicitud.getCodSolicitud().replace("SOL", "IEE"));
					infEstEmp.setFecha(solicitud.getFecha());
				}
				
				this.infRedSoc = this.alquilerService.obtenerEvaluacionRedSocial(evaluacion.getIdEvaluacion());
				if(infRedSoc==null)
					menInfRedSoc = "La solicitud no tiene informe de redes sociales registrado";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String goEvalSol() {
			RequestContext.getCurrentInstance().update("formRegEval:evaluadores");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("formRegEval");
		   return "regEvaluacion?faces-redirect=true";
	   }
	
	public String goConsSol() {

	   return "conSolicitud";
   }
	

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
	public void onRowSelect(SelectEvent event) {
		if(solicitud.getEstado().equals("Registrada"))
			disabled = true;
		else
			disabled = false;
	}

	public String getSolRuc() {
		return solRuc;
	}

	public Date getSolFecIni() {
		return solFecIni;
	}

	public void setSolFecIni(Date solFecIni) {
		this.solFecIni = solFecIni;
	}

	public Date getSolFecFin() {
		return solFecFin;
	}

	public void setSolFecFin(Date solFecFin) {
		this.solFecFin = solFecFin;
	}

	public void setSolRuc(String solRuc) {
		this.solRuc = solRuc;
	}

	public String getSolRazSoc() {
		return solRazSoc;
	}

	public void setSolRazSoc(String solRazSoc) {
		this.solRazSoc = solRazSoc;
	}

	public String getSolEstado() {
		return solEstado;
	}

	public void setSolEstado(String solEstado) {
		this.solEstado = solEstado;
	}
	
	
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public EvaluacionEmpresarial getInfEstEmp() {
		return infEstEmp;
	}

	public void setInfEstEmp(EvaluacionEmpresarial infEstEmp) {
		this.infEstEmp = infEstEmp;
	}

	public EvaluacionRedSocial getInfRedSoc() {
		return infRedSoc;
	}

	public void setInfRedSoc(EvaluacionRedSocial infRedSoc) {
		this.infRedSoc = infRedSoc;
	}

	public String cerrarConSol(){
		
		solicitud = new Solicitud();
		evaluacion = new Evaluacion();
		infEstEmp = new EvaluacionEmpresarial();
		infRedSoc = new EvaluacionRedSocial();
		
		menEval = "";
		menInfEstEmp = "";
		menInfRedSoc = "";
		
		return "solicitudes";
	}

	public String getMenEval() {
		return menEval;
	}

	public void setMenEval(String menEval) {
		this.menEval = menEval;
	}

	public String getMenInfEstEmp() {
		return menInfEstEmp;
	}

	public void setMenInfEstEmp(String menInfEstEmp) {
		this.menInfEstEmp = menInfEstEmp;
	}

	public String getMenInfRedSoc() {
		return menInfRedSoc;
	}

	public void setMenInfRedSoc(String menInfRedSoc) {
		this.menInfRedSoc = menInfRedSoc;
	}
	
	

}
