package pe.upc.edu.alquiler.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
        solicitud.setCodSolicitud("SOL"+idSolicitud);
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
}
