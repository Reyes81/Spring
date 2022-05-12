package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.File;
import com.example.repositories.FileRepository;



@Service
public class FileService {

	@Autowired FileRepository fr;
	
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
