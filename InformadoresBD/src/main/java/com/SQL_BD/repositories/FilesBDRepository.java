package com.SQL_BD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SQL_BD.domain.File;


@Repository
public interface FilesBDRepository extends JpaRepository<File, String>{
	
	public File[] findInformerUserIdById(String id);
	
	@Query("SELECT f FROM File f WHERE f.informer_id = ?1")
	public File[] findByInformerUserId(Integer id);
	

}
