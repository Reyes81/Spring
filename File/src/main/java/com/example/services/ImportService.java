package com.example.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.domain.File;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoBulkWriteException;


@Service
public class ImportService {
	
	@Autowired
    private FileService fs;

	private List<File> generateMongoDocs(List<String> lines) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	    List<File> docs = new ArrayList<>();
	    for (String e : lines) {
	    	File x = mapper.readValue(e, File.class);
	        docs.add(x);
	    }
	    System.out.println("[I] ImportService: readed "+docs.size()+" items");
	    return docs;
	}
	
	private int insert(List<File> mongoDocs) {
	    try {
	    	fs.createAll(mongoDocs); 
	        return 1;
	    } 
	    catch (DataIntegrityViolationException e) {
	        if (e.getCause() instanceof MongoBulkWriteException) {
	            return ((MongoBulkWriteException) e.getCause())
	              .getWriteResult()
	              .getInsertedCount();
	        }
	        return 0;
	    }
	}
		
	public int doImport(Resource resource) throws JsonMappingException, JsonProcessingException {
	    ArrayList<String> jsonlines = new ArrayList<>();
	    try (Scanner s = new Scanner(resource.getFile())) {
	        while (s.hasNext()) {
	        	jsonlines.add(s.nextLine());
	        }
	    }
	    catch(IOException e) {
	    	System.out.println("[ERROR] data file not found");
	    }
	    
	    List<File> mongoDocs = generateMongoDocs(jsonlines);
	    return insert(mongoDocs);
	}
	
}

