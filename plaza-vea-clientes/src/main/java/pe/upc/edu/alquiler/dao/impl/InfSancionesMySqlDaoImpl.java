package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.InfSancionesDao;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.spring.configuration.AbstractDao;
@Transactional
@Repository("infSancionesDao")
public class InfSancionesMySqlDaoImpl extends AbstractDao implements InfSancionesDao {

	@Override
	public InfSanciones obtenerInfSanciones(long idEvaluacion) throws Exception {
		Query query = getSession().createQuery("from InfSanciones where idEvaluacion= :eval");
        query.setString("eval", String.valueOf(idEvaluacion));
        InfSanciones infSanciones = (InfSanciones) query.uniqueResult();
		return infSanciones;
	}

}
