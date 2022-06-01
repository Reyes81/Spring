package com.pf_persistencia.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Informer;
import com.pf_persistencia.domain.Informer.Status;
import com.pf_persistencia.domain.User;
import com.pf_persistencia.repository.InformersRepository;

@Service
public class InformerService {
	
	@Autowired
	InformersRepository ir;
	
	@Autowired
	UserService us;
	
	//PF1. Guardar informador
	public Informer saveInformer(Informer informer) {
		User user = us.getUserByUserName(informer.geteMail());
		informer.setUserId(user);
		ir.save(informer);
		return informer;
	}
	
	//VF1. Obtener todos los informadores
	public List<Informer> getAllInformers(){
		List<Informer> informers = new ArrayList<Informer>();
		informers = ir.findAll();
		
		for(Informer informer:informers) {
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
	
		// Obtener un informador por eMail
		public Informer getInformer(String username){
	
			//Crear una excepción por si no existe el informador
			Informer informer = ir.findByeMail(username);
			
			return informer;
		}
		
		// Obtener un informador por nombre o razón social
		public Informer getInformerByName(String name){
			
			//Crear una excepción por si no existe el informador
			Informer informer = ir.findByName(name);
					
			return informer;
		}
		
		// Obtener un informador por id
		public Informer getInformerId(Integer id){
			
			//Crear una excepción por si no existe el informador
			Informer informer = ir.findById(id).get();
			return informer;
		}
	
	//VF1. Obtener todos los informadores que esten pendientes de aprobación
	public List<Informer> getPendingInformers() {
		List<Informer> informers = new ArrayList<Informer>();
		informers = ir.findByStatus(Status.PENDIENTE);
		
		if(informers.isEmpty()) {
			informers = null;
			System.out.println("No existen informadores en estado 'Pendiente de Aprobación'");
		}
		else {
			for(Informer informer:informers) {
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
		public List<Informer> getQuoteConsumed() {
			List<Informer> informers = new ArrayList<Informer>();
			Double quote = 0.0;
			informers = ir.findByQuote(quote);
			
			if(informers.isEmpty()) {
				informers = null;
				System.out.println("No existen informadores con cuota agotada");
			}
			else {
				for(Informer informer:informers) {
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
		public Informer approveInformer(Integer id) {
			
			Informer informer = ir.findById(id).get();
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
			
			return informer;
		}
		
		
		
		public Informer updateInformer(Informer informer)
		{
			Integer id = informer.getId();
			
			Informer informer_bd = ir.findById(informer.getId()).get();
			informer_bd.setNif(informer.getNif());
			informer_bd.setName(informer.getName());
			informer_bd.setType(informer.getType());
			informer_bd.seteMail(informer.geteMail());
			informer_bd.setPassword(informer.getPassword());
			
			ir.save(informer_bd);
			
			return informer_bd;
		}
		
		// Creamos este update porque en el anterior por requisito del proyecto no se puede
		//actualizar el atributo quote
		public Informer updateQuote(Informer informer) {
			
			Integer id = informer.getId();
			
			Informer informer_bd = ir.findById(id).get();
			informer_bd.setQuote(informer.getQuote());
			
			ir.save(informer_bd);
			return informer_bd;
		}
		
		public void deleteInformer(Integer id)
		{
			ir.deleteById(id);
		}
		
		public void suspendInformer(Integer id)
		{
			Informer informer_bd = ir.findById(id).get();
			informer_bd.setStatus(Status.INACTIVO);
			
			ir.save(informer_bd);
		}	
		
		public File saveFile(File file) {
			Informer informer = file.getInformer();
			informer.setFile(file);
			ir.save(informer);
			
			return file;
		}
		
		public List<File> getFiles(Informer informer) {
			List<File> files = informer.getFiles();
			return files;
		}
		public void deleteFile(Informer informer) {
			File file = informer.getFiles().remove(0);
		}
}
