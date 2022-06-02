package com.pf_persistencia_no_relacional.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.pf_persistencia_no_relacional.domain.File;
import com.pf_persistencia_no_relacional.repository.FileRepository;

@Service
public class FileService {
	
	@Autowired 
	FileRepository fr;
	
	public List<File> findAll(){
		
		return this.fr.findAll();
	}
	
	public void deleteAll() {
		fr.deleteAll();
	}
	
	public File create(File file) {
		
		
		File file_save = fr.save(file);
		return file_save;
	}
	
	public void createAll(List<File> posts) {
		this.fr.saveAll(posts);
	}
	
	public File updateFile(File file)
	{
		File file_update = fr.save(file);
		
		return file_update;
	}
	
	public void deleteFile(String id) {
		
		this.fr.deleteById(id);
	}
	
	public List<File> getPendingFiles()
	{
		List<File> files = fr.findByStatus("PENDIENTE_REVISION");
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
	
	public File getFileTitle(String title)
	{
		File file = fr.findByTitle(title);
		return file;
	}
	
	//Método para obtener el listado de ficheros por keyword ordenados
	public List<File> getFilesByKeyWords(Integer opcion, String keyword) {
		
		List<File> files = null;
		switch (opcion) {
		
			case 1:
				files = fr.findByKeywordsContaining(keyword);
				break;
			
			case 2:
				files = fr.findByKeywordsContainingOrderBySizeDesc(keyword);
				break;
			case 3:
				files = fr.findByKeywordsContainingOrderByAddeddateDesc(keyword);
		}
		
		return files;
	}
	
}
