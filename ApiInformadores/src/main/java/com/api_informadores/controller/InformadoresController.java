package com.api_informadores.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api_informadores.domain.Informador;

@RestController
@RequestMapping("/api/informador")
public class InformadoresController {
	
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	
	@PostMapping(value="/new")
	public Informador newInformer(@RequestBody Informador informador) {	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				  uriNewInformer,
				  informador,
				  Informador.class);
	    
		return informador;
	}	
}
