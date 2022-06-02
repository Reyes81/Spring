package com.pf_persistencia_no_relacional.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pf_persistencia_no_relacional.domain.File;

public interface FileRepository  extends MongoRepository<File, String>{
	
	public List<File> findByKeywordsContainingOrderByAddeddateDesc(String keyword);
	
	public List<File> findByKeywordsContaining(String keyword);
	
	public List<File> findByKeywordsContainingOrderBySizeDesc(String keyword);
	
	public List<File> findByStatus(String status);

	public File getById(String id);
	public File findByTitle(String name);
	
}
