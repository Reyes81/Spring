package com.pf_persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pf_persistencia.domain.Informer;

@Repository
public interface InformersRepository  extends JpaRepository<Informer, Integer> {
	
	public List<Informer> findByStatus(Informer.Status status);
	
	public List<Informer> findByQuote(Double quote);
	
	public Informer findByeMail(String email);
	
	public Informer findByName(String name);
	
}

