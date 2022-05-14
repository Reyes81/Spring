package com.api_informers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api_informers.domain.File;

@Service
public class FileService {
	
	public File createFileMongoDB(String title,String description, String keywords, List<Object> data, Integer size) {
	
	List <String> keywords_list = new ArrayList<String>();
	keywords_list.add(keywords);
	File file = new File(title,description, keywords_list, data,size);
	
	return file;
	}
	
}
