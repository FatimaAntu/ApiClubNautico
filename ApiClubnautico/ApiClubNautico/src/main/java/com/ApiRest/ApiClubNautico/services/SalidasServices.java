package com.ApiRest.ApiClubNautico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ApiRest.ApiClubNautico.Dao.BarcoDao;
import com.ApiRest.ApiClubNautico.Dao.PatronDao;
import com.ApiRest.ApiClubNautico.Dao.SalidasDao;
import com.ApiRest.ApiClubNautico.entities.Barco;
import com.ApiRest.ApiClubNautico.entities.Patron;
import com.ApiRest.ApiClubNautico.entities.Salidas;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class SalidasServices {

	private final SalidasDao salidasDao;
	private final PatronDao patronDao;
	private final BarcoDao barcoDao;

	@Transactional
	public void createSalidas(Salidas salidas) {
		salidasDao.save(salidas);
	}
	
	@Transactional
	public Salidas findSalidas(Long id) {
		Optional<Salidas> optionalSalidas = salidasDao.findById(id);
		if (optionalSalidas.isPresent()) {
			return optionalSalidas.get();
		} else {
			// Manejar el caso en que no se encuentra ningún socio con el ID especificado
			throw new EntityNotFoundException("No se encontró ninguna salida con el ID proporcionado: " + id);
		}}


	public List<Salidas> findAllSalidas() {
		return salidasDao.findAll();
	}

	@Transactional
	public void updateSalidas(Long idSalidas, Salidas salidas) {
		Optional<Salidas> optionalSalidas = salidasDao.findById(idSalidas);
		if (optionalSalidas.isPresent()) {
			Salidas existingSalidas = optionalSalidas.get();
			existingSalidas.setFecha(salidas.getFecha());
			existingSalidas.setHora(salidas.getHora());
			existingSalidas.setDestino(salidas.getDestino());

		} else {
			// Manejar el caso en que el socio no se encuentra
			throw new EntityNotFoundException("No se encontró una salida con el id proporcionado");
		}

	}

	@Transactional
	public void deleteSalidasById(Long idSalidas) {
		Optional<Salidas> optionalSalidas = salidasDao.findById(idSalidas);

		if (optionalSalidas.isPresent()) {
			salidasDao.deleteById(idSalidas);
		} else {
			throw new EntityNotFoundException("No se encontró una salida con el id proporcionado");

		}

	}

	public void añadirPatronYBarcoaSalida(Long idPatron, Long idBarco, Long idSalidas) {

		Optional<Salidas> optionalSalida = salidasDao.findById(idSalidas);
		Optional<Patron> optionalPatron = patronDao.findById(idPatron);
		Optional<Barco> optionalBarco = barcoDao.findById(idBarco);

		if (optionalPatron.isPresent() && optionalBarco.isPresent() && optionalSalida.isPresent()) {
			Patron patron = optionalPatron.get();
			Barco barco = optionalBarco.get();
			Salidas salida = optionalSalida.get();
			salida.setBarco(barco);
			salida.setPatron(patron);
			salidasDao.save(salida);
		} else {
			throw new EntityNotFoundException("Error al asignar barco o patron a salida");
		}
	}

}
