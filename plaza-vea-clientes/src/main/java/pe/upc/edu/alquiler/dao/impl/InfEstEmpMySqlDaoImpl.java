package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.InfEstEmpDao;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.alquiler.model.Parametro;
import pe.upc.edu.spring.configuration.AbstractDao;
@Transactional
@Repository("infEstEmpDao")
public class InfEstEmpMySqlDaoImpl extends AbstractDao implements InfEstEmpDao {

	@Override
	public InfEstEmp obtenerInfEstEmp(long idEvaluacion) throws Exception {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from InfEstEmp where idEvaluacion= :eval");
        query.setString("eval", String.valueOf(idEvaluacion));
        InfEstEmp infEstEmp = (InfEstEmp) query.uniqueResult();
		return infEstEmp;
	}

	@Override
	public Integer registrarInfEstEmp(InfEstEmp infEstEmp) throws Exception {
		Query query = getSession().createQuery("select max(idInfEstEmp) from InfEstEmp");
		Integer idInfEstEmp = 1;
		
		if(query.uniqueResult() != null)
			idInfEstEmp = Integer.parseInt(query.uniqueResult().toString())+1;
        
		infEstEmp.setIdInfEstEmp((long)idInfEstEmp);
		infEstEmp.setCodInfEstEmp(infEstEmp.getEvaluacion().getSolicitud().getCodSolicitud().replace("SOL", "IEE"));

        getSession().save(infEstEmp);
        
        return idInfEstEmp;
	}
	
	public void obtenerResultado(InfEstEmp evaEmp){
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
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and rangoInicial >= :valorNumero and rangoFinal <= :valorNumero");
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
		
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and valorTexto = :valorTexto and rangoInicial >= :valorNumero and rangoFinal <= :valorNumero");
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
		query = getSession().createQuery("from Parametro where pk_idParametro = :idParametro and rangoInicial >= :valorNumero and rangoFinal <= :valorNumero");
        query.setString("idParametro", String.valueOf(7));
        query.setString("valorNumero", String.valueOf(sumaValores));
		par = (Parametro) query.uniqueResult(); 
		evaEmp.setResultado(par.getValorTexto());
	}

}
