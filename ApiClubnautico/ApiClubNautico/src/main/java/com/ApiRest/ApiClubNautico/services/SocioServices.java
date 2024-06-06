package com.ApiRest.ApiClubNautico.services;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ApiRest.ApiClubNautico.Dao.SocioDao;
import com.ApiRest.ApiClubNautico.entities.Socio;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service // para marcar la clase como un servicio de Spring

public class SocioServices {
//declaramos variable final para socioDao
//que sera utilizada para realizar las opraciones de acceso a datos Dao	
	private final SocioDao socioDao;
	private BarcoServices barcoServices;
	private SalidasServices salidasServices;

	// metodo para guardar un nuevo socio
	@Transactional
	public void createSocio(Socio socio) {
		socioDao.save(socio);// save=para guardar al socio en la bbdd
	}

	// busca un socio por su Id
	// el método findById de SocioDao devuelve un Optional,usa getpara obtener el
	// Socio.
	@Transactional
	public Socio findSocioById(Long id) {
		Optional<Socio> optionalSocio = socioDao.findById(id);
		if (optionalSocio.isPresent()) {
			return optionalSocio.get();
		} else {
			// Manejar el caso en que no se encuentra ningún socio con el ID especificado
			throw new EntityNotFoundException("No se encontró ningún socio con el ID proporcionado: " + id);
		}

	}

	@Transactional
	public List<Socio> findAllSocio() {
		return socioDao.findAll();// me devuelve una lista de todos los socios
	}

	@Transactional
	public void updateSocio(Long idSocio, Socio socio) {
		/*
		 * este método busca un socio existente en la base de datos por su ID, actualiza
		 * sus campos dni y nombre con los valores del socio proporcionado, y luego
		 * guarda los cambios en la base de datos(save)
		 */
		Optional<Socio> optionalSocio = socioDao.findById(idSocio);
		if (optionalSocio.isPresent()) {
			Socio existingSocio = optionalSocio.get();
			existingSocio.setDni(socio.getDni());
			existingSocio.setNombre(socio.getNombre());
			socioDao.save(existingSocio);
		} else {
			// Manejar el caso en que el socio no se encuentra
			throw new EntityNotFoundException("No se encontró un socio con el Id proporcionado");
		}
	}

	@Transactional
	public void deleteSocioById(Long idSocio) {
		Optional<Socio> optionalSocio = socioDao.findById(idSocio);
		if (optionalSocio.isPresent()) {
			socioDao.deleteById(idSocio);

		} else {
			// Manejar el caso en que el socio no se encuentra
			throw new EntityNotFoundException("No se encontró un socio con el Id proporcionado");

		}
	}

}
