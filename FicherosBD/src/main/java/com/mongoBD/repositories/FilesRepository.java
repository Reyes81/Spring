package com.mongoBD.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongoBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	public List<File> findByInformerId(Integer informer_id);
	
}
