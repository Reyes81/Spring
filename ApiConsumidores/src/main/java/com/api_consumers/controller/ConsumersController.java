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
	
	//CF1.
	//Obtener listado ordenado de ficheros por palabra clave (CF1). Se buscarán solo
	//ficheros con estado publicado, se indicará la palabra clave y se permitirá ordenar por
	//fecha de creación (de más reciente a más antiguo, opción por defecto), por tamaño
	//(de más grande a más pequeño) o por número de descargas (de más a menos
	//veces descargado). Se obtendrá el identificador del fichero, título, descripción,
	//nombre del productor, fecha de creación, formato, tamaño, número de
	//previsualizaciones y de descargas. No se requerirá autenticación.
	
	//CF1-a.Listado de Ficheros por palabara clave ordenados por fecha
	@GetMapping("files/informador/keywords/fecha/{keyword}")
	public ResponseEntity<List<File>> getFilesByKeyWordsDate(){
		//Todo
		List<File> files = null;
		
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF1-b.Listado de Ficheros por palabara clave ordenados por tamaño
	@GetMapping("files/informador/keywords/size/{keyword}")
	public ResponseEntity<List<File>> getFilesByKeyWordsSize(@PathVariable(value="keyword") String keyword){
		//Todo
		List<File> files = null;
						
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	//CF1-c.Listado de Ficheros por palabara clave ordenados por número de descargas
	@GetMapping("files/informador/keywords/descargas/{keyword}")
	public ResponseEntity<List<File>> getFilesByKeyWordsDownloads(@PathVariable(value="keyword") String keyword){
		//Todo
		List<File> files = null;
			
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	

	
	

	
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
