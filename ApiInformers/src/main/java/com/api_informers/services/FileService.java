package com.api_informers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api_informers.domain.File;

@Service
public class FileService {
	
	public File createFileMongoDB(String added_date,String title,String description, List<String> keywords, List<Object> data, Float size) {
	
	File file = new File(added_date,title,description, keywords, data,size);
	
	return file;
	}
	
	public File createFileSQL(String added_date,String title,String description, List<String> keywords, Float size) {
		
		File file = new File(added_date,title,description, keywords,size);
		
	return file;
	}
}
