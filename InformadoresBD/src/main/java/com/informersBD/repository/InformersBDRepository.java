package com.informersBD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informersBD.domain.Informer;

@Repository
public interface InformersBDRepository  extends JpaRepository<Informer, Integer> {
	
	public List<Informer> findByStatus(Informer.Status status);
	
	public List<Informer> findByQuote(Double quote);
	

}
