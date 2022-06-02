package com.pf_persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pf_persistencia.domain.Informer;

@Repository
public interface InformersRepository  extends JpaRepository<Informer, Integer> {
	
	public List<Informer> findByStatus(Informer.Status status);
	
	public List<Informer> findByQuote(Double quote);
	
	public Informer findByeMail(String email);
	
	public Informer findByName(String name);
	
	@Query("SELECT COUNT(id), status FROM Informer GROUP BY status")
	public List<Object[]> countInformerByStatus();
	
	/*@Query("SELECT i FROM Informer i JOIN FETCH files WHERE HAVING COUNT(files) > 5")
	public List<Informer> findInformerByNFiles();*/
	
	
}

