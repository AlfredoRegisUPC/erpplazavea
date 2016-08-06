package pe.upc.edu.alquiler.dao;

import java.util.Date;
import java.util.List;

import pe.upc.edu.alquiler.model.Evaluador;




public interface EvaluadorDao {
	
	public Evaluador obtenerEvaluador(long idEvaluador) throws Exception;
	
	public Evaluador evaluadorDisponible() throws Exception;
	
	public int actualizarEvaluador(long idEvaluador, String estado, Date fechaProp, Integer cantEva) throws Exception;
	
	public List<Evaluador> listarEvaluadores() throws Exception;
	
}
