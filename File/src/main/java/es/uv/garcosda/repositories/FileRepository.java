package es.uv.garcosda.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.uv.garcosda.domain.File;

public interface FileRepository  extends MongoRepository<File, String>{

	
	
}
