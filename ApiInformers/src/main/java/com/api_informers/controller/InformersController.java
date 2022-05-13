package com.api_informers.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_informers.domain.Informer;

@RestController
@RequestMapping("/api")
public class InformersController {
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/modificarInfo";
	
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
	 
	 

	//PF1. Solicitud de registro de un nuevo productor
	@PostMapping(value="/informador/new")
	public ModelAndView newInformer(@ModelAttribute Informer informer) {	
		System.out.println("Id: " + informer.getId() + "\n" +
				   "nif: " + informer.getNif() + "\n" +
				   "name: " + informer.getName() + "\n" +
				   "status: " + informer.getStatus() + "\n" +
				   "type: " + informer.getType() + "\n" + 
				   "quote: " + informer.getQuote() + "\n" + 
				   "eMail: " + informer.geteMail() + "\n" +
				   "password: " + informer.getPassword());
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				  uriNewInformer,
				  informer,
				  Informer.class);
	    
        return new ModelAndView("index.html");
	}	
	
	//PF2. Editar informacion de un informador
	@PostMapping(value="/informador/edit")
	public Informer editInformer(@ModelAttribute Informer informer)
	{
		
		System.out.println("Id: " + informer.getId() + "\n" +
				   "nif: " + informer.getNif() + "\n" +
				   "name: " + informer.getName() + "\n" +
				   "status: " + informer.getStatus() + "\n" +
				   "type: " + informer.getType() + "\n" + 
				   "quote: " + informer.getQuote() + "\n" + 
				   "eMail: " + informer.geteMail() + "\n" +
				   "password: " + informer.getPassword());
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForObject(
				uriEditInformer,
				informer,
				Informer.class);
		
		return informer;
	}
		
}
