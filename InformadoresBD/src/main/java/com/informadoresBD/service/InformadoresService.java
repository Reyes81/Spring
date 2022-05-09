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
	
	//PF1. Guardar informador
	public Informador saveInformer(Informador informer) {
		ir.save(informer);
		return informer;
	}
	
	//VF1. Obtener todos los informadores
	public List<Informador> getAllInformers(){
		List<Informador> informers = new ArrayList<Informador>();
		informers = ir.findAll();
		
		for(Informador informer:informers) {
			System.out.println("ID: " + informer.getId());
		}
		return informers;
	}
	
	//VF1. Obtener todos los informadores que esten pendientes de aprobación
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
	
	//VF1. Obtener todos los informadores que hayan agotado su cuota anual
		public List<Informador> getQuoteConsumed() {
			List<Informador> informers = new ArrayList<Informador>();
			Double quote = 0.0;
			informers = ir.findByQuote(quote);
			
			if(informers.isEmpty()) {
				informers = null;
				System.out.println("No existen informadores con cuota agotada");
			}
			else {
				for(Informador informer:informers) {
					System.out.println("Informador con cuota agotada -  ID: " + informer.getId());
				}
			}
			
			return informers;
		}
		
	//VF2. Aprobar un informador
		public void approveInformer(Integer id) {
			
			Informador informer = ir.getById(id);
			informer.setStatus(Status.ACTIVO);
			informer.setQuote(500.0);
			
			System.out.println("Id: " + informer.getId() + "\n" +
							   "nif: " + informer.getNif_Cif() + "\n" +
							   "status: " + informer.getStatus() + "\n" +
							   "type: " + informer.getType() + "\n" + 
							   "quote: " + informer.getQuote() + "\n" + 
							   "eMail: " + informer.geteMail() + "\n" +
							   "password" + informer.getPassword());
		}
		
}
