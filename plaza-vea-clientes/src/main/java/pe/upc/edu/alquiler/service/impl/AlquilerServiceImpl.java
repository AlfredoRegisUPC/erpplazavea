package pe.upc.edu.alquiler.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.edu.alquiler.dao.ColaboradorDao;
import pe.upc.edu.alquiler.dao.EntregaDocDao;
import pe.upc.edu.alquiler.dao.EvaluacionDao;
import pe.upc.edu.alquiler.dao.EvaluacionEmpresarialDao;
import pe.upc.edu.alquiler.dao.EvaluacionRedSocialDao;
import pe.upc.edu.alquiler.dao.EvaluadorDao;
import pe.upc.edu.alquiler.dao.InfEstEmpDao;
import pe.upc.edu.alquiler.dao.InfEvalMercDao;
import pe.upc.edu.alquiler.dao.InfSancionesDao;
import pe.upc.edu.alquiler.dao.LocacionDao;
import pe.upc.edu.alquiler.dao.LocatarioDao;
import pe.upc.edu.alquiler.dao.RequisitoDao;
import pe.upc.edu.alquiler.dao.SolicitudDao;
import pe.upc.edu.alquiler.model.Colaborador;
import pe.upc.edu.alquiler.model.EntregaDoc;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.EvaluacionEmpresarial;
import pe.upc.edu.alquiler.model.EvaluacionRedSocial;
import pe.upc.edu.alquiler.model.Evaluador;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfRedSoc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.model.Requisito;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.alquiler.service.AlquilerService;

@Service("alquilerService")
public class AlquilerServiceImpl implements AlquilerService {
	
	@Autowired
	private ColaboradorDao colaboradorDao;
	@Autowired
	private LocatarioDao locatarioDao;
	@Autowired
	private LocacionDao locacionDao;
	@Autowired
	private RequisitoDao requisitoDao;
	@Autowired
	private EntregaDocDao entregaDocDao;
	@Autowired
	private EvaluacionDao evaluacionDao;
	@Autowired
	private EvaluadorDao evaluadorDao;
	@Autowired
	private InfEstEmpDao infEstEmpDao;
	@Autowired
	private InfSancionesDao	infSancionesDao;
	@Autowired
	private InfEvalMercDao infEvalMercDao;
	@Autowired
	private SolicitudDao solicitudDao;	
	@Autowired
	private EvaluacionEmpresarialDao evaEmpDao;
	@Autowired
	private EvaluacionRedSocialDao evaRedSocDao;
	

	@Override
	public Colaborador obtenerColaborador(long idColaborador)
			throws Exception {
		// TODO Auto-generated method stub
		return colaboradorDao.obtenerColaborador(idColaborador);
	}

	@Override
	public Locatario obtenerLocatario(String ruc) throws Exception {
		// TODO Auto-generated method stub
		return locatarioDao.obtenerLocatario(ruc);
	}

	@Override
	public Integer registrarLocatario(Locatario locatario) throws Exception {
		// TODO Auto-generated method stub
		return locatarioDao.registrarLocatario(locatario);
	}

	@Override
	public List<Locacion> listarLocaciones(String tamaño, String ubicacion,
			String local) throws Exception {
		// TODO Auto-generated method stub
		return locacionDao.listarLocaciones(tamaño, ubicacion, local);
	}


	@Override
	public List<Requisito> listarRequisitos() throws Exception {
		// TODO Auto-generated method stub
		return requisitoDao.listarRequisitos();
	}
	@Override
	public Integer registrarEntregaDoc(EntregaDoc requisito) throws Exception {
		// TODO Auto-generated method stub
		return entregaDocDao.registrarEntregaDoc(requisito);
	}

	@Override
	public List<Evaluacion> listarEvaluaciones() throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.listarEvaluaciones();
	}
	
	@Override
	public List<Evaluacion> listarEvaluacionesVencidas() throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.listarEvaluacionesVencidas();
	}
	
	@Override
	public List<Evaluacion> listarEvaluacionesFiltro(String ruc, String razonSocial, Date fecEvalIni, Date fecEvalFin, String estado) throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.listarEvaluacionesFiltro(ruc, razonSocial, fecEvalIni, fecEvalFin, estado);
	}

	@Override
	public Integer registrarEvaluacion(Evaluacion evaluacion) throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.registrarEvaluacion(evaluacion);
	}

	@Override
	public Integer actualizarEvaluacion(Evaluacion evaluacion) throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.actualizarEvaluacion(evaluacion);
	}

	@Override
	public InfEstEmp obtenerInfEstEmp(long idSolicitud) throws Exception {
		// TODO Auto-generated method stub
		return infEstEmpDao.obtenerInfEstEmp(idSolicitud);
	}

	@Override
	public InfRedSoc obtenerInfEvalMerc(long idSolicitud) throws Exception {
		// TODO Auto-generated method stub
		return infEvalMercDao.obtenerInfEvalMerc(idSolicitud);
	}

	@Override
	public InfSanciones obtenerInfSanciones(long idSolicitud)
			throws Exception {
		// TODO Auto-generated method stub
		return infSancionesDao.obtenerInfSanciones(idSolicitud);
	}

	@Override
	public List<Solicitud> listarSolicitudes() throws Exception {
		// TODO Auto-generated method stub
		return solicitudDao.listarSolicitudes();
	}
	
	@Override
	public List<Solicitud> listarSolicitudesFiltro(String ruc, String razonSocial, Date fecSolIni, Date fecSolFin, String estado) throws Exception {
		// TODO Auto-generated method stub
		return solicitudDao.listarSolicitudesFiltro(ruc, razonSocial, fecSolIni, fecSolFin, estado);
	}

	@Override
	public Integer registrarSolicitud(Solicitud solicitud) throws Exception {
		// TODO Auto-generated method stub
		return solicitudDao.registrarSolicitud(solicitud);
	}
	
	@Override
	public Integer actualizarSolicitud(long idSolicitud, String estado) throws Exception {
		// TODO Auto-generated method stub
		return solicitudDao.actualizarSolicitud(idSolicitud, estado);
	}

	
	@Override
	public Evaluador obtenerEvaluador(long idEvaluador) throws Exception {
		// TODO Auto-generated method stub
		return evaluadorDao.obtenerEvaluador(idEvaluador);
	}
	
	@Override
	public Evaluador evaluadorDisponible() throws Exception {
		// TODO Auto-generated method stub
		return evaluadorDao.evaluadorDisponible();
	}

	@Override
	public Integer actualizarEvaluador(long idEvaluador, String estado, Date fechaProp, Integer cantEval) throws Exception {
		// TODO Auto-generated method stub
		return evaluadorDao.actualizarEvaluador(idEvaluador, estado, fechaProp, cantEval);
	}
	
	@Override
	public Evaluacion obtenerEvaluacion(long idEvaluacion) throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.obtenerEvaluacion(idEvaluacion);
	}
	
	@Override
	public List<Evaluador> listarEvaluadores() throws Exception {
		// TODO Auto-generated method stub
		return evaluadorDao.listarEvaluadores();
	}

	@Override
	public Integer registrarInfEstEmp(InfEstEmp infEstEmp) throws Exception {
		// TODO Auto-generated method stub
		return infEstEmpDao.registrarInfEstEmp(infEstEmp);
	}

	@Override
	public Integer registrarInfRedSoc(InfRedSoc infRedSoc) throws Exception {
		// TODO Auto-generated method stub
		return infEvalMercDao.registrarInfRedSoc(infRedSoc);
	}

	@Override
	public Integer actualizarEstadoEvaluacion(long idEvaluacion, long idSolicitud,String estado)
			throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.actualizarEstadoEvaluacion(idEvaluacion,idSolicitud,estado);
	}
	
	@Override
	public Evaluacion obtenerEvaluacionSol(long idSolicitud) throws Exception {
		// TODO Auto-generated method stub
		return evaluacionDao.obtenerEvaluacionSol(idSolicitud);
	}

	@Override
	public EvaluacionEmpresarial calcularEvaluacionEmpresarial(long idEvaluacion)
			throws Exception {
		// TODO Auto-generated method stub
		return evaEmpDao.calcularEvaluacionEmpresarial(idEvaluacion);
	}

	@Override
	public EvaluacionEmpresarial obtenerEvaluacionEmpresarial(long idEvaluacion)
			throws Exception {
		// TODO Auto-generated method stub
		return evaEmpDao.obtenerEvaluacionEmpresarial(idEvaluacion);
	}
	
	@Override
	public Integer registrarEvaluacionEmpresarial(
			EvaluacionEmpresarial evaEmpresarial) throws Exception {
		// TODO Auto-generated method stub
		return evaEmpDao.registrarEvaluacionEmpresarial(evaEmpresarial);
	}

	@Override
	public Integer registrarEvaluacionRedSocial(
			EvaluacionRedSocial evaRedSocial) throws Exception {
		// TODO Auto-generated method stub
		return evaRedSocDao.registrarEvaluacionRedSocial(evaRedSocial);
	}
	
	@Override
	public EvaluacionRedSocial calcularScore(long idEvaluacion, Map<Double,Object> map) throws Exception {
		// TODO Auto-generated method stub
		return evaRedSocDao.calcularEvaluacionRedSocial(idEvaluacion, map);
	}	

	@Override
	public EvaluacionRedSocial obtenerEvaluacionRedSocial(long idEvaluacion)
			throws Exception {
		// TODO Auto-generated method stub
		return evaRedSocDao.obtenerEvaluacionRedSocial(idEvaluacion);
	}
}
