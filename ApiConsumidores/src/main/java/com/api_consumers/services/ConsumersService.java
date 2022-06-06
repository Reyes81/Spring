package com.api_consumers.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_consumers.domain.File;
import com.api_consumers.domain.File.Status;
import com.api_consumers.domain.FileByUsername;
import com.api_consumers.domain.FileConsumer;

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
	static final String uriGetFilesbyKeyword = "http://localhost:8083/api/files/file/keyword/{keyword}";
	static final String uriGetFilesbyKeywordDate = "http://localhost:8083/api/files/file/keyword/fecha/{keyword}";
	static final String uriGetFilesbyKeywordSize = "http://localhost:8083/api/files/file/keyword/size/{keyword}";
	static final String uriGetFilesbyKeywordDownloads = "http://localhost:8083/api/files/file/keyword/downloads/{keyword}";
	static final String uriGetFilesSQL= "http://localhost:8081/api/files/all";
	static final String uriGetFilesDownloadsSQL = "http://localhost:8081/api/files/downloads";
	static final String uriUpdatePreviewsFileSQL = "http://localhost:8081/api/files/updatePreviews";

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
		System.out.println("FILE QSL");
		System.out.println(file_sql);
		
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
	
	public void updateFileSQLId(String id) {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(
				uriUpdatePreviewsFileSQL,
				id);
	}
	
	//CF1.Listado de Ficheros por palabara clave 
	public File[] getFilesByKeyWords(String keyword){
		RestTemplate restTemplate = new RestTemplate();
		
		File[] files =  restTemplate.getForObject(
					uriGetFilesbyKeyword,
					File[].class, keyword);
		
		return files;
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
	public List<FileByUsername> getFilesByKeyWordsDownloads(String keyword){
		RestTemplate restTemplate = new RestTemplate();
		
		FileByUsername[] files =  restTemplate.getForObject(
				uriGetFilesbyKeywordDownloads,
				FileByUsername[].class, keyword);
		
		List<String> files_id = new ArrayList<String>();
		
		for(FileByUsername f:files) {
			files_id.add(f.getId());
		}
		
		File[] files_sql = restTemplate.postForObject(
				uriGetFilesSQL,
				files_id, File[].class);
		
		List<FileByUsername> files_downloads = new ArrayList<FileByUsername>();
		
		for(int i = 0; i < files_sql.length; i++) {
			for(int j = 0; j < files.length; j++) {
				if(files_sql[i].getId().equals(files[j].getId())) {
					files_downloads.add(new FileByUsername(files[j].getId(), files[j].getTitle(), files[j].getDescription(), files[j].getAdded_date(), "JSON", files[j].getSize(), files_sql[i].getPreviews(), files_sql[i].getDownloads(), ""));
				}
			}
			
		}
		files_downloads.sort(Comparator.comparing(FileByUsername::getDownloads).reversed());
					
		return files_downloads;
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
		
		//Comprobamos que el fichero haya sido publicado de lo contrario lanzamos una excepción
		if(file.getStatus() != Status.PUBLICADO) {
			throw new IOException("The requested file is not published.");
		}
		
		//Obtenemos las 10 primeras observaciones del fichero
		List<Object> data2 = file.getData().stream().limit(10).collect(Collectors.toList());
		updateFileSQLId(id);
		
		return data2;
	}
	
	//CF4
	public FileConsumer downloadFile(String id) throws IOException{
		
		//Obtener un fichero completo (CF4). Se proporcionará el identificador de un fichero
		//publicado y se obtendrá todo el fichero. Se incrementará el número de descargas del
		//fichero. No se requerirá autenticación.
		
	
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