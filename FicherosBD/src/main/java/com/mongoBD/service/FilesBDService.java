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
		
		
		File file_save = fr.save(file);
		return file_save;
	}
	
	public void createAll(List<File> posts) {
		this.fr.saveAll(posts);
	}
	
	public List<File> findByInformerId(Integer informerId){
		
		System.out.println("Estoy en el findByInformerId");
		
		List<File> files = fr.findByinformerId(informerId);
		
		//List<File> files = fr.findAll();
		for(File file:files) {
			System.out.println("ID: " + file.getId() + "\n");
			System.out.println("Title: " + file.getTitle() + "\n");
			System.out.println("Description: " + file.getDescription() + "\n");
			System.out.println("InformerId: " + file.getInformerId() + "\n");
		}
		
		return files;
	}
}
