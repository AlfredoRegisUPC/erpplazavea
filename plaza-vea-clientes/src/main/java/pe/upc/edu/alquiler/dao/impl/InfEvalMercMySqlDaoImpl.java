package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.InfEvalMercDao;
import pe.upc.edu.alquiler.model.InfEvalMerc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.spring.configuration.AbstractDao;
@Transactional
@Repository("infEvalMercDao")
public class InfEvalMercMySqlDaoImpl extends AbstractDao implements InfEvalMercDao {

	@Override
	public InfEvalMerc obtenerInfEvalMerc(long idEvaluacion) throws Exception {
		Query query = getSession().createQuery("from InfEvalMerc where idEvaluacion= :eval");
        query.setString("eval", String.valueOf(idEvaluacion));
        InfEvalMerc infEvalMerc = (InfEvalMerc) query.uniqueResult();
		return infEvalMerc;
	}

}
