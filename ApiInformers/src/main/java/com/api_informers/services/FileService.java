package com.api_informers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api_informers.domain.File;

@Service
public class FileService {
	
	public File createFileMongoDB(String title,String description, List<String> keywords, List<Object> data, Integer size) {
	
	File file = new File(title,description, keywords, data,size);
	
	return file;
	}
	
}
