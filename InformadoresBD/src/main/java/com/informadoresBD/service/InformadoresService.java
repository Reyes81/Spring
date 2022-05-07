package com.informadoresBD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informadoresBD.domain.Informador;
import com.informadoresBD.repository.InformadoresRepository;

@Service
public class InformadoresService {
	
	@Autowired
	InformadoresRepository ir;
	
	//Guardar informador
	public Informador saveInformer(Informador informer) {
		ir.save(informer);
		return informer;
	}
	
	//Obtener todos los informadores
	public List<Informador> getAllInformers(){
		List<Informador> informers = new ArrayList<Informador>();
		informers = ir.findAll();
		
		for(Informador informer:informers) {
			System.out.println("ID: " + informer.getId());
		}
		return informers;
	}
}
