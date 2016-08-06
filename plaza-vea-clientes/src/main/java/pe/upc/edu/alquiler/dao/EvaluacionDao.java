package pe.upc.edu.alquiler.dao;

import java.util.Date;
import java.util.List;

import pe.upc.edu.alquiler.model.Evaluacion;




public interface EvaluacionDao {
	
	public List<Evaluacion> listarEvaluaciones() throws Exception;
	
	public List<Evaluacion> listarEvaluacionesVencidas() throws Exception;
	
	public Integer registrarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Integer actualizarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Evaluacion obtenerEvaluacion(long idEvaluacion) throws Exception;
	
	public List<Evaluacion> listarEvaluacionesFiltro(String ruc, String razonSocial, Date fecEvalIni, Date fecEvalFin, String estado) throws Exception;
	
	public Integer actualizarEstadoEvaluacion(long idEvaluacion,long idSolicitud,String estado) throws Exception;
	
	public Evaluacion obtenerEvaluacionSol(long idSolicitud) throws Exception;
}
