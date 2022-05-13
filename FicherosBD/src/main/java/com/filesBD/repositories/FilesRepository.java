package com.filesBD.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filesBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	
	
}
