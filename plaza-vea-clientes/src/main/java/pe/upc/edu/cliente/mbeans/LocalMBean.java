package pe.upc.edu.cliente.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.cliente.model.Local;
import pe.upc.edu.cliente.service.ClienteService;

@ManagedBean(name="localMBean", eager = true)
//@RequestScoped
@SessionScoped
@Component
public class LocalMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Local> listaLocales = new ArrayList<Local>();
	private Local local = new Local();
	
	@Autowired
	private ClienteService clienteService;
	
	public LocalMBean() {
	}
	
	@PostConstruct
	 public void init() {
		try {
			this.listaLocales = this.clienteService.listarLocales();
			System.out.println(listaLocales.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  }
	public int getObtenerLocales() {
		try {
			this.listaLocales = this.clienteService.listarLocales();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public void setListaLocales(List<Local> listaLocales) {
		this.listaLocales = listaLocales;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}



}
