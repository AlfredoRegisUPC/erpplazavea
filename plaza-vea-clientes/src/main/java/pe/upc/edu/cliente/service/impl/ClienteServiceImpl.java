package pe.upc.edu.cliente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.edu.cliente.dao.LocalDao;
import pe.upc.edu.cliente.model.Local;
import pe.upc.edu.cliente.service.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private LocalDao localDao;
	
	@Override
	public List<Local> listarLocales() throws Exception {
		// TODO Auto-generated method stub
		return localDao.listarLocales();
	}

}
