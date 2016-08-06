package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Requisito;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="requisitoMBean")
@RequestScoped
@Component
public class RequisitoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Requisito> listaRequisitos;
	 //private String[] selectedRequisitos;
	private Requisito[] selectedRequisitos;

	private Requisito requisito;
	
	@Autowired
	private AlquilerService alquilerService;
	
	public RequisitoMBean() {
	}
	
	@PostConstruct
	 public void init() {
		try {
			this.listaRequisitos = this.alquilerService.listarRequisitos();
			System.out.println(listaRequisitos.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  }
	
	public int getObtenerRequisitos() {
		try {
			this.listaRequisitos = this.alquilerService.listarRequisitos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public List<Requisito> getListaRequisitos() {
		return listaRequisitos;
	}

	public void setListaRequisitos(List<Requisito> listaRequisitos) {
		this.listaRequisitos = listaRequisitos;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public Requisito[] getSelectedRequisitos() {
		return selectedRequisitos;
	}

	public void setSelectedRequisitos(Requisito[] selectedRequisitos) {
		this.selectedRequisitos = selectedRequisitos;
	}

	
	

}
