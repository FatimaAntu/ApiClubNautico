package com.ApiRest.ApiClubNautico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ApiRest.ApiClubNautico.entities.Socio;

@Repository
public interface SocioDao extends JpaRepository<Socio, Long> {

}// con todo esto implementamos todo el CRUD de una entidad, de forma automatica
