package com.ApiRest.ApiClubNautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ApiRest.ApiClubNautico.Dao.BarcoDao;
import com.ApiRest.ApiClubNautico.Dao.SocioDao;
import com.ApiRest.ApiClubNautico.entities.Barco;
import com.ApiRest.ApiClubNautico.entities.Socio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@Service
public class BarcoServices {

	private final BarcoDao barcoDao;
	private final SocioDao socioDao;

	
	@Transactional
	public void createBarco(Barco barco) {
		barcoDao.save(barco);

	}

	@Transactional
	public Barco findBarco(Long id) {
		Optional<Barco> optionalBarco = barcoDao.findById(id);
		if (optionalBarco.isPresent()) {
			return optionalBarco.get();
		} else {
			throw new EntityNotFoundException("No se encontró el barco con el id proporcionado");
		}
	}

	@Transactional
	public List<Barco> findAllBarco() {
		return barcoDao.findAll();
	}

	@Transactional
	public List<Barco> listaBarcosByIdSocio(Long idSocio) {
		return barcoDao.findBySocioId(idSocio); // Devuelve la lista de barcos asociada a un socio

	}

	@Transactional
	public void updateBarco(Long idBarco, Barco barco) {
		Optional<Barco> optionalBarco = barcoDao.findById(idBarco);
		if (optionalBarco.isPresent()) {
			Barco existingBarco = optionalBarco.get();
			existingBarco.setNombre(barco.getNombre());
			existingBarco.setNumeroAmarre(barco.getNumeroAmarre());
			existingBarco.setMatricula(barco.getMatricula());
			existingBarco.setCuota(barco.getCuota());
			barcoDao.save(existingBarco);
		} else {
			// Manejar el caso en que el socio no se encuentra
			throw new EntityNotFoundException("No se encontró el barco con el id proporcionado");
		}

	}

	@Transactional
	public void deleteBarcoById(Long idBarco) {
		Optional<Barco> optionalBarco = barcoDao.findById(idBarco);
		if (optionalBarco.isPresent()) {
			barcoDao.deleteById(idBarco);
		} else {
			throw new EntityNotFoundException("No se encontró el barco con el id proporcionado");
		}

	}

	public void añadirBarcoaSocio(Long idSocio, Long idBarco) {
		Optional<Socio> optionalSocio = socioDao.findById(idSocio);
		if (optionalSocio.isPresent()) {
			Socio socio = optionalSocio.get();
			Optional<Barco> optionalBarco = barcoDao.findById(idBarco);
			if (optionalBarco.isPresent()) {
				Barco barco = optionalBarco.get();
				barco.setSocio(socio);
				barcoDao.save(barco);
			} else {
				throw new EntityNotFoundException("No se encontró el barco con el id proporcionado");
			}
		} else {
			throw new EntityNotFoundException("No se encontró el socio con el id proporcionado");
		}
	}
}
