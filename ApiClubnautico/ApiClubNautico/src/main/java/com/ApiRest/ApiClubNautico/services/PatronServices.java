package com.ApiRest.ApiClubNautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ApiRest.ApiClubNautico.Dao.PatronDao;
import com.ApiRest.ApiClubNautico.entities.Patron;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Service
public class PatronServices {

	private final PatronDao patronDao;

	

	@Transactional
	public void createPatron(Patron patron) {
		patronDao.save(patron);
	}

	@Transactional
	public Patron findPatron(Long id) {
		Optional<Patron> optionalPatron = patronDao.findById(id);
		if (optionalPatron.isPresent()) {
			return optionalPatron.get();
		} else {
			// Manejar el caso en que no se encuentra ningún socio con el ID especificado
			throw new EntityNotFoundException("No se encontró ningún patrón con el ID proporcionado: " + id);
		}
	}

	@Transactional
	public List<Patron> findAllPatron() {
		return patronDao.findAll();
	}

	@Transactional
	public void updatePatron(Long idPatron, Patron patron) {
		Optional<Patron> optionalPatron = patronDao.findById(idPatron);
		if (optionalPatron.isPresent()) {
			Patron existingPatron = optionalPatron.get();
			existingPatron.setDni(patron.getDni());
			existingPatron.setNombre(patron.getNombre());
			patronDao.save(existingPatron);
		} else {
			// sino lo encuentra
			throw new EntityNotFoundException("No se encontró ningún patrón con el id proporcionado");
		}
	}

	@Transactional
	public void deletePatronById(Long idPatron) {
		Optional<Patron> optionalPatron = patronDao.findById(idPatron);
		if (optionalPatron.isPresent()) {
			patronDao.deleteById(idPatron);
		} else {
			throw new EntityNotFoundException("No se encontró ningún patrón con el id proporcionado");
		}

	}

}
