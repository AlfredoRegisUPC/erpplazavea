package pe.upc.edu.alquiler.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.SolicitudDao;
import pe.upc.edu.alquiler.model.Solicitud;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("solicitudDao")
public class SolicitudMySqlDaoImpl extends AbstractDao implements SolicitudDao {
	
	@SuppressWarnings("unchecked")
	public List<Solicitud> listarSolicitudes() throws Exception {
		List<Solicitud> listSolicitudes = (List<Solicitud>)getSession()
        							.createQuery("from Solicitud").list();
		System.out.println("Las solicitudes son " + listSolicitudes.size());
        return listSolicitudes;
	}

	@Override
	public Integer registrarSolicitud(Solicitud solicitud) throws Exception {
		// TODO Auto-generated method stub
		//Retornar el idSolicitud

		Query query = getSession().createQuery("select max(idSolicitud) from Solicitud");
		Integer idSolicitud = 1;
		
		if(query.uniqueResult() != null)
		idSolicitud = Integer.parseInt(query.uniqueResult().toString())+1;
        
        solicitud.setIdSolicitud((long)idSolicitud);
        
        /*query = getSession().createQuery("select CONCAT('SOL',ano,siglas,MID(''+10000+(secuencia+1),2)) "
        		+ "from Numerador a, Locatario b "
        		+ "where a.numeradorLlave.locatario.idLocatario = b.idLocatario and b.idLocatario = :id");*/
        query = getSession().createQuery("select CONCAT('SOL',ano,siglas,(secuencia+1)) "
        		+ "from Numerador a, Locatario b "
        		+ "where a.numeradorLlave.locatario.idLocatario = b.idLocatario and b.idLocatario = :id");
        
        query.setParameter("id", solicitud.getLocatario().getIdLocatario());
        
        if(query.uniqueResult()!=null){
        System.out.println(query.uniqueResult().toString());        
        solicitud.setCodSolicitud(query.uniqueResult().toString());
        query = getSession().createQuery("update Numerador set secuencia = secuencia+1 where numeradorLlave.locatario.idLocatario = :id");
        query.setParameter("id", solicitud.getLocatario().getIdLocatario());
        query.executeUpdate();
        }
        
        getSession().save(solicitud);
		
		return idSolicitud;
	}
	
	@Override
	public Integer actualizarSolicitud(long idSolicitud, String estado) throws Exception {
		// TODO Auto-generated method stub
		//Retornar el idSolicitud
		String hql = "update Solicitud set estado = :estado where idSolicitud = :id";
		 
		Query query = getSession().createQuery(hql);
		query.setParameter("estado", estado);
		query.setParameter("id", idSolicitud);
		 
		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
		    System.out.println("Updated " + rowsAffected + " rows.");
		}
		return 1;
	}
	
	@Override
	public List<Solicitud> listarSolicitudesFiltro(String ruc, String razonSocial, Date fecSolIni, Date fecSolFin, String estado) throws Exception {
/*		String query = "from Solicitud";
		if(ruc!=null && !ruc.equals(""))
			query = "from Solicitud where ruc= :ruc";
		else if(razonSocial!=null && !razonSocial.equals(""))
			query = "from Solicitud where razonSocial= :razonSocial";
		else if(estado!=null && !estado.equals(""))
			query = "from Solicitud where estado= :estado";
		else if(fecSol!=null && !fecSol.equals(""))
			query = "from Solicitud where fecSol= :fecSol";
			*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			
		Criteria c = getSession().createCriteria(Solicitud.class, "sol");
		if (estado != null && !estado.equals(""))
		c.add((estado == null || estado.equals("")) ? Restrictions.isNull("sol.estado") : Restrictions.eq("sol.estado", estado));
		if (fecSolIni != null && !fecSolIni.equals(""))
		c.add((fecSolIni == null || fecSolIni.equals("")) ? Restrictions.isNull("sol.fecha") : Restrictions.ge("sol.fecha", fecSolIni));
		if (fecSolFin != null && !fecSolFin.equals(""))
		c.add((fecSolFin == null || fecSolFin.equals("")) ? Restrictions.isNull("sol.fecha") : Restrictions.lt("sol.fecha", fecSolFin));
		if (ruc != null && !ruc.equals("") || razonSocial != null && !razonSocial.equals("")){
		c.createCriteria("sol.locatario" , "loc");
		if (ruc != null && !ruc.equals("") )
		c.add((ruc == null || ruc.equals("")) ? Restrictions.isNull("loc.ruc") : Restrictions.eq("loc.ruc", ruc));
		if (razonSocial != null && !razonSocial.equals(""))
		c.add((razonSocial == null || razonSocial.equals("")) ? Restrictions.isNull("loc.razonSocial") : Restrictions.eq("loc.razonSocial", razonSocial));
		}

		List<Solicitud> listSolicitudes = c.list();
		
		System.out.println("Las solicitudes son " + listSolicitudes.size());
        return listSolicitudes;
	}
}
