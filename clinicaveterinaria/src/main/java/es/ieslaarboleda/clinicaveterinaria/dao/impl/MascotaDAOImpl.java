package es.ieslaarboleda.clinicaveterinaria.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ieslaarboleda.clinicaveterinaria.dao.ConexionDatabase;
import es.ieslaarboleda.clinicaveterinaria.dao.MascotaDAO;
import es.ieslaarboleda.clinicaveterinaria.model.Mascota;

public class MascotaDAOImpl implements MascotaDAO {

	@Override
	public boolean addMascota(Mascota mascota) {
		boolean resultado = false;
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "INSERT INTO MASCOTAS(nombre, especie, raza, edad, cliente_id) values (?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, mascota.getNombre());
			ps.setString(2, mascota.getEspecie());
			ps.setString(3, mascota.getRaza());
			ps.setInt(4, mascota.getEdad());
			ps.setInt(5, mascota.getCliente());
			resultado = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean updateMascota(Mascota mascota) {
		boolean resultado = false;
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "UPDATE MASCOTAS SET nombre = ?, especie = ?, raza = ?, edad = ?, cliente_id =? WHERE id= ?";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, mascota.getNombre());
			ps.setString(2, mascota.getEspecie());
			ps.setString(3, mascota.getRaza());
			ps.setInt(4, mascota.getEdad());
			ps.setInt(5, mascota.getCliente());
			ps.setInt(6, mascota.getId());
			resultado = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean deleteMascota(int id) {
		boolean resultado = false;
		try (Connection conexion = ConexionDatabase.getConexion()) {
			String query = "DELETE FROM MASCOTAS WHERE id = ?";
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
	public List<Mascota> getMascotas() {
		List<Mascota> mascotas = new ArrayList<Mascota>();
		try {
			Connection conexion = ConexionDatabase.getConexion();
			String query = "SELECT * FROM mascotas";
			ResultSet resultado = conexion.createStatement().executeQuery(query);
			while (resultado.next()) {
				mascotas.add(new Mascota(resultado.getInt("id"), resultado.getString("nombre"),
						resultado.getString("especie"), resultado.getString("raza"), resultado.getInt("edad"),
						resultado.getInt("cliente_id")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mascotas;
	}

}
