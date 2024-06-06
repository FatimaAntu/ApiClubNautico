package com.ApiRest.ApiClubNautico.Dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ApiRest.ApiClubNautico.entities.Salidas;

@Repository
public interface SalidasDao extends JpaRepository<Salidas, Long> {

	Optional<Salidas> findById(Long idSalidas);
}
