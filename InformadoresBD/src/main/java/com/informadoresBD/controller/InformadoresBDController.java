package com.informadoresBD.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.informadoresBD.domain.Informador;
import com.informadoresBD.service.InformadoresService;

@Component
@RestController
@RequestMapping("/api/informadoresBD")
public class InformadoresBDController {
	
	@Autowired
	InformadoresService is;
	
	@PostMapping(value= "/new")
	public Informador newInformer(@RequestBody Informador informador) {
		
		is.saveInformer(informador);
		return informador;
	}
	
	@GetMapping(value= "/informadores")
	public List<Informador> getAllInformers() {

		List<Informador> informers = new ArrayList<Informador>();
		informers = is.getAllInformers();
		return informers;
	}
	
	@GetMapping(value="/informadores/pendientes")
	public List<Informador> getPendingInformers() {	
			
		List<Informador> informers = new ArrayList<Informador>();
		informers = is.getPendingInformers();
		return informers;
	}

}
