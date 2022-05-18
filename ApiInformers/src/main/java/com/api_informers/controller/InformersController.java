package com.api_informers.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_informers.domain.Informer;
import com.api_informers.domain.User;
import com.api_informers.security.CustomUserDetailsService;
import com.api_informers.services.UsersService;

@RestController
@RequestMapping("/api")
public class InformersController {
	private Informer informer = null;
	@Autowired
	UsersService us; 
	
	@Autowired
	CustomUserDetailsService cs;
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriNewUser = "http://localhost:8081/api/users/new";
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informer/{username}";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/modificarInfo";
	
	/*
	@PostConstruct
	public void init(Authentication auth) {
	    org.springframework.security.core.Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    if((UserDetails) auth.getPrincipal()!=null) {
	    	  UserDetails userDetail = (UserDetails) auth.getPrincipal();
	  	    
	  	    RestTemplate restTemplate = new RestTemplate();
	  	    
	  		informer = restTemplate.getForObject(
	  					 uriGetInformer,
	  					 Informer.class,userDetail.getUsername());
	  		
	  		System.out.println("Informador: " + informer.getName());
	    }
	  
	}
	    */
	
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
		
		RestTemplate restTemplate1 = new RestTemplate();
		
		RestTemplate restTemplate2 = new RestTemplate();
		User user = us.createUser(informer.geteMail(),informer.getPassword());
		
		restTemplate2.postForObject(
				  uriNewUser,
				  user,
				  User.class);
		
		restTemplate1.postForObject(
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
