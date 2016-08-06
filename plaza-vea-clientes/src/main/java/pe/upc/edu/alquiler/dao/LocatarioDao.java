package pe.upc.edu.alquiler.dao;

import pe.upc.edu.alquiler.model.Locatario;




public interface LocatarioDao {
	
	public Locatario obtenerLocatario(String ruc) throws Exception;
	
	public Integer registrarLocatario(Locatario locatario) throws Exception;
	
}
