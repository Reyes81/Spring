package com.mongoBD.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
		for(File f:files)
		{
			List<Object> all_data = f.getData();
			List<Object> limit_data = new ArrayList<Object>();
			
			//Limitar observaciones a 10
			if(all_data.size() > 10){
				for(int i = 0; i < 10; i++){
					limit_data.add(all_data.get(i));
				}
				f.setData(limit_data);
			}
			
		}
		return files;
	}
	
	public File getFileId(String id)
	{
		File file = fr.getById(id);
		return file;
	}
	
	//Método para obtener el listado de ficheros del repo por keyword ordenados por fecha
	public List<File> getFilesByKeyWords(Integer opcion, String keyword) {
		
		List<File> files = null;
		switch (opcion) {
		
			case 1:
				//Falta ordenar por fecha
				files = fr.findByKeywordsContaining(keyword);
				break;
			
			case 2:
				files = fr.findByKeywordsContainingOrderBySizeDesc(keyword);
				break;
		}
		
		return files;
	}
	
}
