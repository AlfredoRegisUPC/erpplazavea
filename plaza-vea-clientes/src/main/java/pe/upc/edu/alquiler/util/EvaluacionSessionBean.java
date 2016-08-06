package pe.upc.edu.alquiler.util;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.service.AlquilerService;

@Stateless
public class EvaluacionSessionBean implements Serializable{

	@Autowired
	private AlquilerService alquilerService;

	    @PersistenceContext
	    EntityManager em;
	    
	    public List<Evaluacion> listarEvaluaciones() {
	        try {
				return this.alquilerService.listarEvaluaciones();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	        
	    }

	
}
