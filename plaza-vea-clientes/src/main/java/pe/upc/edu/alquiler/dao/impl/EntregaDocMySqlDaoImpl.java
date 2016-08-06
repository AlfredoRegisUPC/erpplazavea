package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.EntregaDocDao;
import pe.upc.edu.alquiler.model.EntregaDoc;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("entregaDocDao")
public class EntregaDocMySqlDaoImpl extends AbstractDao implements EntregaDocDao {

	@Override
	public Integer registrarEntregaDoc(EntregaDoc requisito) throws Exception {
		/*String hql = "insert into EntregaDoc (idRequisito,idSolicitud,fechaEntrega,estado) values("
		        + requisito.getIdRequisito()+","+requisito.getIdSolicitud()+","+requisito.getFechaEntrega()+","+requisito.getEstado()+");";
		 
		Query query = getSession().createQuery(hql);
		 
		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
		    System.out.println(rowsAffected + "(s) were inserted");
		}
				return rowsAffected;
		*/
		
		Query query = getSession().createQuery("select max(idEntregaDoc) from EntregaDoc");
		Integer idEntregaDoc = 1;
		
		if(query.uniqueResult() != null)
			idEntregaDoc = Integer.parseInt(query.uniqueResult().toString())+1;
        
        requisito.setIdEntregaDoc((long)idEntregaDoc);

        getSession().save(requisito);
		
		return idEntregaDoc;
		

	}

}
