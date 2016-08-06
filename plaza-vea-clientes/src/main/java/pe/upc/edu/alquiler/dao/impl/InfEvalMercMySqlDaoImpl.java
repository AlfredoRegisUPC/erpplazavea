package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.InfEvalMercDao;
import pe.upc.edu.alquiler.model.InfRedSoc;
import pe.upc.edu.alquiler.model.InfSanciones;
import pe.upc.edu.spring.configuration.AbstractDao;
@Transactional
@Repository("infEvalMercDao")
public class InfEvalMercMySqlDaoImpl extends AbstractDao implements InfEvalMercDao {

	@Override
	public InfRedSoc obtenerInfEvalMerc(long idEvaluacion) throws Exception {
		Query query = getSession().createQuery("from InfRedSoc where idEvaluacion= :eval");
        query.setString("eval", String.valueOf(idEvaluacion));
        InfRedSoc infEvalMerc = (InfRedSoc) query.uniqueResult();
		return infEvalMerc;
	}

	@Override
	public Integer registrarInfRedSoc(InfRedSoc infRedSoc) throws Exception {
		Query query = getSession().createQuery("select max(idInfEvalMerc) from InfRedSoc");
		Integer idInfEvalMerc = 1;
		
		if(query.uniqueResult() != null)
			idInfEvalMerc = Integer.parseInt(query.uniqueResult().toString())+1;
        
		infRedSoc.setIdInfEvalMerc((long)idInfEvalMerc);
		infRedSoc.setCodInfRedSoc(infRedSoc.getEvaluacion().getSolicitud().getCodSolicitud().replace("SOL", "IRD"));

        getSession().save(infRedSoc);
        
        return idInfEvalMerc;
	}

}
