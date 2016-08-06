package pe.upc.edu.alquiler.service;

import java.util.Date;
import java.util.List;

import pe.upc.edu.alquiler.model.Colaborador;
import pe.upc.edu.alquiler.model.EntregaDoc;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.Evaluador;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfEvalMerc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.model.Requisito;
import pe.upc.edu.alquiler.model.Solicitud;


public interface AlquilerService {
	
	public Colaborador obtenerColaborador(long idColaborador) throws Exception;
	
	public Locatario obtenerLocatario(String ruc) throws Exception;
	
	public Integer registrarLocatario(Locatario locatario) throws Exception;
	
	public List<Locacion> listarLocaciones(String tamaño, String ubicacion, String local) throws Exception;
	
	public List<Requisito> listarRequisitos() throws Exception;
	
	public Integer registrarEntregaDoc(EntregaDoc requisito) throws Exception;
	
	public List<Evaluacion> listarEvaluaciones() throws Exception;
	
	public Integer registrarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Integer actualizarEvaluacion(Evaluacion evaluacion) throws Exception;
	
	public Evaluador obtenerEvaluador(long idEvaluador) throws Exception;
	
	public Evaluacion obtenerEvaluacion(long idEvaluacion) throws Exception;
	
	public Evaluador evaluadorDisponible() throws Exception;
	
	public Integer actualizarEvaluador(long idEvaluador, String estado, Date fechaProp, Integer cantEva) throws Exception;
	
	public InfEstEmp obtenerInfEstEmp(long idEvaluacion) throws Exception;
	
	public InfEvalMerc obtenerInfEvalMerc(long idEvaluacion) throws Exception;
	
	public InfSanciones obtenerInfSanciones(long idEvaluacion) throws Exception;
	
	public List<Solicitud> listarSolicitudes() throws Exception;
	
	public Integer registrarSolicitud(Solicitud solicitud) throws Exception;
	
	public Integer actualizarSolicitud(long idSolicitud, String estado) throws Exception;
	
	public List<Evaluador> listarEvaluadores() throws Exception;
}
