package com.api_consumers.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_consumers.domain.File;
import com.api_consumers.domain.File.Status;
import com.api_consumers.domain.FileByUsername;
import com.api_consumers.domain.FileConsumer;
import com.api_consumers.domain.FileSQL;

//FindbyKeywordsContaining

@Service
public class ConsumersService {
	
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriGetFileSQL= "http://localhost:8081/api/files/file/{id}";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriGetFilesByUsername = "http://localhost:8081/api/files/username/{username}";
	//static final String uriGetAllFilesMongo = "http://localhost:8083/api/files/informador/{informerId}";
	static final String uriGetFilesById = "http://localhost:8083/api/files/filesById";
	static final String uriGetFilesbyKeywordDate = "http://localhost:8083/api/files/file/keyword/fecha/{keyword}";
	static final String uriGetFilesbyKeywordSize = "http://localhost:8083/api/files/file/keyword/size/{keyword}";
	static final String uriGetFilesbyKeywordDownloads = "http://localhost:8083/api/files/file/keyword/downloads/{keyword}";
	static final String uriGetFilesSQL= "http://localhost:8081/api/files/all";

	
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
	
	//CF1-a.Listado de Ficheros por palabara clave ordenados por fecha
	public File[] getFilesByKeyWordsDate(String keyword){
		RestTemplate restTemplate = new RestTemplate();
		
		File[] files =  restTemplate.getForObject(
					uriGetFilesbyKeywordDate,
					File[].class, keyword);
		
		return files;
	}
		
	//CF1-b.Listado de Ficheros por palabara clave ordenados por tamaño
	public File[] getFilesByKeyWordsSize(String keyword){
		RestTemplate restTemplate = new RestTemplate();
		
		File[] files =  restTemplate.getForObject(
				uriGetFilesbyKeywordSize,
				File[].class, keyword);
							
		return files;
	}
		
	//CF1-c.Listado de Ficheros por palabara clave ordenados por número de descargas
	public FileByUsername[] getFilesByKeyWordsDownloads(String keyword){
		RestTemplate restTemplate = new RestTemplate();
		
		FileByUsername[] files =  restTemplate.getForObject(
				uriGetFilesbyKeywordDownloads,
				FileByUsername[].class, keyword);
		
		/*List<String> files_id = new ArrayList<String>();
		
		for(FileByUsername f:files) {
			files_id.add(f.getId());
		}
		
		FileByUsername[] files_sql = restTemplate.getForObject(
				uriGetFilesSQL,
				FileByUsername[].class, files_id);
		
						*/	
		return files;
	}
		
	//CF2
	public FileByUsername[] getFilesByInformerName(String name){
		
		//Obtener listado de ficheros por nombre de productor (CF2). Se buscarán solo
		//ficheros con estado publicado, se indicará el nombre o razón social del productor. Se
		//obtendrá el identificador del fichero, título, descripción, fecha de creación, formato,
		//tamaño, número de previsualizaciones y de descargas. No se requerirá
		//autenticación.

		
		//Obtenemos informador a partir del nombre o razón social
		RestTemplate restTemplate = new RestTemplate();
		
		FileByUsername[] files_by_username= restTemplate.getForObject(
										uriGetFilesByUsername,
										FileByUsername[].class, name);
		
		
		//Obtenemos todos los ficheros del informador por su id
		RestTemplate restTemplate2 = new RestTemplate();
		
		FileByUsername[] files = restTemplate2.postForObject(
				uriGetFilesById,
				files_by_username,
				FileByUsername[].class);
		
		return files;

	}
	
	//CF3
	public List<Object> previewFile(String id) throws IOException {
		
		//Obtener una previsualización de un fichero (CF3). Se proporcionará el identificador
		//de un fichero publicado y se obtendrán las primeras 10 observaciones del fichero. Se
		//incrementará el número de previsualizaciones del fichero. No se requerirá
		//autenticación.

		//Obtenemos el fichero por Id de MongoDB
		File file = getFileMongoId(id);
		System.out.println(file);
		
		
		//Comprobamos que el fichero haya sido publicado de lo contrario lanzamos una excepción
		if(file.getStatus() != Status.PUBLICADO) {
			throw new IOException("The requested file is not published.");
		}
		
		System.out.println("Funciona");
		//Obtenemos las 10 primeras observaciones del fichero
		List<Object> data = file.getData().stream().limit(10).collect(Collectors.toList());
		
		Integer num_previews=0;
		//Obtenemos el fichero de SQL con el id
		System.out.println("Funciona");
		File file_sql = getFileSQLId(id);
		if(file_sql.getPreviews()!=null)
			num_previews = file_sql.getPreviews();
		System.out.println("Funciona");
				
		//Incrementamos el valor de previews
		Integer previews = num_previews + 1;
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

		Integer num_downloads=0;
		//Obtenemos el fichero de SQL con el id
		File file_sql = getFileSQLId(id);
		
		if(file_sql.getDownloads()!=null)
			num_downloads = file_sql.getDownloads();
		
		//Incrementamos el valor de downloads
		Integer downloads = num_downloads + 1;
		file_sql.setDownloads(downloads);
		
		//Actualizamos el fichero en SQL
		updateFileSQL(file_sql);
		
		FileConsumer file_consumer = new FileConsumer(file.getId(), file.getTitle(), file.getDescription(), file.getAdded_date(), file.getData(), file.getKeywords(), file.getInformer_id(), file.getSize());
		
		return file_consumer;
	}
}