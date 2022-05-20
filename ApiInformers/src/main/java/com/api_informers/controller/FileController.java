package com.api_informers.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.api_informers.domain.File;
import com.api_informers.domain.Informer;
import com.api_informers.domain.User;
import com.api_informers.services.FileService;
import com.api_informers.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/informador")
public class FileController {

	static final String uriNewFileSQL =  "http://localhost:8081/api/files/newFile";
	static final String uriNewFileMongo= "http://localhost:8083/api/files/newFile";
	static final String uriGetAllFilesMongo= "http://localhost:8083/api/files";
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	@Autowired 
	FileService fs;
	
	@Autowired 
	UsersService us;
	
	@GetMapping("/newFile")
	 public ModelAndView handleRequestNewFile(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("newFile.html");

	    }
	
	@PostMapping(value="/newFile")
	public void createPost(@RequestParam MultipartFile file, @RequestParam String title, 
										   @RequestParam String description, @RequestParam List<String> keywords,
										   @RequestParam Integer size,
										   HttpServletRequest request) throws IOException {
		
		User user_session = us.getUserSession();
		
		RestTemplate restTemplate = new RestTemplate();
		Informer informer_session  = restTemplate.getForObject(
				uriGetInformer,
				 Informer.class,user_session.getUsername());
		
		String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<Object> data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		//File f = fs.create(new File(added_date,title, description, keywords, data,size));
		
		
		File f1 = fs.createFileMongoDB(informer_session.getId(),title, description, keywords, data, size);
		
		RestTemplate restTemplate1 = new RestTemplate();
		RestTemplate restTemplate2 = new RestTemplate();
		
		//Fichero a la BD de MongoDB
		File f2 = restTemplate1.postForObject(
				uriNewFileMongo,
				f1,
				File.class);
	/*
		//Fichero a la BD de sQL
		restTemplate2.postForObject(
				uriNewFileSQL,
				id_file,
				Object.class);
		*/
		
	} 
	
	
	//VF1.Obtenemos todos los informadores
		@GetMapping("/files")
		 public ModelAndView handleRequestAll(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
			
			
			 
				RestTemplate restTemplate = new RestTemplate();
				File[] files = restTemplate.getForObject(
						uriGetAllFilesMongo,
						  File[].class);
				
				ModelAndView modelAndView = new ModelAndView("allFiles.html");
				modelAndView.addObject("ficheros", files);
		        return modelAndView;

		    }
	
}
