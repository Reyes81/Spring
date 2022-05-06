package com.informadoresBD.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.informadoresBD.domain.Informador;

@RestController("api/usuariosBD")
public class InformadoresBDController {
	
	@PostMapping("/new")
	public Informador newInformer(@RequestBody Informador informador) {
		System.out.println("Estamos en UsuariosBD");
		return informador;
	}

}
