package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InformacionCrediticia;
import pe.upc.edu.alquiler.model.Sunat;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="infEstEmpMBean")
@RequestScoped
@Component
public class InfEstEmpMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private InfEstEmp evEmp = new InfEstEmp();
	private InfEstEmp infEstEmp = new InfEstEmp();
	private List<InfEstEmp> listaInfEstEmp;
	
	private Sunat sunat;
	private InformacionCrediticia infCre;
	
	private Evaluacion evaluacion;
	
	@Autowired
	private AlquilerService alquilerService;
	
	public InfEstEmpMBean() {
	}
	
	public int getObtenerInfEstEmp(long idEvaluacion) {
		try {
			this.infEstEmp = this.alquilerService.obtenerInfEstEmp(idEvaluacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public String registrarInfEstEmp() {
		int idInfEstEmp = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			
			infEstEmp.setEvaluacion(evaluacion);
			infEstEmp.setFecha(new Date());
			idInfEstEmp = this.alquilerService.registrarInfEstEmp(infEstEmp);
			
			if(idInfEstEmp>0){
				if(infEstEmp.getEstado().equals("Aprobado"))
					this.alquilerService.actualizarEstadoEvaluacion(infEstEmp.getEvaluacion().getIdEvaluacion(), infEstEmp.getEvaluacion().getSolicitud().getIdSolicitud(),"Pendiente");
				else if(infEstEmp.getEstado().equals("Rechazado")){
					this.alquilerService.actualizarEstadoEvaluacion(infEstEmp.getEvaluacion().getIdEvaluacion(), infEstEmp.getEvaluacion().getSolicitud().getIdSolicitud(),"Rechazada");
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
		
		sunat = new Sunat();
		infCre = new InformacionCrediticia();
		evaluacion = new Evaluacion();
		infEstEmp = new InfEstEmp();
		
		return "evaluaciones";
		
		
	}
	
	public String cancelaRegInfEstEmp(){
		sunat = new Sunat();
		infCre = new InformacionCrediticia();
		evaluacion = new Evaluacion();
		infEstEmp = new InfEstEmp();
		
		return "evaluaciones";
	}
	
	public int getObtenerDatosEmpresa(long idEvaluacion){		
		try {
			this.infEstEmp = this.alquilerService.obtenerInfEstEmp(idEvaluacion);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return 1;
	}
	
	public void getObtenerSunatInfocorp(){
		/*
		sunat = new Sunat();
		sunat.setActividadEconomica("ACTIVIDAD ECONÓMICA");
		sunat.setCondicion("ACTIVO");
		sunat.setRepresentanteLegal("REPRESENTANTE LEGAL");
		sunat.setSistemaEmision("MANUAL");
		
		OmisionTributaria omiTri = new OmisionTributaria();
		omiTri.setDiasAtraso(1);
		omiTri.setFechaVencimiento(new Date());
		omiTri.setImporte(5000.00);
		omiTri.setPeriodo("MENSUAL");

		OmisionTributaria omiTri2 = new OmisionTributaria();
		omiTri2.setDiasAtraso(1);
		omiTri2.setFechaVencimiento(new Date());
		omiTri2.setImporte(3000.00);
		omiTri2.setPeriodo("MENSUAL");
		
		List<OmisionTributaria> lOmiTri = new ArrayList<OmisionTributaria>();
		lOmiTri.add(omiTri);
		lOmiTri.add(omiTri2);
				
		sunat.setDetalleOmisiones(lOmiTri);
		
		infCre = new InformacionCrediticia();
		infCre.setCalificacion("Bueno");
		infCre.setCalificacionAbr("Bno.");
		infCre.setDeudaAcumulada(8000.00);
		infCre.setScore(10.0);
		
		infEstEmp.setResultado("APTO");
		*/
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

	public Sunat getSunat() {
		return sunat;
	}

	public void setSunat(Sunat sunat) {
		this.sunat = sunat;
	}

	public InformacionCrediticia getInfCre() {
		return infCre;
	}

	public void setInfCre(InformacionCrediticia infCre) {
		this.infCre = infCre;
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
	
}
