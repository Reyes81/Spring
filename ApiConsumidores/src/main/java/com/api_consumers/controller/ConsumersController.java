package com.api_consumers.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_consumers.domain.File;
import com.api_consumers.domain.FileByUsername;
import com.api_consumers.domain.FileConsumer;
import com.api_consumers.services.ConsumersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/consumidor")
public class ConsumersController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsumersController.class);
	
	@Autowired
	ConsumersService cs;
	
	//CF1-a.Listado de Ficheros por palabara clave
	@GetMapping("/files/keywords/{keyword}")
	public ResponseEntity<File[]> getFilesByKeyWords(@PathVariable(value="keyword") String keyword){

		LOGGER.debug("List of Files by keyword " + keyword + " sorted by date");
		File[] files = cs.getFilesByKeyWords(keyword);
		
		if(files.length == 0)
			log.info("There are no files with the keyword: " + keyword);
		
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF1-a.Listado de Ficheros por palabara clave ordenados por fecha
	@GetMapping("/files/keywords/fecha/{keyword}")
	public ResponseEntity<File[]> getFilesByKeyWordsDate(@PathVariable(value="keyword") String keyword){

		LOGGER.debug("List of Files by keyword " + keyword + " sorted by date");
		File[] files = cs.getFilesByKeyWordsDate(keyword);
		
		if(files.length == 0)
			log.info("There are no files with the keyword: " + keyword);
		
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF1-b.Listado de Ficheros por palabara clave ordenados por tamaño
	@GetMapping("/files/keywords/size/{keyword}")
	public ResponseEntity<File[]> getFilesByKeyWordsSize(@PathVariable(value="keyword") String keyword){

		LOGGER.debug("List of Files by keyword " + keyword + " sorted by size");
		File[] files = cs.getFilesByKeyWordsSize(keyword);
		
		if(files.length == 0)
			log.info("There are no files with the keyword: " + keyword);
						
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF1-c.Listado de Ficheros por palabara clave ordenados por número de descargas
	@GetMapping("/files/keywords/downloads/{keyword}")
	public ResponseEntity<List<FileByUsername>> getFilesByKeyWordsDownloads(@PathVariable(value="keyword") String keyword){
		LOGGER.debug("List of Files by keyword " + keyword + " sorted by downloads");
		List<FileByUsername> files = cs.getFilesByKeyWordsDownloads(keyword);
		
		if(files.size() == 0)
			log.info("There are no files with the keyword: " + keyword);
			
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF2. Obtener listado de ficheros por nombre de productor
	@GetMapping("/files/informador/{username}")
	public ResponseEntity<FileByUsername[]> getFilesByInformerName(@PathVariable(value="username") String name){
		
		LOGGER.debug("Obtain the list of informer files" + name);
		FileByUsername[] files = cs.getFilesByInformerName(name);
		
		if(files.length == 0)
			log.info("There are no informers files " + name);
		
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	
	//CF3.Previsualizar un fichero de un fichero
	@GetMapping("/files/preview/{id}")
	public ResponseEntity<List<Object>> previewFile(@PathVariable(value="id") String id) throws IOException {
		LOGGER.debug("Previews file with id " + id);
		List<Object> data = null;
		
			data = cs.previewFile(id);
		
				
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
			
	//CF4.Descarga de un fichero
	@GetMapping("/files/download/{id}")
	public ResponseEntity<FileConsumer> downloadFile(@PathVariable(value="id") String id) throws IOException {
			
		LOGGER.debug("Downloads file with id " + id);
		FileConsumer file = null;
			file = cs.downloadFile(id);
		
			
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
}
