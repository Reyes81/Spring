package com.informadoresBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informadoresBD.domain.Informador;

@Repository
public interface InformadoresRepository  extends JpaRepository<Informador, Integer> {

}
