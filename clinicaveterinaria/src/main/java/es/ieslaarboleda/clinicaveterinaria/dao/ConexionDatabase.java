package es.ieslaarboleda.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDatabase {
	private static final String URL = "jdbc:mysql://localhost:3306/clinica_veterinaria";
	private static final String USER = "root";
	private static final String PASSWORD = "2001";
	
	public static Connection getConexion() {
		try {
			return DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
