package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Evaluador;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="evaluadorMBean", eager = true)
//@RequestScoped
@SessionScoped
@Component
public class EvaluadorMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Evaluador> listaEvaluadores = new ArrayList<Evaluador>();
	private Evaluador evaluador = new Evaluador();
	
	@Autowired
	private AlquilerService alquilerService;
	
	public EvaluadorMBean() {
	}
	
	@PostConstruct
	 public void init() {
		try {
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
			System.out.println(listaEvaluadores.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	  }
	public int getObtenerEvaluadores() {
		try {
			this.listaEvaluadores = this.alquilerService.listarEvaluadores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public List<Evaluador> getListaEvaluadores() {
		return listaEvaluadores;
	}

	public void setListaEvaluadores(List<Evaluador> listaEvaluadores) {
		this.listaEvaluadores = listaEvaluadores;
	}

	public Evaluador getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}



}
