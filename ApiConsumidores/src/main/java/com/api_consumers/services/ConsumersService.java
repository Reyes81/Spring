package com.api_consumers.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_consumers.domain.File;
import com.api_consumers.domain.FileConsumer;

@Service
public class ConsumersService {
	
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriDownloadFileSQL= "http://localhost:8081/api/files/file/{id}";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	

	
	//CF3
	public File previewFile(String id){
		
		//Obtener una previsualización de un fichero (CF3). Se proporcionará el identificador
		//de un fichero publicado y se obtendrán las primeras 10 observaciones del fichero. Se
		//incrementará el número de previsualizaciones del fichero. No se requerirá
		//autenticación.

		
		File file =null;
		return file;
		
	}
	
	//CF4
	public FileConsumer downloadFile(String id){
		
		//Obtener un fichero completo (CF4). Se proporcionará el identificador de un fichero
		//publicado y se obtendrá todo el fichero. Se incrementará el número de descargas del
		//fichero. No se requerirá autenticación.

		
		//Capturar excepción si no existe el id //
		
		//Obtenemos el fichero completo de Mongo
		RestTemplate restTemplate = new RestTemplate();
		File file= restTemplate.getForObject(
		uriGetFileMongoId,
		File.class,id);

		//Obtenemos el fichero de SQL con el id
		RestTemplate restTemplate2 = new RestTemplate();
		File file_sql = restTemplate2.getForObject(
		uriDownloadFileSQL,
		File.class,id);
		
		//Incrementamos el valor de downloads
		Integer downloads = file_sql.getDownloads() + 1;
		file_sql.setDownloads(downloads);
		
		//Actualizamos el fichero en SQL
		RestTemplate restTemplate3 = new RestTemplate();
		restTemplate3.put(
		uriEditFileSQL,
		file_sql,
		File.class);
		
		FileConsumer file_consumer = new FileConsumer(file.getId(), file.getTitle(), file.getDescription(), file.getAdded_date(), file.getData(), file.getKeywords(), file.getInformer_id(), file.getSize());
		
		return file_consumer;
	}
	
	

}
