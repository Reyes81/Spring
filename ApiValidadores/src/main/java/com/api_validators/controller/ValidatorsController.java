package com.api_validators.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_validators.domain.FileInformers;
import com.api_validators.domain.Informer;
import com.api_validators.services.ValidatorsService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RestController
@RequestMapping(value="/api/validador")
public class ValidatorsController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ValidatorsController.class);
	
	@Autowired
	ValidatorsService vs;
	
	//VF1.Obtenemos todos los informadores
	@GetMapping(value="/informadores")
	 public ResponseEntity<Informer[]> handleRequestAll(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 	LOGGER.debug("Get all informers");
		 	
			Informer[] informadores = vs.getAllInformers();
			
			if(informadores.length == 0)
				log.info("There are no informers");
			
			return new ResponseEntity<>(informadores, HttpStatus.OK);

	    }
	
	//VF1.Obtener Informadores pendientes de Aprobación
	@GetMapping(value="/informadores/pendientes")
	public ResponseEntity<Informer[]> getPendingInformers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {	
		
		LOGGER.debug("Get all informers pending approval");
			
		Informer[] informadores = vs.getPendingInformers();
		
		if(informadores.length == 0)
			log.info("There are no informers pending approval");

        return new ResponseEntity<>(informadores, HttpStatus.OK);
	}
	
	//VF1.Obtener informadores que hayan consumido su cuota anual
	@GetMapping(value="/informadores/cuota")
	public ResponseEntity<Informer[]> getInformersQuoteConsumed() {	
			
		LOGGER.debug("Get all informers with exhausted quota");
		
		Informer[] informadores = vs.getInformersQuoteConsumed();
		
		if(informadores.length == 0)
			log.info("There are no informers with the annual quota consumed");

        return new ResponseEntity<>(informadores, HttpStatus.OK);
	}
	
	//TODO. VF1.Obtener informadores con ficheros erróneos
	@GetMapping(value="/informadores/ficherosErroneos")
	public ResponseEntity<List<Informer>> getInformersErrorFiles() {	
			
		LOGGER.debug("Get all informers with erroneous files");
		
		List<Informer> informadores = vs.getInformersErrorFiles();
		
		if(informadores.isEmpty())
			log.info("There are no informers with erroneous files");

		return new ResponseEntity<>(informadores, HttpStatus.OK);
	}
	
	//VF2. Aprobar un nuevo informador
	@RequestMapping("/informadores/validar/{id}")
	public void validateInformer(@PathVariable(value = "id") Integer id) {	

		LOGGER.debug("Approve informer with id " + id);
		
		vs.validateInformer(id);
		
	}
	
	//VF3. Editar un Informador
	@RequestMapping("/informadores/edit/{id}")
	public Informer updateInformer(@PathVariable(value="id") Integer id, @RequestBody Informer informer) {
		
		LOGGER.debug("Edit the full informer information with ID " + id);
		return vs.updateInformer(id, informer);
	}
	
	//VF4. Eliminar un informador
	@RequestMapping("/informadores/eliminar/{id}")
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
		
		LOGGER.debug("Remove informer with id " + id);
		vs.deleteInformer(id);
	}
	
	//VF4 Extra. Dar de baja un informador
	@RequestMapping("/informadores/inactivo/{id}")
	public void suspendInformer(@PathVariable(value = "id") Integer id) {	

		LOGGER.debug("Temporarily unsubscribe the informer with id " + id);
		
		vs.suspendInformer(id);
	}
	
	//VF5. Obtener el listado de ficheros pendientes de revision
	@GetMapping(value="/files/pendientes")
	public FileInformers[] getPendingFiles(){
		
		LOGGER.debug("Get all files pending approval");
		
		FileInformers[] complete_files_informers = vs.getPendingFiles();
		
		return complete_files_informers;
	}
	
	//VF6. Publicar un fichero
	@RequestMapping("/files/publish/{id}")
	public void publishFile(@PathVariable(value = "id") String id) {
		
		LOGGER.debug("Publicar el fichero con id " + id);
		vs.publishFile(id);
	}
	
	
}


