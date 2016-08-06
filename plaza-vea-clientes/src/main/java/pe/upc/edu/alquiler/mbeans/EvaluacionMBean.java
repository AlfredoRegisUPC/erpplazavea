package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.Evaluador;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfRedSoc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.alquiler.service.AlquilerService;
import pe.upc.edu.alquiler.util.EnvioMail;

@ManagedBean(name="evaluacionMBean")
@RequestScoped
@Component
public class EvaluacionMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Evaluacion> listaEvaluaciones;
	private Evaluacion evaluacion = new Evaluacion();
	
	@Autowired
	private AlquilerService alquilerService;
	
	private InfEstEmp infEstEmp;
	private InfSanciones infSanciones;
	private InfRedSoc infEvalMerc;
	
	private Boolean disabled = true;
	
	private Solicitud solicitud;
	private Evaluador evaluador;
	private List<Evaluador> listaEvaluadores;
	
	private String mensaje = "";
	
	private Date evalFecIni;
	private Date evalFecFin;
	private String evalRuc;
	private String evalRazSoc;
	private String evalEstado;
	
	private long idEvaluador;
	//@EJB
    //private EvaluacionSessionBean evaluacionSessionBean;
	
	public EvaluacionMBean() {
		try {
			listaEvaluadores = new ArrayList<Evaluador>();
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
			System.out.println(listaEvaluadores.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@PostConstruct
	private void init() {
		// TODO Auto-generated method stub
		try {
			listaEvaluadores = new ArrayList<Evaluador>();
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
			System.out.println(listaEvaluadores.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public int getObtenerEvaluaciones() {
		try {
			//this.listaEvaluaciones = this.alquilerService.listarEvaluaciones();
			
			//Inicio Evaluaciones vencidas....
			List<Evaluacion> evaVencidas = new ArrayList<Evaluacion>();
			evaVencidas = this.alquilerService.listarEvaluacionesVencidas();
			String mensajeMail = "";
			if(evaVencidas.size()>0){
				for (int i = 0; i < evaVencidas.size(); i++) {
					evaVencidas.get(i).setEstado("Vencida");
					this.alquilerService.actualizarEvaluacion(evaVencidas.get(i));
					mensajeMail += "\n" + "\n"+
							"Código de Solicitud: " + evaVencidas.get(i).getSolicitud().getCodSolicitud()+ "\n" + "\n" + 
							"Evaluador: " + evaVencidas.get(i).getEvaluador().getColaborador().getApellidos()+ ", " + evaVencidas.get(i).getEvaluador().getColaborador().getNombres()+"\n" + "\n" +
							"Fecha Límite: " + evaVencidas.get(i).getFechaProp()+ "\n" + "\n";
				
				}
				
				
				EnvioMail e = new EnvioMail();
				e.envioMailVencidas(mensajeMail);
			}
			
			
			//Fin Evaluaciones vencidas.....
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			this.listaEvaluaciones = this.alquilerService.listarEvaluacionesFiltro(evalRuc, evalRazSoc, evalFecIni, evalFecFin, evalEstado);
			evalRuc = null;
			evalRazSoc = null;
			evalFecIni = null;
			evalFecFin = null;
			evalEstado = null;
			
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
			//this.listaEvaluaciones = evaluacionSessionBean.listarEvaluaciones();
			System.out.println(listaEvaluaciones.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public void showSelectedEval(ActionEvent event){
		try {
			//this.evaluacion = this.alquilerService.obtenerEvaluacion(idEvaluacion);
			this.evaluacion = (Evaluacion) event.getComponent().getAttributes().get("eval");
			System.out.println("Evaluacion: " + evaluacion.getIdEvaluacion());
			idEvaluador = evaluacion.getEvaluador().getIdEvaluador();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delSelectedEval(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			//this.evaluacion = this.alquilerService.obtenerEvaluacion(idEvaluacion);
			this.evaluacion = (Evaluacion) event.getComponent().getAttributes().get("eval");
			System.out.println("Evaluacion: " + evaluacion.getIdEvaluacion());
			int actEval = 0;
			    evaluacion.setObservacion("La evaluacion ha sido eliminada");
				evaluacion.setEstado("Eliminada");
			
				actEval =  this.alquilerService.actualizarEvaluacion(evaluacion);
				if(actEval > 0){
					this.alquilerService.actualizarEvaluador(evaluacion.getEvaluador().getIdEvaluador(), "Disponible", null, (evaluacion.getEvaluador().getCantidadEval()-1));
					this.alquilerService.actualizarSolicitud(evaluacion.getSolicitud().getIdSolicitud(), "Eliminada");
					context.addMessage(null, new FacesMessage("Información",  "La evaluación fue eliminada satisfactoriamente") );
				}
				else
					context.addMessage(null, new FacesMessage("Error",  "La evaluación presento problemas al ser eliminada") );
				
				context.getExternalContext().getFlash().setKeepMessages(true);
				  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public String goListPage() {
		   return "evaluaciones";
	   }

	   public String goEditPage() {
		   return "evaluacion";
	   }
	   
	   public String goInfEstEmpPage() {
		   //return "infEstEmp";
		   return "infEstEmp?faces-redirect=true";
	   }
	   public String goInfRedSoc(){
			//RequestContext.getCurrentInstance().update("formRegEval:evaluadores");
			//RequestContext context = RequestContext.getCurrentInstance();
			//context.update("formRegEval");
		   return "infMerc?faces-redirect=true";		   
		   //return "infMerc";
	   }
	
	   public void getObtenerInformes() {
			try {				
				
				this.infEstEmp = this.alquilerService.obtenerInfEstEmp(evaluacion.getIdEvaluacion());
				//this.infSanciones = this.alquilerService.obtenerInfSanciones(evaluacion.getIdEvaluacion());
				this.infEvalMerc = this.alquilerService.obtenerInfEvalMerc(evaluacion.getIdEvaluacion());
				
				InformesMBean infMBean = new InformesMBean();
				infMBean.setInfEstEmp(infEstEmp);
				//infMBean.setInfSanciones(infSanciones);
				infMBean.setInfRedSoc(infEvalMerc);
				
				Map<String,Object> options = new HashMap<String, Object>();
		        options.put("resizable", true);
		        options.put("draggable", true);
		        options.put("modal", true);
		        RequestContext.getCurrentInstance().openDialog("informes", options, null);
		    
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   public void onCarChosen(SelectEvent event) {
	        Evaluacion eval = (Evaluacion) event.getObject();
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + eval.getIdEvaluacion());
	         
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	public String actualizarEvaluacion() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		int actEval = 0;
		try {
			actEval =  this.alquilerService.actualizarEvaluacion(this.evaluacion);
			if(actEval > 0){
					if(this.evaluacion.getEstado().equals("Aprobada")||this.evaluacion.getEstado().equals("Rechazada")){
						//Si se actualiza la evaluación se cambia el estado del evaluador a disponible y el estado de la solicitud
						this.alquilerService.actualizarEvaluador(this.evaluacion.getEvaluador().getIdEvaluador(), "Disponible", null, (this.evaluacion.getEvaluador().getCantidadEval()-1));
						this.alquilerService.actualizarSolicitud(this.evaluacion.getSolicitud().getIdSolicitud(), this.evaluacion.getEstado());
					evaluacion = this.alquilerService.obtenerEvaluacion(this.evaluacion.getIdEvaluacion());
					//Mandar correo.
					EnvioMail e = new EnvioMail();
					e.envioMail(evaluacion.getSolicitud().getLocatario().getRazonSocial(),
								evaluacion.getSolicitud().getLocatario().getCorreo(),
								evaluacion.getEstado(),
								evaluacion.getSolicitud().getLocatario().getRepresentante(),
								evaluacion.getSolicitud().getLocatario().getRuc(),
								evaluacion.getFechaModif());
					}
					else{
						this.alquilerService.actualizarEvaluador(idEvaluador, "Disponible", null, (evaluacion.getEvaluador().getCantidadEval()-1));
						this.alquilerService.actualizarEvaluador(this.evaluacion.getEvaluador().getIdEvaluador(), "Disponible", null, (this.evaluacion.getEvaluador().getCantidadEval()+1));
					}
					
					context.addMessage(null, new FacesMessage("Información",  "La evaluación fue actualizada satisfactoriamente") );
			}
			else
				context.addMessage(null, new FacesMessage("Error",  "La evaluación presento problemas al ser actualizada") );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		  context.getExternalContext().getFlash().setKeepMessages(true);
		  
		return "evaluaciones";
	}
	
	public String cancelaEvaluacion(){
		return "evaluaciones";
	}

	public String cancelaRegEvaluacion(){
		return "solicitudes";
	}
	
	public List<Evaluacion> getListaEvaluaciones() {
		return listaEvaluaciones;
	}

	public void setListaEvaluaciones(List<Evaluacion> listaEvaluaciones) {
		this.listaEvaluaciones = listaEvaluaciones;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
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

	public InfRedSoc getInfEvalMerc() {
		return infEvalMerc;
	}

	public void setInfEvalMerc(InfRedSoc infEvalMerc) {
		this.infEvalMerc = infEvalMerc;
	}
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	
	public void onRowSelect(SelectEvent event) {
		if(evaluacion.getEstado().equals("Aprobada")||evaluacion.getEstado().equals("Rechazada"))
			disabled = false;
		else
			disabled = true;
	}
	
	
	public String registrarEvaluacion(){
		FacesContext context = FacesContext.getCurrentInstance();
		//Al registrar una solicitud se autoregistra una evaluación con un evaluador disponible 
		int idEvaluacion = 0;
		//Evaluacion evaluacion = new Evaluacion();
		//evaluacion.setIdSolicitud((long) idSolicitud);
		evaluacion.setSolicitud(solicitud);
		evaluacion.setEvaluador(evaluador);
/*		Evaluador evaluador = this.alquilerService.evaluadorDisponible();
		//evaluacion.setIdEvaluador(evaluador.getIdEvaluador());
		
		*/
		//Cambiar fecha propuesta según RN*****
				Calendar fechaProp = new GregorianCalendar();			
				fechaProp.add(Calendar.DATE, 7);
				evaluacion.setFechaProp(new Date(fechaProp.getTimeInMillis()));
		//Cambiar estado inicial según RN
		evaluacion.setEstado("Registrada");
		evaluacion.setFechaCreacion(new Date());
		evaluacion.setUsuarioCreacion("EVALUADOR");
		evaluacion.setCodEvaluacion(solicitud.getCodSolicitud().replace("SOL", "EVA"));
		
		//System.out.println("====> Contenido de Observación:"+evaluacion.getObservacion());
		
		//Completar datos de evaluación
		try {
			
		idEvaluacion = this.alquilerService.registrarEvaluacion(evaluacion);
		
		if(idEvaluacion>0){
			
			this.alquilerService.actualizarSolicitud(solicitud.getIdSolicitud(), "Pendiente");
			
			Integer cantEva = evaluacion.getEvaluador().getCantidadEval()+1;
			String estEva = "Disponible";
			if(cantEva >= 5)
				estEva = "No Disponible";
			
			this.alquilerService.actualizarEvaluador(evaluacion.getEvaluador().getIdEvaluador(), estEva, new Date(fechaProp.getTimeInMillis()), cantEva);
			
			context.addMessage(null, new FacesMessage("Información",  "La evaluación de solicitud fue registrada satisfactoriamente") );
		}
		else{
			context.addMessage(null, new FacesMessage("Error",  "Se presentaron problemas en el registro de la evaluación de la solicitud") );
		}
		
        context.getExternalContext().getFlash().setKeepMessages(true);
        
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		evaluador =  new Evaluador();
		evaluacion = new Evaluacion();
		try {
			listaEvaluadores = this.alquilerService.listarEvaluadores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "solicitudes";
		
	}
	
	public void getObtenerFecPro() {
		try {
			evaluacion = new Evaluacion();
			System.out.println("Evaluador: " + evaluador);	
			Calendar fechaProp = new GregorianCalendar();			
			fechaProp.add(Calendar.DATE, 7);
			evaluacion.setFechaProp(new Date(fechaProp.getTimeInMillis()));
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getObtenerEvaluadores() {
		try {
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
			
			if(this.listaEvaluadores.size()<=0)
				this.mensaje = "No hay evaluadores disponibles que cumplan con los datos ingresados";
			else
				this.mensaje = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public List<Evaluador> getListaEvaluadores() {
		return listaEvaluadores;
	}

	public void setListaEvaluadores(List<Evaluador> listaEvaluadores) {
		this.listaEvaluadores = listaEvaluadores;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Evaluador getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}

	public Date getEvalFecIni() {
		return evalFecIni;
	}

	public void setEvalFecIni(Date evalFecIni) {
		this.evalFecIni = evalFecIni;
	}

	public Date getEvalFecFin() {
		return evalFecFin;
	}

	public void setEvalFecFin(Date evalFecFin) {
		this.evalFecFin = evalFecFin;
	}

	public String getEvalRuc() {
		return evalRuc;
	}

	public void setEvalRuc(String evalRuc) {
		this.evalRuc = evalRuc;
	}

	public String getEvalRazSoc() {
		return evalRazSoc;
	}

	public void setEvalRazSoc(String evalRazSoc) {
		this.evalRazSoc = evalRazSoc;
	}

	public String getEvalEstado() {
		return evalEstado;
	}

	public void setEvalEstado(String evalEstado) {
		this.evalEstado = evalEstado;
	}
	
	
	

}