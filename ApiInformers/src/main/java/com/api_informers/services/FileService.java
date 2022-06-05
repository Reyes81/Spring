package com.api_informers.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.File;
import com.api_informers.domain.Informer;
import com.api_informers.domain.Informer.Status;
import com.api_informers.domain.User;

@Service
public class FileService {
	
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	static final String uriNewFileSQL= "http://localhost:8081/api/files/newFile";
	static final String uriDeleteFileSQL= "http://localhost:8081/api/files/delete/{id}";
	static final String uriNewFileMongo= "http://localhost:8083/api/files/newFile";
	static final String uriGetAllFilesSQL= "http://localhost:8081/api/informadoresBD/files/informerid/{id}";
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriDeleteFileMongo= "http://localhost:8083/api/files/file/delete/{id}";
	//static final String uriGetAllFilesMongo = "http://localhost:8083/api/files/informer";
	static final String uriGetAllFilesMongo = "http://localhost:8083/api/files/all";
	
	
	@Autowired
	UsersService us;
	
	@Autowired
	InformersService is;
	
	public File createFileMongoDB(User user_session,String title,String description, List<String> keywords, Double size, List<Object> data) {
	
		File file = null;
		File new_file = null;
		
		Informer informer_session = is.getInformerSession();
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			file = new File(informer_session.getId(),title,description, keywords, data,size);
			//Obtenemos la fecha y hora actual
			LocalDateTime created_date = LocalDateTime.now();
			
			//Actualizamos el campo added_date del fichero con la fecha de creación del fichero en el portal
			file.setDate(created_date);
			
			//Fichero a la BD de MongoDB
			RestTemplate restTemplate = new RestTemplate();
			new_file = restTemplate.postForObject(
				uriNewFileMongo,
				file,
				File.class);
				System.out.println("Fichero creado con éxito");
		}
		else {
			System.out.println("No se puede crear un fichero ya que el estado del informador es: " + informer_session.getStatus());
		}	
	
	return new_file;
	}
	
	public File createFileSQL(String nosql_id, Informer informer)
	{
		RestTemplate restTemplate = new RestTemplate();
		File file = new File();
		file.setInformer(informer);
		file.setPreviews(0);
		file.setDownloads(0);
		file.setId(nosql_id);
		
		//Fichero a la BD SQL
		//No va porque alli salen los ids a null
		
		System.out.println(file);
		restTemplate.postForObject(
		uriNewFileSQL,
		file,
		File.class);
		
		return file;
		
	}
	
	public File[] getFiles() {
		
		File[] files = null;

		Informer informer_session = is.getInformerSession();
	
		if(informer_session.getStatus() == Status.ACTIVO) {
			
			//Obtenemos todos los ficheros de ese informer desde SQL con su user id
			RestTemplate restTemplate = new RestTemplate();

			System.out.println("antes de getallfilesql");
			
			//Obtenemos todos los ids de ficheros de ese usuario
			String[] files_sql = restTemplate.getForObject(
					uriGetAllFilesSQL,
						String[].class, informer_session.getId());
			
			System.out.println("despues de getallfilesql");
			System.out.println(files_sql);
			
			//Recuperamos ficheros de mongo con los ficheros recuperados de SQL
			RestTemplate restTemplate2 = new RestTemplate();
			files = restTemplate2.postForObject(
					uriGetAllFilesMongo,
					files_sql,
					File[].class);
			
			System.out.println(files);
			
		}
		else
			System.out.println("No se puede obtener el listado de ficheros ya que el estado del informador es: " + informer_session.getStatus());
	
		return files;
	}
	
	public String editFile(String id, String title, String description, List<String> keywords) {
		
		String update = "Fichero actualizado.";
		Informer informer_session = is.getInformerSession();
		
		//Meter en excepción
		System.out.println(informer_session.getStatus());
		if(informer_session.getStatus() == Status.ACTIVO) {
			
			RestTemplate restTemplate = new RestTemplate();
			
			File file_update= restTemplate.getForObject(
					uriGetFileMongoId,
					File.class,id);
			
			file_update.setTitle(title);
			file_update.setDescription(description);
			file_update.setKeywords(keywords);
			
			RestTemplate restTemplate2 = new RestTemplate();
			
			restTemplate2.put(
					uriEditFileMongo,
					file_update,
					File.class);
		}
		else {update = "El fichero no se ha podido actualizar porque el productor no ha sido validado.";}
		
		
		return update;
	}
	
	public void deleteFile(String id) {
		
		Informer informer_session = is.getInformerSession();
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.delete(
					uriDeleteFileMongo,
					id);
			
			RestTemplate restTemplate2 = new RestTemplate();
			restTemplate2.delete(
					uriDeleteFileSQL,
					id);	
		}
		
	}
}

