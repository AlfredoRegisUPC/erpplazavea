package pe.upc.edu.alquiler.dao;

import pe.upc.edu.alquiler.model.InfRedSoc;




public interface InfEvalMercDao {
	
	public InfRedSoc obtenerInfEvalMerc(long idEvaluacion) throws Exception;
	
	public Integer registrarInfRedSoc(InfRedSoc infRedSoc) throws Exception;
	
}
