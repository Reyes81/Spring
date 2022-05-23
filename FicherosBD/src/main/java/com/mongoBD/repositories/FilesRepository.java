package com.mongoBD.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongoBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	public List<File> findByinformerId(Integer informer_id);
	
	public File[] findByStatus(String status);
	
}
