package com.pf_persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pf_persistencia.domain.File;


@Repository
public interface FilesRepository extends JpaRepository<File, String>{
	
	//public File[] findInformerUserIdById(String id);
	
	//@Query("SELECT f FROM File f WHERE f.informer_id = ?1")
	//public File[] findByInformerUserId(Integer id);
	

}
