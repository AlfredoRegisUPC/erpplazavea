package pe.upc.edu.alquiler.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.EvaluacionEmpresarialDao;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.EvaluacionEmpresarial;
import pe.upc.edu.alquiler.model.InformacionCrediticia;
import pe.upc.edu.alquiler.model.InformacionCrediticiaData;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.model.OmisionTributaria;
import pe.upc.edu.alquiler.model.OmisionTributariaData;
import pe.upc.edu.alquiler.model.Parametro;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.alquiler.model.Sunat;
import pe.upc.edu.alquiler.model.SunatData;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("evaluacionEmpresarialDao")
public class EvaluacionEmpresarialMySqlDaoImpl extends AbstractDao implements EvaluacionEmpresarialDao {

	@Override
	public EvaluacionEmpresarial calcularEvaluacionEmpresarial(long idEvaluacion)
			throws Exception {
		
		Query query;
		EvaluacionEmpresarial evaEmp = new EvaluacionEmpresarial();
		/*
		Query query = getSession().createQuery("from EvaluacionEmpresarial where pk_idEvaluacion= :idEvaluacion");
        query.setString("idEvaluacion", String.valueOf(idEvaluacion));
        EvaluacionEmpresarial evaEmp= (EvaluacionEmpresarial) query.uniqueResult();
        */
		        
		query = getSession().createQuery("from Evaluacion where idEvaluacion= :idEvaluacion");
        query.setString("idEvaluacion", String.valueOf(idEvaluacion));
        Evaluacion evaSol= (Evaluacion) query.uniqueResult();
        
        Solicitud sol = evaSol.getSolicitud();
        long idSol = sol.getIdSolicitud();
        
        query = getSession().createQuery("from Solicitud where idSolicitud = :idSolicitud");
        query.setString("idSolicitud", String.valueOf(idSol));
        sol = (Solicitud) query.uniqueResult();
        
        Locatario loc = sol.getLocatario();
        long idLoc = loc.getIdLocatario();
        
        query = getSession().createQuery("from Locatario where idLocatario = :idLocatario");
        query.setString("idLocatario", String.valueOf(idLoc));
        loc = (Locatario) query.uniqueResult();
        
        query = getSession().createQuery("from InformacionCrediticiaData where pk_idLocatario = :idLocatario");
        query.setString("idLocatario", String.valueOf(idLoc));
        InformacionCrediticiaData infCreDat = (InformacionCrediticiaData) query.uniqueResult();
        
        query = getSession().createQuery("from SunatData where pk_idLocatario = :idLocatario");
        query.setString("idLocatario", String.valueOf(idLoc));
        SunatData sunDat = (SunatData) query.uniqueResult();
        
        query = getSession().createQuery("from OmisionTributariaData where pk_idLocatario = :idLocatario");
        query.setString("idLocatario", String.valueOf(idLoc));
        List<OmisionTributariaData> lstOmiDat = query.list();
        Set<OmisionTributariaData> lstOmiTriDat = new HashSet<OmisionTributariaData>(lstOmiDat);
        
        Set<OmisionTributaria> lstOmiTri = new HashSet<OmisionTributaria>() ;
        OmisionTributaria omiTri = new OmisionTributaria();
        for (OmisionTributariaData element : lstOmiTriDat) {
        	omiTri.setIdOmision(element.getIdOmisionData());
            omiTri.setPeriodo(element.getPeriodo());
            omiTri.setOmisionDescripcion(element.getOmisionDescripcion());
            lstOmiTri.add(omiTri);
        }
        Sunat sunat = null;
        if(sunDat != null){
        sunat = new Sunat();
        sunat.setActividadEconomica(sunDat.getActividadEconomica());
        sunat.setCondicion(sunDat.getCondicion());     
        sunat.setTipoContribuyente(sunDat.getTipoContribuyente());
        sunat.setIdEvaluacion(idEvaluacion);
        sunat.setDetalleOmisiones(lstOmiTri);
        }
        
        InformacionCrediticia infCre = null;
        if(infCreDat != null){
        infCre = new InformacionCrediticia();
        infCre.setCalificacion(infCreDat.getCalificacion());
        infCre.setCalificacionAbr(infCreDat.getCalificacionAbr());
        infCre.setDeudaAcumulada(infCreDat.getDeudaAcumulada());
        infCre.setScore(infCreDat.getScore());
        infCre.setIdEvaluacion(idEvaluacion);
        }
        
        evaEmp.setSunat(sunat);
        evaEmp.setInformacionCrediticia(infCre);
        
        evaEmp.setIdEvaluacion(idEvaluacion);   
        
        if(sunat !=null && infCre !=null)
        obtenerResultado(evaEmp);
        
		return evaEmp;
		
	}
	
	private void obtenerResultado(EvaluacionEmpresarial evaEmp){
		Double sumaValores;
		//=============================
		// 			SUNAT
		//=============================
		//Evaluación de Actividad Económica
		Query query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and valorTexto = :valorTexto");
        query.setString("idParametro", String.valueOf(1));
        query.setString("valorTexto", String.valueOf(evaEmp.getSunat().getActividadEconomica()));
		Parametro par = (Parametro) query.uniqueResult(); 
		evaEmp.getSunat().setValorActividadEconomica(par.getValorNumerico());
		
		sumaValores = par.getValorNumerico();
		
		//Evaluación de Condición
        query.setString("idParametro", String.valueOf(2));
        query.setString("valorTexto", String.valueOf(evaEmp.getSunat().getCondicion()));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.getSunat().setValorCondicion(par.getValorNumerico());
		
		sumaValores += par.getValorNumerico();
		
		//Evaluación de Condición
        query.setString("idParametro", String.valueOf(4));
        query.setString("valorTexto", String.valueOf(evaEmp.getSunat().getTipoContribuyente()));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.getSunat().setValorTipoContribuyente(par.getValorNumerico());
		
		sumaValores += par.getValorNumerico();
		
		//Evaluación de Omisiones	
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and rangoInicial <= :valorNumero and rangoFinal >= :valorNumero");
        query.setString("idParametro", String.valueOf(3));
        query.setString("valorNumero", String.valueOf(evaEmp.getSunat().getDetalleOmisiones().size()));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.getSunat().setValorOmisiones(par.getValorNumerico());
		
		sumaValores += par.getValorNumerico();
		
		//=============================
		// 		Info Crediticia
		//=============================
		//Evaluación de Calificación
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and valorTexto = :valorTexto");
        query.setString("idParametro", String.valueOf(5));
        query.setString("valorTexto", String.valueOf(evaEmp.getInformacionCrediticia().getCalificacionAbr()));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.getInformacionCrediticia().setValorCalificacion(par.getValorNumerico());
		
		sumaValores += par.getValorNumerico();
		
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and valorTexto = :valorTexto and rangoInicial <= :valorNumero and rangoFinal >= :valorNumero");
        query.setString("idParametro", String.valueOf(6));
        if (evaEmp.getInformacionCrediticia().getCalificacionAbr().equals("NOR")){
        	query.setString("valorTexto", String.valueOf(evaEmp.getInformacionCrediticia().getCalificacionAbr()));
        }else{
        	query.setString("valorTexto", "MOR");
        }
        query.setString("valorNumero", String.valueOf(evaEmp.getInformacionCrediticia().getDeudaAcumulada()));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.getInformacionCrediticia().setValorDeuda(par.getValorNumerico());
		
		sumaValores += par.getValorNumerico();
		
		evaEmp.setTotalScore(sumaValores);
		//=============================
		// 		Resultado Eva.Emp
		//=============================
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and rangoInicial <= :valorNumero and rangoFinal >= :valorNumero");
        query.setString("idParametro", String.valueOf(7));
        query.setString("valorNumero", String.valueOf(sumaValores));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.setResultado(par.getValorTexto());
	}

	@Override
	public Integer registrarEvaluacionEmpresarial(
			EvaluacionEmpresarial evaEmpresarial) throws Exception {
		
		
		getSession().save(evaEmpresarial.getInformacionCrediticia());
		getSession().save(evaEmpresarial.getSunat());
		
		evaEmpresarial.setCodInfEstEmp(evaEmpresarial.getEvaluacion().getSolicitud().getCodSolicitud().replace("SOL", "IEE"));
		
		getSession().save(evaEmpresarial);
        
        return Integer.parseInt(evaEmpresarial.getIdEvaluacion()+"");
	}

	@Override
	public Integer actualizarEstadoEvaluacionEmpresarial(long idEvaluacion,
			long idSolicitud, String estado) throws Exception {
				
		return null;
	}
	
	@Override
	public EvaluacionEmpresarial obtenerEvaluacionEmpresarial(long idEvaluacion)
			throws Exception {		
		
		Query query = getSession().createQuery("from EvaluacionEmpresarial where pk_idEvaluacion = :idEvaluacion");
        query.setString("idEvaluacion", String.valueOf(idEvaluacion));        
        EvaluacionEmpresarial evaEmp = (EvaluacionEmpresarial) query.uniqueResult(); 		
		
		return evaEmp;
	}
}
