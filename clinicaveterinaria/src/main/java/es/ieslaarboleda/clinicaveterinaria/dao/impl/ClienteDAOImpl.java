package es.ieslaarboleda.clinicaveterinaria.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ieslaarboleda.clinicaveterinaria.dao.ClienteDAO;
import es.ieslaarboleda.clinicaveterinaria.dao.ConexionDatabase;
import es.ieslaarboleda.clinicaveterinaria.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	@Override
	public boolean addCliente(Cliente cliente) {
		boolean resultado = false;

		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "INSERT INTO CLIENTES (nombre,telefono,email) values (?,?,?)";//PlaseHolder
			PreparedStatement ps = conexion.prepareStatement(query);//Objeto de Java.sql que nos permite hacer inserciones 
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getTelefono());
			ps.setString(3, cliente.getEmail());
			resultado = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean updateCliente(Cliente cliente) {
		boolean resultado = false;
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "UPDATE CLIENTES SET nombre = ?, telefono = ?, email = ? WHERE id = ?";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getTelefono());
			ps.setString(3, cliente.getEmail());
			ps.setInt(4, cliente.getId());

			resultado = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean deleteCliente(int id) {
		boolean resultado = false;
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "DELETE FROM CLIENTES where id= ?";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			resultado = ps.executeUpdate() > 0;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "SELECT * FROM CLIENTES";
			ResultSet resultado = conexion.createStatement().executeQuery(query);
			while (resultado.next()) {
				clientes.add(new Cliente(resultado.getInt("id"), resultado.getString("nombre"),
						resultado.getString("telefono"), resultado.getString("email")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
	}

}
