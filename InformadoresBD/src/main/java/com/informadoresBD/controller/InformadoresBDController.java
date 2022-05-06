package com.informadoresBD.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informadoresBD.domain.Informador;

@RestController
@RequestMapping("/api/informadoresBD")
public class InformadoresBDController {
	
	@PostMapping(value= "/new")
	public Informador newInformer(@RequestBody Informador informador) {
		System.out.println("Estamos en InformadoresBD");
		return informador;
	}

}
