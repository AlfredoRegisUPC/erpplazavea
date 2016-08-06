package pe.upc.edu.alquiler.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.EvaluadorDao;
import pe.upc.edu.alquiler.model.Evaluador;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("evaluadorDao")
public class EvaluadorMySqlDaoImpl extends AbstractDao implements EvaluadorDao {
	
	public Evaluador obtenerEvaluador(long idEvaluador) throws Exception {
		Evaluador evaluador = (Evaluador)getSession()
        							.createQuery("from Evaluador where idEvaluador = " + idEvaluador).list();
        return evaluador;
	}

	@Override
	public Evaluador evaluadorDisponible() throws Exception {
		// TODO Auto-generated method stub
		
		Query query = getSession().createQuery("from Evaluador where estado = 'Disponible' ");
		query.setMaxResults(1);
		Evaluador evaluador = (Evaluador) query.uniqueResult();
		return evaluador;
	}
	
	@Override
	public List<Evaluador> listarEvaluadores() throws Exception {
		// TODO Auto-generated method stub
			List<Evaluador> listEvaluadores = (List<Evaluador>)getSession()
				.createQuery("from Evaluador where estado = 'Disponible' order by cantidadEval asc").list();
			System.out.println("Los evaluadores disponibles son " + listEvaluadores.size());
			return listEvaluadores;
	}

	@Override
	public int actualizarEvaluador(long idEvaluador, String estado, Date fechaProp, Integer cantEva) throws Exception {
		
		if(cantEva<0)
			cantEva = 0;
		
		String hql = "update Evaluador set estado = :estado, fechaAct= :fecPro, actividad = :act, cantidadEval = :cantEva where idEvaluador = :id";
		 
		Query query = getSession().createQuery(hql);
		query.setParameter("estado", estado);
		if(estado.equals("Disponible")){
			query.setParameter("fecPro",null);
			query.setParameter("act",null);
		}
		else{
		query.setParameter("fecPro", fechaProp);
		query.setParameter("act", "Evaluando solicitud de alquiler");
		}
		query.setParameter("cantEva", cantEva);
		query.setParameter("id", idEvaluador);
		 
		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
		    System.out.println("Updated " + rowsAffected + " rows.");
		}
		return 1;
	}
	
}
