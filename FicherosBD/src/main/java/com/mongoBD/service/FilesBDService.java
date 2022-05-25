package com.mongoBD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		for(File file:files) {
			System.out.println("ID: " + file.getId() + "\n");
			System.out.println("Title: " + file.getTitle() + "\n");
			System.out.println("Description: " + file.getDescription() + "\n");
			System.out.println("InformerId: " + file.getInformerId() + "\n");
		}
		
		return files;
	}
	
	public File updateFile(File file)
	{
		
		System.out.println("Validator ID " + file.getInValidatorId());
		File file_update = fr.save(file);
		
		return file_update;
	}
	
	public void deleteFile(String id) {
		
		this.fr.deleteById(id);
	}
	
	public File[] getPendingFiles()
	{
		File[] files = fr.findByStatus("PENDIENTE_REVISION");
		return files;
	}
	
	public File getFileId(String id)
	{
		File file = fr.getById(id);
		return file;
	}
	
}
