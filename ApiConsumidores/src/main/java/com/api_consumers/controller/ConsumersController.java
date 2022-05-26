package com.api_consumers.controller;

import java.io.IOException;
import java.util.List;

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

@RestController
@RequestMapping("/api/consumidor")
public class ConsumersController {
	
	@Autowired
	ConsumersService cs;
	
	//CF2. Obtener listado de ficheros por nombre de productor
	@GetMapping("files/informador/{username}")
	public ResponseEntity<FileByUsername[]> getFilesByInformerName(@PathVariable(value="username") String name){
		FileByUsername[] files = cs.getFilesByInformerName(name);
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	
	//CF3.Previsualizar un fichero de un fichero
	@GetMapping("/files/preview/{id}")
	public ResponseEntity<List<Object>> previewFile(@PathVariable(value="id") String id) {
				
		List<Object> data = null;
		try {
			data = cs.previewFile(id);
		} catch (IOException e) {

			e.printStackTrace();
		}
				
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
			
	//CF4.Descarga de un fichero
	@GetMapping("/files/download/{id}")
	public ResponseEntity<FileConsumer> downloadFile(@PathVariable(value="id") String id) {
			
		FileConsumer file = null;
		try {
			file = cs.downloadFile(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
}
