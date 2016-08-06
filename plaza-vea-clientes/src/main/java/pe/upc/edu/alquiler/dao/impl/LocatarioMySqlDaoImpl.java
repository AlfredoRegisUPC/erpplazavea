package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.LocatarioDao;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("locatarioDao")
public class LocatarioMySqlDaoImpl extends AbstractDao implements LocatarioDao {
	
	@Override
	public Locatario obtenerLocatario(String ruc) throws Exception {
		// TODO Auto-generated method stub
		 Query query = getSession().createQuery("from Locatario where ruc= :ruc");
        query.setString("ruc", ruc);
        Locatario locatario = (Locatario) query.uniqueResult();
		return locatario;
	}

	@Override
	public Integer registrarLocatario(Locatario locatario) throws Exception {
		
		Query query = getSession().createQuery("select max(idLocatario) from Locatario");
		
		Integer idLocatario = 1;
		
		if(query.uniqueResult() != null)
			idLocatario = Integer.parseInt(query.uniqueResult().toString())+1;
        
        locatario.setIdLocatario((long)idLocatario);
        getSession().save(locatario);
        
		
		return idLocatario;
	}
	
}
