package pe.upc.edu.alquiler.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.InfEstEmpDao;
import pe.upc.edu.alquiler.model.InfEstEmp;
import pe.upc.edu.alquiler.model.Locatario;
import pe.upc.edu.spring.configuration.AbstractDao;
@Transactional
@Repository("infEstEmpDao")
public class InfEstEmpMySqlDaoImpl extends AbstractDao implements InfEstEmpDao {

	@Override
	public InfEstEmp obtenerInfEstEmp(long idEvaluacion) throws Exception {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from InfEstEmp where idEvaluacion= :eval");
        query.setString("eval", String.valueOf(idEvaluacion));
        InfEstEmp infEstEmp = (InfEstEmp) query.uniqueResult();
		return infEstEmp;
	}

}
