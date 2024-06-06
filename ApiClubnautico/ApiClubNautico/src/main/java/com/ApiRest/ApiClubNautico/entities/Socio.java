package com.ApiRest.ApiClubNautico.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // me crea constructor vacio
@AllArgsConstructor // me crea constructor parametrizado
@Data // getters y setters
@Entity
@Table(name = "socio")

public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, length = 9, unique = true)
	private String dni;
	
//orphanRemoval:para q los barcos cuando se eliminen de la lista tb lo hagan de la bbdd
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Barco> barcos = new ArrayList<>();

}
