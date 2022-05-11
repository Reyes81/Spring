package com.api_validadores.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.api_validadores.domain.Informador;

@RestController
@RequestMapping(value="/api/validador")
public class ValidadoresController {
	
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetInformersQuote = "http://localhost:8081/api/informadoresBD/informadores/cuota";
	static final String uriValidateInformer = "http://localhost:8081/api/informadoresBD/informadores/validar/{id}";
	
	
	@GetMapping("/index")
	 public ModelAndView handleRequestIndex(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        return new ModelAndView("index.html");

	    }
	

	
	//VF1.Obtenemos todos los informadores
	@GetMapping(value="/informadores")
	 public ModelAndView handleRequestAll(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
			RestTemplate restTemplate = new RestTemplate();
			Informador[] informadores = restTemplate.getForObject(
					  uriGetAllInformes,
					  Informador[].class);
			
			ModelAndView modelAndView = new ModelAndView("allInformes.html");
			modelAndView.addObject("informadores", informadores);
	        return modelAndView;

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
	
	@GetMapping("/informadores/editar/{id}")
	 public ModelAndView handleRequestEdit(@PathVariable(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
			ModelAndView model = new ModelAndView("editInformer.html");
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("HOLAAAAAAAAA");
			System.out.println(id);
			Informador informador = restTemplate.getForObject(
						 uriGetAllInformes+"?id="+id,
						 Informador.class);
			
			model.addObject(informador);

	        return new ModelAndView("editInformer.html");

	    }
	
	//TODO. VF1.Obtener informadores con ficheros erróneos
	
	//VF2. Aprobar un nuevo productor
	//@PutMapping("/informadores/validar/{id}")
	@RequestMapping("/informadores/validar/{id}")
	public ModelAndView handleRequestValidate(@PathVariable(value = "id") Integer id) {	
		System.out.println("hola");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriValidateInformer, Integer.class, id);
		
		Informador[] informadores = restTemplate.getForObject(
				  uriGetAllInformes,
				  Informador[].class);
		
		ModelAndView model = new ModelAndView("allInformes.html");
		model.addObject(informadores);
        return model;
	}
	
	
}


