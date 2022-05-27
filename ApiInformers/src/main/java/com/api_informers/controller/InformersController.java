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
	@Autowired
	UsersService us; 
	
	@Autowired
	InformersService is; 
	
	@Autowired
	CustomUserDetailsService cs;
	
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	//PF1. Solicitud de registro de un nuevo productor
	@PostMapping(value="/informador/new")
	public Informer newInformer(@RequestBody Informer informer) {
		Informer newInformer = is.newInformer(informer);
        return newInformer;
	}	
	
	
	//PF2. Editar informacion de un informador
	@RequestMapping(value="/informador/edit")
	public Informer editInformer(@RequestBody Informer informer){
		Informer informer_update= is.updateInformer(informer);
		return informer_update;
	}
	
	//Devolver la sesion del usuario
	@GetMapping("/user")
	 public User getUser(){
		User user_session = us.getUserSession();
		return user_session;
	 }
}
