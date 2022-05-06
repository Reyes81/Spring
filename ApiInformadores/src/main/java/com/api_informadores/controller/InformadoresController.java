package com.api_informadores.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api_informadores.domain.Informador;

@RestController
@RequestMapping("/api/informador")
public class InformadoresController {
	
	@PostMapping(value="/new")
	public Informador newInformer(@RequestBody Informador informador) {	
		
		System.out.println("hoola");
		
		final String uri = "http://localhost:8081/api/informadoresBD/new";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				  uri,
				  informador,
				  Informador.class);
	    
		return informador;
	}
	
}
