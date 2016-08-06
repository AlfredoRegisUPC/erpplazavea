package pe.upc.edu.cliente.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.cliente.dao.LocalDao;
import pe.upc.edu.cliente.model.Local;
import pe.upc.edu.cliente.model.Ubigeo;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("localDao")
public class LocalMySqlDaoImpl extends AbstractDao implements LocalDao {
	
	@SuppressWarnings("unchecked")
	public List<Local> listarLocales() throws Exception {
		Query query = getSession().createQuery("select l.idLocal, "
				+ "concat(l.nombre,'-',l.direccion,'-',u.distrito,'-',u.provincia,'-',u.departamento) as nombre,"
				+ " l.direccion, l.ubigeo "
				+ "from Local l INNER JOIN l.ubigeo u");
        List<Object[]> list = query.list();
        List<Local> listLocales = new ArrayList<Local>();
        for(Object[] local: list){
        	Local l = new Local();
        	l.setIdLocal((long)local[0]);
        	l.setNombre(local[1].toString());
        	l.setDireccion(local[2].toString());
        	l.setUbigeo((Ubigeo)local[3]);
        	listLocales.add(l);
        }
        return listLocales;
	}
	
}
