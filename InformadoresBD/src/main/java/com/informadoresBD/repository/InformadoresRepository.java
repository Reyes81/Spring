package com.informadoresBD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informadoresBD.domain.Informador;

@Repository
public interface InformadoresRepository  extends JpaRepository<Informador, Integer> {
	
	public List<Informador> findByStatus(Informador.Status status);
	
	public List<Informador> findByQuote(Double quote);
	

}
