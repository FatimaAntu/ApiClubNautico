package com.ApiRest.ApiClubNautico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiRest.ApiClubNautico.entities.Patron;

@Repository
public interface PatronDao extends JpaRepository<Patron, Long> {

}
