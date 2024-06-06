package com.ApiRest.ApiClubNautico.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patron")

public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, length = 9, unique = true)
	private String dni;
    @JsonIgnore
	@OneToMany(mappedBy = "patron", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Salidas> salidas = new ArrayList<>();

}
