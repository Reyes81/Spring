package com.mongoBD.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongoBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	
	
}
