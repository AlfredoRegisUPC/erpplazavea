package pe.upc.edu.alquiler.dao;

import java.util.List;

import pe.upc.edu.alquiler.model.Evaluacion;




public interface EvaluacionDao {
	
	public List<Evaluacion> listarEvaluaciones() throws Exception;
	
	public Integer registrarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Integer actualizarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Evaluacion obtenerEvaluacion(long idEvaluacion) throws Exception;
	
}
