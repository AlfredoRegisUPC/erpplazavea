package pe.upc.edu.alquiler.dao;

import java.util.List;

import pe.upc.edu.alquiler.model.Locacion;


public interface LocacionDao {
	
	public List<Locacion> listarLocaciones(String tamaño, String ubicacion, String local) throws Exception;
	
}
