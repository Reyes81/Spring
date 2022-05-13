package com.api_informers.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.api_informers.domain.File;
import com.api_informers.services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/informador")
public class FileController {

	static final String uriNewFileSQL = "http://localhost:8081/api/informadoresBD/newFile";
	
	@Autowired FileService fs;
	
	@GetMapping("/newFile")
	 public ModelAndView handleRequestNewFile(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("newFile.html");

	    }
	
	@PostMapping("/newFile")
	public ModelAndView createPost(@RequestParam MultipartFile file, @RequestParam String title, 
										   @RequestParam String description, @RequestParam String keywords,
										   @RequestParam Integer size,
										   HttpServletRequest request) throws IOException {
		
		String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<Object> data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		//File f = fs.create(new File(added_date,title, description, keywords, data,size));
		
		File f = fs.createFileMongoDB(title, description, keywords, data, size);
		
		File f2 = fs.createFileSQL(title, description, keywords, size);
	
	RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				uriNewFileSQL,
				f2,
				File.class);
		
		return new ModelAndView("index.html");
	} 
	
	/*
	@GetMapping()
	public ResponseEntity<List<File>> getAll(HttpServletRequest request) {
		List<File> files = fs.findAll();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	*/
}
