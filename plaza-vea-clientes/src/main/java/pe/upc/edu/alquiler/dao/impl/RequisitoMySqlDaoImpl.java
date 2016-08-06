package pe.upc.edu.alquiler.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.RequisitoDao;
import pe.upc.edu.alquiler.model.Requisito;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("requisitoDao")
public class RequisitoMySqlDaoImpl extends AbstractDao implements RequisitoDao {
	
	@SuppressWarnings("unchecked")
	public List<Requisito> listarRequisitos() throws Exception {
		List<Requisito> listRequisitos = (List<Requisito>)getSession()
        							.createQuery("from Requisito").list();
		System.out.println("Los Requisito son " + listRequisitos.size());
        return listRequisitos;
	}
	
}
