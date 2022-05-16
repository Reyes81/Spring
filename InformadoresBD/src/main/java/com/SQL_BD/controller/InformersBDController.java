package com.SQL_BD.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Informer.Status;
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
	public Informer newInformer(@RequestBody Informer informer) {
		informer.setStatus(Status.PENDIENTE);
		is.saveInformer(informer);
		return informer;
	}
	
	@PostMapping(value="/modificarInfo")
	public Informer editInformer(@RequestBody Informer informer){
		Informer new_informer = is.updateInformer(informer);
		return new_informer;
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
	
	//@DeleteMapping(value="/informadores/eliminar/{id}")
	@RequestMapping("/informadores/eliminar/{id}")
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
			
		is.deleteInformer(id);
	}
	
}
