package com.informadoresBD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informadoresBD.domain.Fichero;

@Repository
public interface FilesRepository extends JpaRepository<Fichero, Integer> {

}
