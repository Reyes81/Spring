package com.filesBD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.filesBD.domain.File;
import com.filesBD.repositories.FilesRepository;

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

}
