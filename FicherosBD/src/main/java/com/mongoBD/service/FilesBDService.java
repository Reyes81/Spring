package com.mongoBD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoBD.domain.File;
import com.mongoBD.repositories.FilesRepository;

@Service
public class FilesBDService {
	
	@Autowired 
	FilesRepository fr;
	
	public List<File> findAll(){
		return this.fr.findAll();
	}
	
	public File create(File file) {
		return this.fr.save(file);
	}
	
	public void createAll(List<File> posts) {
		this.fr.saveAll(posts);
	}
	
	public List<File> findByInformerId(Integer informerId){
		
		List<File> files = fr.findByInformerId(informerId);
		
		return files;
	}

}
