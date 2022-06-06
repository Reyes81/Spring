package com.SQL_BD.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Informer.Status;
import com.SQL_BD.domain.User;
import com.SQL_BD.repositories.InformersBDRepository;

@Service
public class InformersBDService {
	
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	@Autowired
	InformersBDRepository ir;
	
	@Autowired
	UsersBDService us;
	
	//PF1. Guardar informador
	public Informer saveInformer(Informer informer) {
		User user = us.getUserByUserName(informer.geteMail());
		informer.setUserId(user);
		Informer new_informer = ir.save(informer);

		return new_informer;
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
			Informer informer_bd = ir.getById(id);
			Informer informer = new Informer();
			informer.seteMail(informer_bd.geteMail());
			informer.setFiles(informer_bd.getFiles());
			informer.setId(id);
			informer.setName(informer_bd.getName());
			informer.setNif(informer_bd.getNif());
			informer.setPassword(informer_bd.getPassword());
			informer.setQuote(informer_bd.getQuote());
			informer.setStatus(informer_bd.getStatus());
			informer.setType(informer_bd.getType());
			informer.setUserId(informer_bd.getUserId());
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
			
			return informer;
		}
		
		//Guardar un fichero
		public void saveFile(File file) {
			
			//fr.save(fichero);
		}
		
		
		public Informer updateInformer(Informer informer)
		{
			Integer id = informer.getId();
			Informer informer_bd = ir.getById(id);
			informer_bd.setNif(informer.getNif());
			informer_bd.setName(informer.getName());
			informer_bd.setType(informer.getType());
			informer_bd.seteMail(informer.geteMail());
			informer_bd.setPassword(informer.getPassword());
			
			ir.save(informer_bd);
			
			return informer_bd;
		}
		
		public Informer updateInformerAll(Informer informer)
		{
			Integer id = informer.getId();
			Informer informer_bd = ir.getById(id);
			informer_bd.setNif(informer.getNif());
			informer_bd.setName(informer.getName());
			informer_bd.setType(informer.getType());
			informer_bd.seteMail(informer.geteMail());
			informer_bd.setPassword(informer.getPassword());
			informer_bd.setQuote(informer.getQuote());
			informer_bd.setStatus(informer.getStatus());
			
			ir.save(informer_bd);
			
			return informer_bd;
		}
		
		// Creamos este update porque en el anterior por requisito del proyecto no se puede
		//actualizar el atributo quote
		public Informer updateQuote(Informer informer) {
			
			Integer id = informer.getId();
			
			Informer informer_bd = ir.getById(id);
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
			Informer informer_bd = ir.getById(id);
			informer_bd.setStatus(Status.INACTIVO);
			
			ir.save(informer_bd);
		}
		
		public List<File> getFilesInformer(Integer id) {
			Informer informer_bd = ir.getById(id);
			List<File> files = informer_bd.getFiles();
			return files;
			
		}
}
