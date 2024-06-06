package com.ApiRest.ApiClubNautico.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiRest.ApiClubNautico.entities.Barco;

@Repository
public interface BarcoDao extends JpaRepository<Barco, Long> {

	// List<Barco>findByIdSocio(Long idSocio);//esto me hace el select por detras
	List<Barco> findBySocioId(Long idSocio);
}
