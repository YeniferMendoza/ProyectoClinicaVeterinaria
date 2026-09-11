package es.ieslaarboleda.clinicaveterinaria.dao;

import java.util.List;

import es.ieslaarboleda.clinicaveterinaria.model.Cliente;

public interface ClienteDAO {
	
	boolean addCliente(Cliente cliente);
	
	boolean updateCliente(Cliente cliente);
	
	boolean deleteCliente(int id);
	
	List<Cliente> getClientes();
	
}
