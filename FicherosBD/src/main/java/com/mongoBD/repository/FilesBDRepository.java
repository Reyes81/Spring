package com.mongoBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongoBD.domain.File;


public interface FilesBDRepository  extends MongoRepository<File, String>{
	
}

	
	