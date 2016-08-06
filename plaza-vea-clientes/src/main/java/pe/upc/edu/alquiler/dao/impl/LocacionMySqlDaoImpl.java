package pe.upc.edu.alquiler.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.edu.alquiler.dao.LocacionDao;
import pe.upc.edu.alquiler.model.Locacion;
import pe.upc.edu.spring.configuration.AbstractDao;

@Transactional
@Repository("locacionDao")
public class LocacionMySqlDaoImpl extends AbstractDao implements LocacionDao {
	
	@SuppressWarnings("unchecked")
	public List<Locacion> listarLocaciones(String tamaño, String ubicacion, String local) throws Exception {
		List<Locacion> listaLocaciones = new ArrayList<Locacion>();
		
		if((tamaño == null || tamaño.equals("") || tamaño.equals("null")) && (ubicacion == null || ubicacion.equals("") || ubicacion.equals("null")) && (local == null || local.equals("") || local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible'").list();
		}
		else if((tamaño == null || tamaño.equals("") || tamaño.equals("null")) && (ubicacion == null || ubicacion.equals("") || ubicacion.equals("null")) && (local != null && !local.equals("") && !local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and idLocal =" + Integer.valueOf(local)).list();
		}
		else if((tamaño == null || tamaño.equals("") || tamaño.equals("null")) && (ubicacion != null && !ubicacion.equals("") && !ubicacion.equals("null")) && (local != null && !local.equals("") && !local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and ubicacion ='"+ubicacion+"' and idLocal =" + Integer.valueOf(local)).list();
		}
		else if((tamaño != null && !tamaño.equals("") && !tamaño.equals("null")) && (ubicacion == null || ubicacion.equals("") || ubicacion.equals("null")) && (local != null && !local.equals("") && !local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and tipo = '" + tamaño+"' and idLocal =" + Integer.valueOf(local)).list();
		}
		else if((tamaño == null || tamaño.equals("") || tamaño.equals("null")) && (ubicacion != null && !ubicacion.equals("") && !ubicacion.equals("null")) && (local == null || local.equals("") || local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and ubicacion ='"+ubicacion+"'").list();
		}
		else if((tamaño != null && !tamaño.equals("") && !tamaño.equals("null")) && (ubicacion != null && !ubicacion.equals("") && !ubicacion.equals("null")) && (local == null || local.equals("") || local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and tipo = '" + tamaño +"' and ubicacion ='"+ubicacion+"'").list();
		}
		else if((tamaño != null && !tamaño.equals("") && !tamaño.equals("null")) && (ubicacion == null || ubicacion.equals("") || ubicacion.equals("null")) && (local == null || local.equals("") || local.equals("null"))){
			listaLocaciones = (List<Locacion>)getSession()
					.createQuery("from Locacion where estado = 'Disponible' and tipo = '" + tamaño+"'").list();
		}
		else if((tamaño != null && !tamaño.equals("") && !tamaño.equals("null")) && (ubicacion != null && !ubicacion.equals("") && !ubicacion.equals("null")) && (local != null && !local.equals("") && !local.equals("null"))){
		listaLocaciones = (List<Locacion>)getSession()
        							.createQuery("from Locacion where estado = 'Disponible' and tipo = '" + tamaño +
        									     "' and ubicacion ='"+ubicacion+"' and idLocal =" + Integer.valueOf(local)).list();
		}
		System.out.println("Las locaciones son " + listaLocaciones.size());
        return listaLocaciones;
	}

}
