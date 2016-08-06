package pe.upc.edu.cliente.service;

import java.util.List;

import pe.upc.edu.cliente.model.Local;

public interface ClienteService {
	
	public List<Local> listarLocales() throws Exception;
	
}
