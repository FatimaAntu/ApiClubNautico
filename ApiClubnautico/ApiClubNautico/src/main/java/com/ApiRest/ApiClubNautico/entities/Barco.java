package com.ApiRest.ApiClubNautico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barco")
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private Long numeroAmarre;

	@Column(nullable = false)
	private Long matricula;

	@Column(nullable = false)
	private double cuota;
    @JsonIgnore//esta anotacion evito que en los get me devolviera bucles
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSocio")
	private Socio socio; 

}
