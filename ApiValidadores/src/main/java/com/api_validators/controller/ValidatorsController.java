package com.api_validators.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_validators.domain.File;
import com.api_validators.domain.Informer;
import com.api_validators.services.ValidatorsService;

@RestController
@RequestMapping(value="/api/validador")
public class ValidatorsController {
	
	
	
	static final String uriGetInformersQuote = "http://localhost:8081/api/informadoresBD/informadores/cuota";
	static final String uriValidateInformer = "http://localhost:8081/api/informadoresBD/informadores/validar/{id}";
	static final String uriDeleteInformer = "http://localhost:8081/api/informadoresBD/informadores/eliminar/{id}";
	static final String uriGetPendingFiles = "http://localhost:8083/api/files/pendientes";
	
	@Autowired
	ValidatorsService vs;
	
	//VF1.Obtenemos todos los informadores
	@GetMapping(value="/informadores")
	 public Informer[] handleRequestAll(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
			Informer[] informadores = vs.getAllInformers();
			
			return informadores;

	    }
	
	//VF1.Obtener Informadores pendientes de Aprobación
	@GetMapping(value="/informadores/pendientes")
	public Informer[] getPendingInformers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {	
			
		Informer[] informadores = vs.getPendingInformers();

        return informadores;
	}
	
	//VF1.Obtener informadores que hayan consumido su cuota anual
	@GetMapping(value="/informadores/cuota")
	public Informer[] getInformersQuoteConsumed() {	
			
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetInformersQuote,
					 Informer[].class);

        return informadores;
	}
	
	//TODO. VF1.Obtener informadores con ficheros erróneos
	
	//VF2. Aprobar un nuevo informador
	//@PutMapping("/informadores/validar/{id}")
	@RequestMapping("/informadores/validar/{id}")
	public void validateInformer(@PathVariable(value = "id") Integer id) {	
		System.out.println(id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriValidateInformer, Integer.class, id);
		
	}
	
	//VF3. Editar un Informador
	@RequestMapping("/informadores/edit/{id}")
	public void updateInformer(@PathVariable(value="id") Integer id, @RequestBody Informer informer) {
		
		vs.updateInformer(id, informer);
	}
	
	//VF4. Eliminar un produtor
	//@DeleteMapping("/informadores/eliminar/{id}")
	@RequestMapping("/informadores/eliminar/{id}")
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriDeleteInformer, Integer.class, id);
	}
	
	//VF5. Obtener el listado de ficheros pendientes de revision
	@GetMapping(value="/files/pendientes")
	public File[] getPendingFiles(){
		
		RestTemplate restTemplate = new RestTemplate();
		File[] files = restTemplate.getForObject(
					 uriGetPendingFiles,
					 File[].class);
		
		return files;
	}
	
	
}


