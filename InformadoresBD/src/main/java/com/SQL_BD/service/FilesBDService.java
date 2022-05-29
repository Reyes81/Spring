package com.SQL_BD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.repositories.FilesBDRepository;

@Service
public class FilesBDService {
	
	@Autowired
	FilesBDRepository fr;
	@Autowired
	InformersBDService is;
	
	
	public File newFile(File file)
	{
		fr.save(file);
		
		return file;
	}
	
	public File updateFile(File file)
	{
		File file_update = fr.save(file);
		
		return file_update;
	}
	

	public File[] findInformerByFileId(String id)
	{
		File[] files = fr.findInformerUserIdById(id);
		return files;
	}
	
	public File findById(String id) {
		
		File file = fr.findById(id).get();
		
		return file;
	}
	
	
	//Adaptar por mapeo OneToMany
	public File[] findByUserId(Informer informer) {
		File[] files = fr.findByInformerUserId(informer);
		
		return files;
	}

}
