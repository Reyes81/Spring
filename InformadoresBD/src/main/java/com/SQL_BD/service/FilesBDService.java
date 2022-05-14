package com.SQL_BD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.File;
import com.SQL_BD.repositories.FilesBDRepository;

@Service
public class FilesBDService {
	
	@Autowired
	FilesBDRepository fr;
	
	public File newFile(String id) {
		
		File file = new File(id);
		fr.save(file);
		
		return file;
	}

}
