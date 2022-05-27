package com.mongoBD.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongoBD.domain.File;

public interface FilesRepository  extends MongoRepository<File, String>{

	public List<File> findByinformerId(Integer informer_id);
	
	//Da error en el OrderBy
	public List<File> findByKeywordsContainingOrderByAddeddateDesc(String keyword);
	
	public List<File> findByKeywordsContaining(String keyword);
	
	public List<File> findByKeywordsContainingOrderBySizeDesc(String keyword);
	
	public File[] findByStatus(String status);

	public File getById(String id);
	
}
