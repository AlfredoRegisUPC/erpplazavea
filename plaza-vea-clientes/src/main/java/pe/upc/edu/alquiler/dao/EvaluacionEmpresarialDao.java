package pe.upc.edu.alquiler.dao;

import pe.upc.edu.alquiler.model.EvaluacionEmpresarial;




public interface EvaluacionEmpresarialDao {
	
	public EvaluacionEmpresarial calcularEvaluacionEmpresarial(long idEvaluacion) throws Exception;
	
	public EvaluacionEmpresarial obtenerEvaluacionEmpresarial(long idEvaluacion) throws Exception;
	
	public Integer registrarEvaluacionEmpresarial(EvaluacionEmpresarial evaEmpresarial) throws Exception;
	
	public Integer actualizarEstadoEvaluacionEmpresarial(long idEvaluacion, long idSolicitud,String estado) throws Exception;
}
