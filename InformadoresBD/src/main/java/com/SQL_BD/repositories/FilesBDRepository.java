package com.SQL_BD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;


@Repository
public interface FilesBDRepository extends JpaRepository<File, String>{
	
	public File[] findInformerUserIdById(String id);
	public File[] findByInformerUserId(Informer informer);
	

	

}
