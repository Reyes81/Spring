package com.SQL_BD.service;

import java.util.Optional;

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
		File file_update = fr.getById(file.getId());
		file_update.setDownloads(file.getDownloads());
		file_update.setPreviews(file.getPreviews());
		file_update.setValidator(file.getValidator());
		fr.save(file_update);
		return file_update;
	}
	
	public File updateFileValidator(File file)
	{
		File file_update = fr.getById(file.getId());
		file_update.setValidator(file.getValidator());
		fr.save(file_update);
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
	
	
	public File[] findByUserId(Informer informer) {
		File[] files = fr.findByInformerUserId(informer);
		
		return files;
	}
	
	public Boolean deleteFile(String id) {
		boolean delete = false;
		Optional<File> file_delete = fr.findById(id);
		
		if(file_delete.isPresent()) {
			fr.deleteById(id);
			delete = true;
		}
		return delete;
	}

}
