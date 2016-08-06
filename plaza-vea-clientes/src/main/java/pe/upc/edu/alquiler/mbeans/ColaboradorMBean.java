package pe.upc.edu.alquiler.mbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.upc.edu.alquiler.model.Colaborador;
import pe.upc.edu.alquiler.service.AlquilerService;

@ManagedBean(name="colaboradorMBean")
@RequestScoped
@Component
public class ColaboradorMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Colaborador colaborador;
	
	
	@Autowired
	private AlquilerService alquilerService;
	
	public ColaboradorMBean() {
	}
	
	public int getObtenerColaborador(long idColaborador) {
		try {
			
			this.colaborador = this.alquilerService.obtenerColaborador(idColaborador);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	

}
