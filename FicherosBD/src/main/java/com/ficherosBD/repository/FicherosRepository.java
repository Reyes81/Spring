package com.ficherosBD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ficherosBD.domain.Fichero;

public interface FicherosRepository  extends MongoRepository<Fichero, String>{

	
	
}
