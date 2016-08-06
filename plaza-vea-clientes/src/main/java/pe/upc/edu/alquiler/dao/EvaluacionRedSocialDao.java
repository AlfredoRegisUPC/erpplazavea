package pe.upc.edu.alquiler.dao;

import java.util.Map;

import pe.upc.edu.alquiler.model.EvaluacionRedSocial;


public interface EvaluacionRedSocialDao {
	
	public EvaluacionRedSocial calcularEvaluacionRedSocial(long idEvaluacion,Map<Double,Object> map) throws Exception;
	
	public EvaluacionRedSocial obtenerEvaluacionRedSocial(long idEvaluacion) throws Exception;
	
	public Integer registrarEvaluacionRedSocial(EvaluacionRedSocial evaRedSocial) throws Exception;
	
	public Integer actualizarEstadoEvaluacionRedSocial(long idEvaluacion, long idSolicitud,String estado) throws Exception;
}
