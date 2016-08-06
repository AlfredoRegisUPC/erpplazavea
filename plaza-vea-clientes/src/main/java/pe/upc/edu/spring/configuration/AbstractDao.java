package pe.upc.edu.spring.configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {
	
	@Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void registrar(Object entity) {
        getSession().persist(entity);
    }
    
    public void actualizar(Object entity) {
        getSession().update(entity);
    }
 
    public void eliminar(Object entity) {
        getSession().delete(entity);
    }
}
