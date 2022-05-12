package com.filesBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filesBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	
	
}
