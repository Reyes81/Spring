package com.SQL_BD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Informer.Status;
import com.SQL_BD.repositories.InformersBDRepository;

@Service
public class InformersBDService {
	
	@Autowired
	InformersBDRepository ir;
	
	@Autowired
	UsersBDService us;
	
	//PF1. Guardar informador
	public Informer saveInformer(Informer informer) {
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
		public void approveInformer(Integer id) {
			
			Informer informer = ir.getById(id);
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
		public void saveFile(File file) {
			
			//fr.save(fichero);
		}
		
		//PF2. Editar informacion de un informador
		
		public Informer updateInformer(Informer informer)
		{
			//Hay que buscarlo po el username de sesión para que coja el informador
			Informer old_informer = ir.getById(informer.getId());
			String old_username = old_informer.geteMail();
			
			if(old_informer.getStatus()==Status.ACTIVO) {
			
				if(informer.getNif()!=null)
					old_informer.setNif(informer.getNif());
				if(informer.getName()!=null)
					old_informer.setName(informer.getName());
				if(informer.getPassword()!=null)
					old_informer.setPassword(informer.getPassword());
				if(informer.getType()!=null)
					old_informer.setType(informer.getType());
				if(informer.geteMail()!=null)
					old_informer.seteMail(informer.geteMail());
				
				us.updateUser(old_username,old_informer.geteMail(),old_informer.getPassword());
	
				ir.save(old_informer);
			}
			//return old_informer; Da error no se por que
			else 
				System.out.println("No se puede editar el informador ya que su estado es: " + old_informer.getStatus());
			
			return old_informer;
			

		}
		
		public void deleteInformer(Integer id)
		{
			ir.deleteById(id);
		}
	
}
