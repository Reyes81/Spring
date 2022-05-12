package com.example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.domain.File;


public interface FileRepository  extends MongoRepository<File, String>{

	
	
}