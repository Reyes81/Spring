package com.informadoresBD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informadoresBD.domain.Fichero;
import com.informadoresBD.domain.Informador;
import com.informadoresBD.domain.Informador.Status;
import com.informadoresBD.repository.FilesRepository;
import com.informadoresBD.repository.InformadoresRepository;

@Service
public class InformadoresBDService {
	
	@Autowired
	InformadoresRepository ir;
	
	@Autowired
	FilesRepository fr;
	
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
			System.out.println("Id: " + informer.getId() + "\n" +
					   "nif: " + informer.getNif() + "\n" +
					   "status: " + informer.getStatus() + "\n" +
					   "type: " + informer.getType() + "\n" + 
					   "quote: " + informer.getQuote() + "\n" + 
					   "eMail: " + informer.geteMail() + "\n" +
					   "password: " + informer.getPassword());
		}
		return informers;
	}
	
	//VF1. Obtener todos los informadores que esten pendientes de aprobación
	public List<Informador> getPendingInformers() {
		List<Informador> informers = new ArrayList<Informador>();
		informers = ir.findByStatus(Status.PENDIENTE);
		
		if(informers.isEmpty()) {
			informers = null;
			System.out.println("No existen informadores en estado 'Pendiente de Aprobación'");
		}
		else {
			for(Informador informer:informers) {
				System.out.println("Id: " + informer.getId() + "\n" +
						   "nif: " + informer.getNif() + "\n" +
						   "status: " + informer.getStatus() + "\n" +
						   "type: " + informer.getType() + "\n" + 
						   "quote: " + informer.getQuote() + "\n" + 
						   "eMail: " + informer.geteMail() + "\n" +
						   "password: " + informer.getPassword());
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
					System.out.println("Id: " + informer.getId() + "\n" +
							   "nif: " + informer.getNif() + "\n" +
							   "status: " + informer.getStatus() + "\n" +
							   "type: " + informer.getType() + "\n" + 
							   "quote: " + informer.getQuote() + "\n" + 
							   "eMail: " + informer.geteMail() + "\n" +
							   "password: " + informer.getPassword());
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
							   "nif: " + informer.getNif() + "\n" +
							   "status: " + informer.getStatus() + "\n" +
							   "type: " + informer.getType() + "\n" + 
							   "quote: " + informer.getQuote() + "\n" + 
							   "eMail: " + informer.geteMail() + "\n" +
							   "password: " + informer.getPassword());
			ir.save(informer);
		}
		
		//Guardar un fichero
		public void saveFile(Fichero fichero, Integer informerId) {
			
			Informador informer = ir.getById(informerId);
			List <Fichero> files = informer.getFicheros();
			files.add(fichero);
			informer.setFicheros(files);
			fr.save(fichero);
		}
	
}
