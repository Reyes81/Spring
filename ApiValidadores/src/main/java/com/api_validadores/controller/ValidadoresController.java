package com.api_validadores.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/api/validador")
public class ValidadoresController {
	
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetInformersQuote = "http://localhost:8081/api/informadoresBD/informadores/cuota";
	static final String uriValidateInformer = "http://localhost:8081/api/informadoresBD/informadores/validar";
	
	
	//VF1.Obtenemos todos los informadores
	@GetMapping(value="/informadores")
	public void getAllInformers() {	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(
				  uriGetAllInformes,
				  Object[].class);
	
	}
	
	//VF1.Obtener Informadores pendientes de Aprobación
	@GetMapping(value="/informadores/pendientes")
	public void getPendingInformers() {	
			
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(
					 uriGetPendingInformers,
					 Object[].class);
	}
	
	//VF1.Obtener informadores que hayan consumido su cuota anual
	@GetMapping(value="/informadores/cuota")
	public void getInformersQuoteConsumed() {	
			
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(
					 uriGetInformersQuote,
					 Object[].class);
	}
	
	//TODO. VF1.Obtener informadores con ficheros erróneos
	
	//VF2. Aprobar un nuevo productor
	@PutMapping(value="/informadores/validar/{id}")
	public void validateInformer(@PathVariable(value = "id") Integer id) {	
		System.out.println("hola");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriValidateInformer, restTemplate, id);
	}
}


