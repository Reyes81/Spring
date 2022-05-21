package com.api_informers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.Informer;
import com.api_informers.domain.User;
import com.api_informers.security.CustomUserDetailsService;
import com.api_informers.services.InformersService;
import com.api_informers.services.UsersService;

@RestController
@RequestMapping("/api")
public class InformersController {
	private Informer informer = null;
	@Autowired
	UsersService us; 
	
	@Autowired
	InformersService is; 
	
	@Autowired
	CustomUserDetailsService cs;
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriNewUser = "http://localhost:8081/api/users/new";
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	
	@GetMapping("/user")
	 public User getUser(){
		User user_session = us.getUserSession();
		return user_session;
	    }
	/*
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
	 */
	
	//PF1. Solicitud de registro de un nuevo productor
	@PostMapping(value="/informador/new")
	public Informer newInformer(@RequestBody Informer informer) {	
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
		
		User user2 = restTemplate2.postForObject(
				  uriNewUser,
				  user,
				  User.class);
		informer.setUserId(user2.getId());
		
		restTemplate1.postForObject(
				  uriNewInformer,
				  informer,
				  Informer.class);
		
	
        return informer;
	}	
	
	/*
	//PF2. Editar informacion de un informador
	@PostMapping(value="/informador/edit")
	public Informer editInformer(@RequestBody Informer informer)
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
	}*/
	
	@RequestMapping(value="/informador/edit")
	public Informer editInformer(@RequestBody Informer informer)
	{
		Informer informer_update= is.updateInformer(informer);
		return informer_update;
	}
		
}
