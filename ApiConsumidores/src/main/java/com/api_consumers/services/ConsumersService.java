package com.api_consumers.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_consumers.domain.File;
import com.api_consumers.domain.File.Status;
import com.api_consumers.domain.FileConsumer;
import com.api_consumers.domain.Informer;

@Service
public class ConsumersService {
	
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriGetFileSQL= "http://localhost:8081/api/files/file/{id}";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriGetInformerByName = "http://localhost:8081/api/informadoresBD/informador/nombre/{name}";
	static final String uriGetAllFilesMongo= "http://localhost:8083/api/files/informador/{informerId}";
	
	//Método común para obtener un fichero por ID de MongoDB
	public File getFileMongoId(String id) {
		
		//Obtenemos el fichero completo de Mongo
		RestTemplate restTemplate = new RestTemplate();
			File file= restTemplate.getForObject(
			uriGetFileMongoId,
			File.class,id);
			
		return file;
	}
	
	//Método común para obtener un fichero por ID de SQL
	public File getFileSQLId(String id) {
		
		RestTemplate restTemplate2 = new RestTemplate();
		File file_sql = restTemplate2.getForObject(
		uriGetFileSQL,
		File.class,id);
		
		return file_sql;
	}
	
	//Método común para actualizar un fichero de SQL
	public void updateFileSQL(File file_sql) {
			
		RestTemplate restTemplate3 = new RestTemplate();
		restTemplate3.put(
		uriEditFileSQL,
		file_sql,
		File.class);
	}
		
	
	//CF2
	public File[] getFilesByInformerName(String name){
		
		//Obtener listado de ficheros por nombre de productor (CF2). Se buscarán solo
		//ficheros con estado publicado, se indicará el nombre o razón social del productor. Se
		//obtendrá el identificador del fichero, título, descripción, fecha de creación, formato,
		//tamaño, número de previsualizaciones y de descargas. No se requerirá
		//autenticación.
		
		
		//Obtenemos informador a partir del nombre o razón social
		RestTemplate restTemplate = new RestTemplate();
			Informer informador= restTemplate.getForObject(
					uriGetInformerByName,
			Informer.class,name);
			
		//Obtenemos todos los ficheros del informador por su id
			RestTemplate restTemplate2 = new RestTemplate();
			File[] files = restTemplate.getForObject(
					uriGetAllFilesMongo,
			File[].class,informador.getId());
		
		return files;
	}
	
	//CF3
	public List<Object> previewFile(String id) throws IOException{
		
		//Obtener una previsualización de un fichero (CF3). Se proporcionará el identificador
		//de un fichero publicado y se obtendrán las primeras 10 observaciones del fichero. Se
		//incrementará el número de previsualizaciones del fichero. No se requerirá
		//autenticación.

		//Obtenemos el fichero por Id de MongoDB
		File file = getFileMongoId(id);
		
		//Comprobamos que el fichero haya sido publicado de lo contrario lanzamos una excepción
		if(file.getStatus() != Status.PUBLICADO) {
			throw new IOException("The requested file is not published.");
		}
		
		//Obtenemos las 10 primeras observaciones del fichero
		List<Object> data = file.getData().stream().limit(10).collect(Collectors.toList());
		
		//Obtenemos el fichero de SQL con el id
		File file_sql = getFileSQLId(id);
				
		//Incrementamos el valor de previews
		Integer previews = file_sql.getPreviews() + 1;
		file_sql.setPreviews(previews);
				
		//Actualizamos el fichero en SQL
		updateFileSQL(file_sql);
		
		return data;
	}
	
	//CF4
	public FileConsumer downloadFile(String id) throws IOException{
		
		//Obtener un fichero completo (CF4). Se proporcionará el identificador de un fichero
		//publicado y se obtendrá todo el fichero. Se incrementará el número de descargas del
		//fichero. No se requerirá autenticación.

		
		//Capturar excepción si no existe el id //
		
	
		//Obtenemos el fichero completo de Mongo		
		File file = getFileMongoId(id);
		
		//Comprobamos que el fichero haya sido publicado de lo contrario lanzamos una excepción
		if(file.getStatus() != Status.PUBLICADO) {
			throw new IOException("The requested file is not published.");
		}

		//Obtenemos el fichero de SQL con el id
		File file_sql = getFileSQLId(id);
		
		//Incrementamos el valor de downloads
		Integer downloads = file_sql.getDownloads() + 1;
		file_sql.setDownloads(downloads);
		
		//Actualizamos el fichero en SQL
		updateFileSQL(file_sql);
		
		FileConsumer file_consumer = new FileConsumer(file.getId(), file.getTitle(), file.getDescription(), file.getAdded_date(), file.getData(), file.getKeywords(), file.getInformer_id(), file.getSize());
		
		return file_consumer;
	}
	
	

}
