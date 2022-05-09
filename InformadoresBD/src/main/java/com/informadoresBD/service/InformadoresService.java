package com.informadoresBD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informadoresBD.domain.Informador;
import com.informadoresBD.domain.Informador.Status;
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
	
	//Obtener todos los informadores que esten pendientes de aprobación
	public List<Informador> getPendingInformers() {
		List<Informador> informers = new ArrayList<Informador>();
		Informador.Status status = null;
		informers = ir.findByStatus(status.PENDIENTE);
		
		if(informers.isEmpty()) {
			informers = null;
			System.out.println("No existen informadores en estado 'Pendiente de Aprobación'");
		}
		else {
			for(Informador informer:informers) {
				System.out.println("Informador Pendiente ID: " + informer.getId());
			}
		}
		
		return informers;
	}
}
