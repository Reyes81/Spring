package com.SQL_BD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SQL_BD.domain.File;


@Repository
public interface FilesBDRepository extends JpaRepository<File, String>{
	
	public File[] findInformerUserIdById(String id);

}
