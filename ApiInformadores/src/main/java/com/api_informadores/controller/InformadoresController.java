package com.api_informadores.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.api_informadores.domain.Fichero;
import com.api_informadores.domain.Informador;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class InformadoresController {
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriCreateFile = "http://localhost:8083/api/ficheroBD/crearFichero";
	
	@GetMapping("/home")
	 public ModelAndView handleRequestHome(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("home.html");

	    }
	
	@GetMapping("/informador/index")
	 public ModelAndView handleRequestIndex(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("index.html");

	    }
	
	 @GetMapping("/informador/registro")
	 public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("newInformer.html");

	    }

	
	@PostMapping(value="/informador/new")
	public ModelAndView newInformer(@ModelAttribute Informador informador) {	
		System.out.println("Id: " + informador.getId() + "\n" +
				   "nif: " + informador.getNif() + "\n" +
				   "name: " + informador.getName() + "\n" +
				   "status: " + informador.getStatus() + "\n" +
				   "type: " + informador.getType() + "\n" + 
				   "quote: " + informador.getQuote() + "\n" + 
				   "eMail: " + informador.geteMail() + "\n" +
				   "password: " + informador.getPassword());
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				  uriNewInformer,
				  informador,
				  Informador.class);
	    
        return new ModelAndView("index.html");
	}	
	
	@PostMapping(value="/informador/crearFichero")
	public ModelAndView newFile(@ModelAttribute Fichero fichero) {	
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				  uriCreateFile,
				  fichero,
				  Fichero.class);
	    
        return new ModelAndView("index.html");
	}
	
	//Ficheros
	/*
	@PostMapping()
	public ResponseEntity<File> createPost(@RequestParam MultipartFile file, @RequestParam String title, 
										   @RequestParam String description, @RequestParam List<String> keywords,
										   HttpServletRequest request) throws IOException {
		
		String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<Object> data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		//File f = fs.create(new File(title, description, keywords, data));
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				  uriCreateFile,
				  fichero,
				  Fichero.class);
		
		return new ResponseEntity<>(f, HttpStatus.OK);
	} 
	
	
	@GetMapping()
	public ResponseEntity<List<File>> getAll(HttpServletRequest request) {
		//List<File> files = fs.findAll();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}*/
}
