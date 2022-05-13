package com.informersBD.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informersBD.domain.File;
import com.informersBD.repositories.FilesBDRepository;

@Service
public class FilesBDService {
	
	@Autowired
	FilesBDRepository fr;
	
	public File newFile(File file) {
		
		fr.save(file);
		
		return file;
	}

}
