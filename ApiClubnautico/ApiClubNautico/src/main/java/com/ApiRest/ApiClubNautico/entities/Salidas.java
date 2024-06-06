package com.ApiRest.ApiClubNautico.entities;

import java.sql.Date;
import java.sql.Time;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "salidas")

public class Salidas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(nullable = false)
	private Date fecha;

	@Column(nullable = false)
	private Time hora;

	@Column(nullable = false)
	private String destino;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "barco_id")
	private Barco barco;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "patron_id")
	private Patron patron;

}
