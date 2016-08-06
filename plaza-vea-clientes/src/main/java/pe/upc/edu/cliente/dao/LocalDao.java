package pe.upc.edu.cliente.dao;

import java.util.List;

import pe.upc.edu.cliente.model.Local;

public interface LocalDao {
	
	public List<Local> listarLocales() throws Exception;
	
}
