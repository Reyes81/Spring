package com.SQL_BD.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Informer.Status;
import com.SQL_BD.domain.User;
import com.SQL_BD.service.FilesBDService;
import com.SQL_BD.service.InformersBDService;

@RestController
@RequestMapping("/api/informadoresBD")
public class InformersBDController {
	
	@Autowired
	InformersBDService is;
	
	@Autowired
	FilesBDService fs;
	
	@PostMapping(value= "/new")
	public ResponseEntity<Informer> newInformer(@RequestBody Informer informer) {
		informer.setStatus(Status.PENDIENTE);
		is.saveInformer(informer);
		return new ResponseEntity<>(informer, HttpStatus.OK);
	}

	@GetMapping(value= "/informadores")
	public ResponseEntity<List<Informer>> getAllInformers() {

		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getAllInformers();
		return new ResponseEntity<>(informers, HttpStatus.OK);
	}
	
	@GetMapping(value= "/informador/{username}")
	public Informer getInformer(@PathVariable(value = "username") String username) {
		Informer informer = is.getInformer(username);

		return informer;
	}
	
	//Obtenemos informador por nombre o razón social
	@GetMapping(value= "/informador/nombre/{name}")
	public ResponseEntity<Informer> getInformerByName(@PathVariable(value = "name") String name) {
	
		Informer informer = is.getInformerByName(name);
		System.out.println(informer);
		return new ResponseEntity<>(informer, HttpStatus.OK);
	}
	
	@GetMapping(value= "/informador/id/{id}")
	public Informer getInformer(@PathVariable(value = "id") Integer id) {
		Informer informer = is.getInformerId(id);
		return informer;
	}
	
	@GetMapping(value="/informadores/pendientes")
	public ResponseEntity<List<Informer>> getPendingInformers() {	
			
		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getPendingInformers();
		return new ResponseEntity<>(informers, HttpStatus.OK);
	}
	
	@GetMapping(value="/informadores/cuota")
	public ResponseEntity<List<Informer>> getQuoteConsumed() {	
			
		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getQuoteConsumed();
		return new ResponseEntity<>(informers, HttpStatus.OK);
	}
	
	//@PutMapping(value="/informadores/validar/{id}")
	@RequestMapping("/informadores/validar/{id}")
	public ResponseEntity<String> validateInformer(@PathVariable(value = "id") Integer id) {	
			
		Informer informer_validate = is.approveInformer(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
		
	@RequestMapping("/informadores/modificarInfo")
	public ResponseEntity<String> updateInformer(@RequestBody Informer informer) {	
		System.out.println(informer.getName());
		System.out.println(informer.getUserId());
		Informer informer_update = is.updateInformer(informer);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@RequestMapping("/informadores/actualizarCuota")
	public ResponseEntity<Boolean> updateQuote(@RequestBody Informer informer) {	
				
		Informer informer_update = is.updateQuote(informer);
		System.out.println("-----------> Informadores BD");
		System.out.println(informer_update);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	//@DeleteMapping(value="/informadores/eliminar/{id}")
	@RequestMapping("/informadores/eliminar/{id}")
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
			
		is.deleteInformer(id);
	}
	
	@RequestMapping("/informadores/inactivo/{id}")
	public void suspendInformer(@PathVariable(value = "id") Integer id) {	
			
		is.suspendInformer(id);
	}
	
	@GetMapping(value= "/files/informerid/{id}")
	public List<String> getFilesByInformerId(@PathVariable(value = "id") Integer id) {
		List<File> files = is.getFilesInformer(id);
		List<String> files_id = new ArrayList<String>();
		
		for(File f: files) {
			files_id.add(f.getId());
		}
		return files_id;
		
	}
}
