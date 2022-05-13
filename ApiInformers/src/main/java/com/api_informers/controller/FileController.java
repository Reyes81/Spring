package com.api_informers.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.api_informers.domain.File;
import com.api_informers.domain.Informer;
import com.api_informers.services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/informadores")
public class FileController {

	static final String uriNewFileSQL = "http://localhost:8082/api/informadoresBD/crearFichero";
	@Autowired FileService fs;
	
	@PostMapping("/newFile")
	public ResponseEntity<File> createPost(@RequestParam MultipartFile file,  @RequestParam String added_date,@RequestParam String title, 
										   @RequestParam String description, @RequestParam List<String> keywords,
										   @RequestParam Float size,
										   HttpServletRequest request) throws IOException {
		
		String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<Object> data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		//File f = fs.create(new File(added_date,title, description, keywords, data,size));
		
		File f = fs.createFileMongoDB(added_date, title, description, keywords, data, size);
		System.out.println("Numero de descargas: " + f.getDownloads());
		
		File f2 = fs.createFileSQL(added_date, title, description, keywords, size);
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				uriNewFileSQL,
				f2,
				File.class);
		return new ResponseEntity<>(f, HttpStatus.OK);
	} 
	
	/*
	@GetMapping()
	public ResponseEntity<List<File>> getAll(HttpServletRequest request) {
		List<File> files = fs.findAll();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	*/
}
