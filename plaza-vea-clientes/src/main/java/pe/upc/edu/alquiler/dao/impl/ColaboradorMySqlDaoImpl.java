package pe.upc.edu.alquiler.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.ColaboradorDao;
import pe.upc.edu.alquiler.model.Colaborador;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("colaboradorDao")
public class ColaboradorMySqlDaoImpl extends AbstractDao implements ColaboradorDao {
	
	public Colaborador obtenerColaborador(long idColaborador) throws Exception {
		Colaborador colaborador = (Colaborador)getSession()
        							.createQuery("from Colaborador where idColaborador = " + idColaborador).list();
        return colaborador;
	}
	
}
