package pe.upc.edu.alquiler.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.EvaluacionDao;
import pe.upc.edu.alquiler.model.Evaluacion;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfRedSoc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("evaluacionDao")
public class EvaluacionMySqlDaoImpl extends AbstractDao implements EvaluacionDao {
	
	@SuppressWarnings("unchecked")
	public List<Evaluacion> listarEvaluaciones() throws Exception {
		/*List<Evaluacion> listEvaluaciones = (List<Evaluacion>)getSession()
        							.createQuery("from Evaluacion").list();
		System.out.println("Las evaluaciones son " + listEvaluaciones.size());
        return listEvaluaciones;
        Query query = getSession().createQuery("select l.idLocal, "
				+ "concat(l.nombre,'-',l.direccion,'-',u.distrito,'-',u.provincia,'-',u.departamento) as nombre,"
				+ " l.direccion, l.ubigeo "
				+ "from Local l INNER JOIN l.ubigeo u");
				List<Object[]> list = query.list();
        List<Local> listLocales = new ArrayList<Local>();
        for(Object[] local: list){
        	Local l = new Local();
        	l.setIdLocal((long)local[0]);
        	l.setNombre(local[1].toString());
        	l.setDireccion(local[2].toString());
        	l.setUbigeo((Ubigeo)local[3]);
        	listLocales.add(l);
        }
        */
		List<Evaluacion> listEvaluaciones = new ArrayList<Evaluacion>();
		/*String hql = "from Evaluacion eval, Evaluador e, Colaborador col,Solicitud sol,Locatario loc where"
				+ " eval.evaluador = e.idEvaluador and e.colaborador = col.idColaborador and eval.idSolicitud = sol.idSolicitud  and sol.idLocatario = loc.idLocatario";
		*/
		//String hql = "from Evaluacion ev, Evaluador e, Colaborador c where ev.evaluador = e.idEvaluador and e.colaborador = c.idColaborador";
		String hql = "from Evaluacion ev, Solicitud s, Locatario l where ev.solicitud = s.idSolicitud and s.locatario = l.idLocatario";
		
		////String hql = "from Evaluacion ev INNER JOIN ev.evaluador e INNER JOIN e.colaborador c";
		
		List<?> list = getSession().createQuery(hql).list();

		for(int i=0; i<list.size(); i++) {
			Object[] row = (Object[]) list.get(i);
			Evaluacion eval = (Evaluacion)row[0];
			//Evaluador e = (Evaluador)row[1];
			//Colaborador col = (Colaborador)row[2];
			Solicitud sol = (Solicitud)row[1];
			//Locatario loc = (Locatario)row[4];
			eval.setSolicitud(sol);
			//eval.setLocatario(loc);
			//eval.setEvaluador(e);
			//eval.setColaborador(col);
			listEvaluaciones.add(eval);
		}		
		return listEvaluaciones;
	}

	@Override
	public Integer registrarEvaluacion(Evaluacion evaluacion) throws Exception {
		
		Query query = getSession().createQuery("select max(idEvaluacion) from Evaluacion");
		Integer idEvaluacion = 1;
		
		if(query.uniqueResult() != null)
			idEvaluacion = Integer.parseInt(query.uniqueResult().toString())+1;
        
        evaluacion.setIdEvaluacion((long)idEvaluacion);

        getSession().save(evaluacion);
        /*//Guardar informes PRUEBAS
        query = getSession().createQuery("select max(idInfEstEmp) from InfEstEmp");
        Integer idInfEstEmp = 1;
		
		if(query.uniqueResult() != null)
			idInfEstEmp = Integer.parseInt(query.uniqueResult().toString())+1;
        
		InfEstEmp infEstEmp = new InfEstEmp();
		infEstEmp.setIdInfEstEmp((long)idInfEstEmp);
		infEstEmp.setIdEvaluacion((long)idEvaluacion);
		infEstEmp.setActEconPrin("ACTIVIDAD ECONOMICA PRINCIPAL");
		infEstEmp.setComentario("COMENTARIO");
		infEstEmp.setEstado("APROBADO");
		infEstEmp.setEstadoContri("HABIDO");
		infEstEmp.setFecha(new Date());
		infEstEmp.setOmisiones("NINGUNA");
		infEstEmp.setRepLegal("REPRESENTANTE LEGAL");
		
        getSession().save(infEstEmp);
        
        query = getSession().createQuery("select max(idInfSanciones) from InfSanciones");
        Integer idInfSanciones = 1;
		
		if(query.uniqueResult() != null)
			idInfSanciones = Integer.parseInt(query.uniqueResult().toString())+1;
        
		InfSanciones infSanciones = new InfSanciones();
		infSanciones.setIdInfSanciones((long)idInfSanciones);
		infSanciones.setIdEvaluacion((long)idEvaluacion);
		infSanciones.setAntBancario("NINGUNO");
		infSanciones.setComentario("COMENTARIO");
		infSanciones.setEstado("APROBADO");
		infSanciones.setEstadoBanc("APROBADO");
		infSanciones.setFecha(new Date());
		infSanciones.setFechaDeuda(new Date());
		infSanciones.setMontoDeuda(0.00);
		
        getSession().save(infSanciones);
        
        query = getSession().createQuery("select max(idInfEvalMerc) from InfEvalMerc");
        Integer idInfEvalMerc = 1;
        
        if(query.uniqueResult()!=null)
        	idInfEvalMerc = Integer.parseInt(query.uniqueResult().toString())+1;
        
        InfEvalMerc infEvalMerc = new InfEvalMerc();
        infEvalMerc.setIdInfEvalMerc((long)idInfEvalMerc);
        infEvalMerc.setIdEvaluacion((long)idEvaluacion);
        infEvalMerc.setCalificacion("10");
        infEvalMerc.setComentario("COMENTARIO");
        infEvalMerc.setEstado("APROBADO");
        infEvalMerc.setFecha(new Date());
        infEvalMerc.setFuente("REDES SOCIALES");
        infEvalMerc.setTipoMedio("INTERNET");
        infEvalMerc.setTipoRecep("OTROS");
        
        getSession().save(infEvalMerc);
        // Guardar informes PRUEBA
*/		
		return idEvaluacion;
        
	}

	@Override
	public Integer actualizarEvaluacion(Evaluacion evaluacion) throws Exception {
		
		/*String hql = "update Evaluacion set estado = :estado, fechaModif= :fecMod, observacion = :obs where idEvaluacion = :id";
		 
		Query query = getSession().createQuery(hql);
		query.setParameter("estado", evaluacion.getEstado());
		query.setParameter("fecMod", new Date());
		query.setParameter("obs", evaluacion.getObservacion());
		query.setParameter("id", evaluacion.getIdEvaluacion());*/
		
		evaluacion.setFechaModif(new Date());
		 
		getSession().update(evaluacion);
		
		//int rowsAffected = query.executeUpdate();
		//if (rowsAffected > 0) {
		    //System.out.println("Updated " + rowsAffected + " rows.");
		//}
		return 1;
	}

	@Override
	public Evaluacion obtenerEvaluacion(long idEvaluacion)
			throws Exception {
		String hql = "from Evaluacion ev, Solicitud s, Locatario l where ev.solicitud = s.idSolicitud and s.locatario = l.idLocatario and ev.idEvaluacion = " + idEvaluacion;
			
		Object[] row = (Object[]) getSession().createQuery(hql).uniqueResult();

		Evaluacion eval = null;
		if(row!=null){
		eval = (Evaluacion)row[0];
		Solicitud sol = (Solicitud)row[1];
		eval.setSolicitud(sol);
		}

		return eval;
	}
	
	@Override
	public List<Evaluacion> listarEvaluacionesFiltro(String ruc, String razonSocial, Date fecEvalIni, Date fecEvalFin, String estado) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			
		Criteria c = getSession().createCriteria(Evaluacion.class, "eva");
		if (estado != null && !estado.equals(""))
		c.add((estado == null || estado.equals("")) ? Restrictions.isNull("eva.estado") : Restrictions.eq("eva.estado", estado));
		if (fecEvalIni != null && !fecEvalIni.equals(""))
		c.add((fecEvalIni == null || fecEvalIni.equals("")) ? Restrictions.isNull("eva.fechaCreacion") : Restrictions.ge("eva.fechaCreacion", fecEvalIni));
		if (fecEvalFin != null && !fecEvalFin.equals(""))
			c.add((fecEvalFin == null || fecEvalFin.equals("")) ? Restrictions.isNull("eva.fechaCreacion") : Restrictions.lt("eva.fechaCreacion", fecEvalFin));
			
		c.createCriteria("eva.solicitud" , "sol");
		c.createCriteria("sol.locatario" , "loc");
		if (ruc != null && !ruc.equals("") || razonSocial != null && !razonSocial.equals("")){	
		if (ruc != null && !ruc.equals("") )
		c.add((ruc == null || ruc.equals("")) ? Restrictions.isNull("loc.ruc") : Restrictions.eq("loc.ruc", ruc));
		if (razonSocial != null && !razonSocial.equals(""))
		c.add((razonSocial == null || razonSocial.equals("")) ? Restrictions.isNull("loc.razonSocial") : Restrictions.eq("loc.razonSocial", razonSocial));
		}

		List<Evaluacion> listEvaluaciones = c.list();
		
		System.out.println("Las evaluaciones son " + listEvaluaciones.size());
        return listEvaluaciones;
	}
	
	@Override
	public Integer actualizarEstadoEvaluacion(long idEvaluacion, long idSolicitud, String estado) throws Exception {
		// TODO Auto-generated method stub
		//Retornar el idSolicitud
		String hql = "update Evaluacion set estado = :estado where idEvaluacion = :id";
		 
		Query query = getSession().createQuery(hql);
		query.setParameter("estado", estado);
		query.setParameter("id", idEvaluacion);
		 
		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
			hql = "update Solicitud set estado = :estado where idSolicitud = :id";
			 
			query = getSession().createQuery(hql);
			query.setParameter("estado", estado);
			query.setParameter("id", idSolicitud);
			 
			rowsAffected = query.executeUpdate();
			if (rowsAffected > 0) {
			    System.out.println("Updated " + rowsAffected + " rows.");
			}
		}
		
		return 1;
	}
	
	@Override
	public Evaluacion obtenerEvaluacionSol(long idSolicitud)
			throws Exception {
		String hql = "from Evaluacion ev, Solicitud s, Locatario l where ev.solicitud = s.idSolicitud and s.locatario = l.idLocatario and ev.solicitud = " + idSolicitud;
			
		Object[] row = (Object[]) getSession().createQuery(hql).uniqueResult();

			Evaluacion eval = (Evaluacion)row[0];
			Solicitud sol = (Solicitud)row[1];
			eval.setSolicitud(sol);

			return eval;
	}
	
	@Override
	public List<Evaluacion> listarEvaluacionesVencidas() throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String[] estados = { "Registrada", "Pendiente" };
			
		Criteria c = getSession().createCriteria(Evaluacion.class, "eva");
		c.add(Restrictions.in("eva.estado", estados ));
		c.add(Restrictions.le("eva.fechaProp", new Date()));
		c.createCriteria("eva.solicitud" , "sol");
			
		List<Evaluacion> listEvaluaciones = c.list();
		
		System.out.println("Las evaluaciones vencidas son " + listEvaluaciones.size());
        return listEvaluaciones;
	}
	
}
