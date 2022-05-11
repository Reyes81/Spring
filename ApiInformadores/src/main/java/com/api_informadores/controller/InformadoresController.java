package com.api_informadores.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_informadores.domain.Informador;

@RestController
@RequestMapping("/api")
public class InformadoresController {
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	
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
}
