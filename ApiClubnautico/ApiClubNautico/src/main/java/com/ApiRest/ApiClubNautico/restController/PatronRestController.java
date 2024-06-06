package com.ApiRest.ApiClubNautico.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApiRest.ApiClubNautico.entities.Patron;
import com.ApiRest.ApiClubNautico.services.PatronServices;

@RestController
@RequestMapping("patron") // esto es la url
public class PatronRestController {

	@Autowired // inyeccion de dependencia
	private PatronServices patronServices;

	@PostMapping // creamos
	public ResponseEntity<Patron> crearPatron(@RequestBody Patron patron) {
		patronServices.createPatron(patron);
		return new ResponseEntity<Patron>(patron, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Patron>> getPatron() {
		List<Patron> patron = patronServices.findAllPatron();
		return ResponseEntity.ok(patron);

	}

	@GetMapping("/{idPatron}")
	public ResponseEntity<Patron> buscarPatronPorId(@PathVariable Long idPatron) {
		Patron patron = patronServices.findPatron(idPatron);
		if (patron != null) {
			return ResponseEntity.ok(patron);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{idPatron}") // actualizamos
	public ResponseEntity<Patron> actualizaPatron(@PathVariable Long idPatron, @RequestBody Patron patron) {
		patronServices.updatePatron(idPatron, patron);
		return new ResponseEntity<Patron>(patron, HttpStatus.OK);

	}

	@DeleteMapping("/{idPatron}") // eliminamos
	public ResponseEntity<?> eliminarPatron(@PathVariable Long idPatron) {
		patronServices.deletePatronById(idPatron);
		return new ResponseEntity<Patron>(HttpStatus.OK);
	}
}
