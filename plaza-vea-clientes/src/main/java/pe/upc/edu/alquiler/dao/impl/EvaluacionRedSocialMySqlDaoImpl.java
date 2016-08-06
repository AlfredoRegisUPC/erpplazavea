package pe.upc.edu.alquiler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.EvaluacionRedSocialDao;
import pe.upc.edu.alquiler.model.AnalisisPorRedSocial;
import pe.upc.edu.alquiler.model.AnalisisRedSocialLlave;
import pe.upc.edu.alquiler.model.CriterioRedSocial;
import pe.upc.edu.alquiler.model.EvaluacionRedSocial;
import pe.upc.edu.alquiler.model.Parametro;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("evaluacionRedSocialDao")
public class EvaluacionRedSocialMySqlDaoImpl extends AbstractDao implements EvaluacionRedSocialDao {
	Double sumaValores = 0.0;
	AnalisisRedSocialLlave arsLlave= new AnalisisRedSocialLlave();	 	
	CriterioRedSocial criRedSoc    = new CriterioRedSocial();
	List<AnalisisPorRedSocial> lstAnaRedSoc;
	
	@Override
	public EvaluacionRedSocial calcularEvaluacionRedSocial(long idEvaluacion, Map<Double,Object> map)
			throws Exception {		
		Query query;
		EvaluacionRedSocial evaRedSoc = new EvaluacionRedSocial();
		lstAnaRedSoc = new ArrayList<AnalisisPorRedSocial>();
				
		for (Map.Entry<Double,Object> a : map.entrySet()) {			
			    			
			procesaRed(idEvaluacion,a.getKey().intValue(),a.getValue());
			
			//Valor de Nivel de Aceptación
			query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and (:valorNumero between rangoInicial and rangoFinal)");
	        query.setString("idParametro", String.valueOf(12));
	        query.setString("valorNumero", String.valueOf(sumaValores));
			Parametro par = (Parametro) query.uniqueResult(); 
			evaRedSoc.setNivelAceptacion(par.getValorTexto());
			evaRedSoc.setValor(sumaValores);
			evaRedSoc.setIdEvaluacion(idEvaluacion);			
			evaRedSoc.setListaAnalisisPorRedSocial(lstAnaRedSoc);
		}
        return evaRedSoc;
	}
	
	@SuppressWarnings("unchecked")
	private void procesaRed(Long idEvaluacion, Integer idRedSocial, Object map){
		Integer idParametro = 0; 		
		Map<Double,Double> mapDato = (Map<Double,Double>)map;

		for(Map.Entry<Double,Double> c : mapDato.entrySet()){
			System.out.println("Dentro del list Criterio Red Social");
			switch (c.getKey().intValue()){
				case 1 :	//Seguidores
					idParametro = 8;
					break;
				case 2 :	//Número de Comentarios Buenos
					idParametro = 9;					
					break;
				case 3 :	//Número de Comentarios Malos
					idParametro = 10;					
					break;
				case 4 :	//Número de Comentarios Malos
					idParametro = 11;					
					break;	
			}		
			Query query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and (:valorNumero between rangoInicial and rangoFinal)");
	        query.setString("idParametro", String.valueOf(idParametro));
	        query.setString("valorNumero", String.valueOf(c.getValue()));
			Parametro par = (Parametro) query.uniqueResult(); 
			sumaValores += par.getValorNumerico();	
			
			//Relaciona a Analisis
			lstAnaRedSoc.add(new AnalisisPorRedSocial(idEvaluacion, idRedSocial, c.getKey().longValue(), par.getValorNumerico(), c.getValue()));
		}
	}

	@Override
	public Integer registrarEvaluacionRedSocial(
			EvaluacionRedSocial evaRedSocial) throws Exception {
		
				
		evaRedSocial.setCodEvaRedSoc(evaRedSocial.getEvaluacion().getSolicitud().getCodSolicitud().replace("SOL", "ERS"));		
		
		//Graba Evaluación de Redes Sociales
		getSession().save(evaRedSocial);
		
		//Graba el Análisis por Red Social
		for(AnalisisPorRedSocial aprs : evaRedSocial.getListaAnalisisPorRedSocial()){
			getSession().save(aprs);
		}		

		return evaRedSocial.getIdEvaluacion().intValue();
	}

	@Override
	public Integer actualizarEstadoEvaluacionRedSocial(long idEvaluacion,
			long idSolicitud, String estado) throws Exception {
				
		return null;
	}

	@Override
	public EvaluacionRedSocial obtenerEvaluacionRedSocial(long idEvaluacion)
			throws Exception {		
		
		Query query = getSession().createQuery("from EvaluacionRedSocial where pk_idEvaluacion = :idEvaluacion");
        query.setString("idEvaluacion", String.valueOf(idEvaluacion));        
        EvaluacionRedSocial evaRedSocial = (EvaluacionRedSocial) query.uniqueResult(); 		
		
		return evaRedSocial;
	}
	
}
