package es.ieslaarboleda.clinicaveterinaria.model;

public class Cliente {
	private int id;
	private String nombre;
	private String telefono;
	private String email;
	
	public Cliente(int id, String nombre, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return nombre ;
	}
	
	

}
