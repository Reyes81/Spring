package com.api_validators.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_validators.domain.Informer;

@RestController
@RequestMapping(value="/api/validador")
public class ValidatorsController {
	
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetInformersQuote = "http://localhost:8081/api/informadoresBD/informadores/cuota";
	static final String uriValidateInformer = "http://localhost:8081/api/informadoresBD/informadores/validar/{id}";
	static final String uriDeleteInformer = "http://localhost:8081/api/informadoresBD/informadores/eliminar/{id}";
	
	
	//VF1.Obtenemos todos los informadores
	@GetMapping(value="/informadores")
	 public ModelAndView handleRequestAll(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
			RestTemplate restTemplate = new RestTemplate();
			Informer[] informadores = restTemplate.getForObject(
					  uriGetAllInformes,
					  Informer[].class);
			
			ModelAndView modelAndView = new ModelAndView("allInformes.html");
			modelAndView.addObject("informadores", informadores);
	        return modelAndView;

	    }
	
	//VF1.Obtener Informadores pendientes de Aprobación
	@GetMapping(value="/informadores/pendientes")
	public ModelAndView getPendingInformers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {	
			
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetPendingInformers,
					 Informer[].class);
		
		ModelAndView modelAndView = new ModelAndView("allInformes.html");
		modelAndView.addObject("informadores", informadores);
        return modelAndView;
	}
	
	//VF1.Obtener informadores que hayan consumido su cuota anual
	@GetMapping(value="/informadores/cuota")
	public ModelAndView getInformersQuoteConsumed() {	
			
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetInformersQuote,
					 Informer[].class);
		
		ModelAndView modelAndView = new ModelAndView("allInformes.html");
		modelAndView.addObject("informadores", informadores);
        return modelAndView;
	}
	
	//TODO. VF1.Obtener informadores con ficheros erróneos
	
	//VF2. Aprobar un nuevo productor
	//@PutMapping("/informadores/validar/{id}")
	@RequestMapping("/informadores/validar/{id}")
	public void validateInformer(@PathVariable(value = "id") Integer id) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriValidateInformer, Integer.class, id);
	}
	
	//VF4. Eliminar un produtor
	//@DeleteMapping("/informadores/eliminar/{id}")
	@RequestMapping("/informadores/eliminar/{id}")
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriDeleteInformer, Integer.class, id);
	}
	
	
}


