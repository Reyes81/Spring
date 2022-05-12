package com.informersBD.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informersBD.domain.File;
import com.informersBD.domain.Informer;
import com.informersBD.domain.Informer.Status;
import com.informersBD.service.InformersBDService;

@Component
@RestController
@RequestMapping("/api/informadoresBD")
public class InformersBDController {
	
	@Autowired
	InformersBDService is;
	
	@PostMapping(value= "/new")
	public Informer newInformer(@RequestBody Informer informer) {
		informer.setStatus(Status.PENDIENTE);
		is.saveInformer(informer);
		return informer;
	}
	
	@GetMapping(value= "/informadores")
	public List<Informer> getAllInformers() {

		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getAllInformers();
		return informers;
	}
	
	@GetMapping(value="/informadores/pendientes")
	public List<Informer> getPendingInformers() {	
			
		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getPendingInformers();
		return informers;
	}
	
	@GetMapping(value="/informadores/cuota")
	public List<Informer> getQuoteConsumed() {	
			
		List<Informer> informers = new ArrayList<Informer>();
		informers = is.getQuoteConsumed();
		return informers;
	}
	
	//@PutMapping(value="/informadores/validar/{id}")
	@RequestMapping("/informadores/validar/{id}")
	public void validateInformer(@PathVariable(value = "id") Integer id) {	
			
		is.approveInformer(id);
	}
	
	@PostMapping(value= "/informadores/crearFichero")
	public File newFile(@RequestBody File file) {
		
		is.saveFile(file);
		return file;
	}

}
