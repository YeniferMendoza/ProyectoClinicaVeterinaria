package es.ieslaarboleda.clinicaveterinaria.dao;

import java.util.List;

import es.ieslaarboleda.clinicaveterinaria.model.Mascota;

public interface MascotaDAO {
	
	boolean addMascota(Mascota mascota);
	
	boolean updateMascota(Mascota mascota);
	
	boolean deleteMascota(int id);
	
	List<Mascota> getMascotas();

}
