package com.SQL_BD.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;


@Repository
public interface FilesBDRepository extends JpaRepository<File, Integer>{
	
	public File[] findInformerUserIdById(String id);

}
