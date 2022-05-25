package com.api_consumers.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_consumers.domain.File;

@Service
public class ConsumersService {
	
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriDownloadFileSQL= "http://localhost:8081/api/files/file/{id}";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit";
	
	public File downloadFile(String id) {
		
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
		
		//Incrementamos el valor de downloads e incrementamos el mismo
		Integer downloads = file.getDownloads() + 1;
		file_sql.setDownloads(downloads);
		
		//Actualizamos el fichero en SQL
		RestTemplate restTemplate3 = new RestTemplate();
		restTemplate3.put(
		uriEditFileSQL,
		file_sql,
		File.class);
		
		return file;
	}
	
	

}
