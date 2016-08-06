package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.alquiler.service.AlquilerService;
import pe.upc.edu.cliente.model.Local;

@ManagedBean(name="locacionMBean" , eager = true)
//@RequestScoped
@SessionScoped
@Component
public class LocacionMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Locacion> listaLocaciones;
	private Locacion locacion = new Locacion();
	private List<Local> listaLocales;
	private String tipo;
	private String ubicacion;
	private Local local = new Local();
	
	private String mensaje = "";
	
	@Autowired
	private AlquilerService alquilerService;
	
	public LocacionMBean() {
	}
	
	public void getObtenerLocaciones() {
		try {
			System.out.println("Tipo: " + tipo);	
			System.out.println("Ubicacion: " + ubicacion);
			System.out.println("Local: " + local.getIdLocal());
			this.listaLocaciones = this.alquilerService.listarLocaciones(tipo, ubicacion, String.valueOf(local.getIdLocal()));
			
			if(this.listaLocaciones.size()<=0)
				this.mensaje = "No hay locaciones disponibles que cumplan con los datos ingresados";
			else
				this.mensaje = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getObtenerIdLocacion()
	{
		System.out.println(locacion.getIdLocacion());;
	}	

	public List<Locacion> getListaLocaciones() {
		return listaLocaciones;
	}

	public void setListaLocaciones(List<Locacion> listaLocaciones) {
		this.listaLocaciones = listaLocaciones;
	}

	

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public void setListaLocales(List<Local> listaLocales) {
		this.listaLocales = listaLocales;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Locacion getLocacion() {
		return locacion;
	}

	public void setLocacion(Locacion locacion) {
		this.locacion = locacion;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	

}
