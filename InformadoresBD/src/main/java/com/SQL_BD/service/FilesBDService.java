package com.SQL_BD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.repositories.FilesBDRepository;

@Service
public class FilesBDService {
	
	@Autowired
	FilesBDRepository fr;
	
	
	public File newFile(File file)
	{
		file.setPreviews(0);
		file.setDownloads(0);
		fr.save(file);
		
		return file;
	}
	

}
