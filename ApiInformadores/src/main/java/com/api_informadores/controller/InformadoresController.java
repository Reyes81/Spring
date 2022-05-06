package com.api_informadores.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.api_informadores.domain.Informador;

@RestController("api/informador")
public class InformadoresController {
	
	@PostMapping(value = "/new")
	public Informador newInformer(@RequestBody Informador informador) {	
		
		final String uri = "http://localhost:8081/api/usuariosBD/new";
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				  uri,
				  informador,
				  ResponseEntity.class);
	    
		return informador;
	}
}
