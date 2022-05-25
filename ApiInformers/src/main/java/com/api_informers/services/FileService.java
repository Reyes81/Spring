package com.api_informers.services;

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
	static final String uriNewFileMongo= "http://localhost:8083/api/files/newFile";
	static final String uriGetAllFilesMongo= "http://localhost:8083/api/files/informador/{informerId}";
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriDeleteFileMongo= "http://localhost:8083/api/files/file/delete/{id}";
	
	
	@Autowired
	UsersService us;
	
	public Informer getInformerSession() {
		
		User user_session = us.getUserSession();
		RestTemplate restTemplate = new RestTemplate();
		
		Informer informer_session  = restTemplate.getForObject(
				uriGetInformer,
				 Informer.class,user_session.getUsername());
		
		return informer_session;
	}
	
	
	public File createFileMongoDB(User user_session,String title,String description, List<String> keywords, Double size, List<Object> data) {
	
		File file = null;
		File new_file = null;
		
		Informer informer_session = getInformerSession();
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			file = new File(informer_session.getId(),title,description, keywords, data,size);
			System.out.println("Id informador: " + file.getInformer_id());
			RestTemplate restTemplate2 = new RestTemplate();
			
			//Fichero a la BD de MongoDB
			new_file = restTemplate2.postForObject(
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
	
	public File createFileSQL(String nosql_id, Integer informer_id)
	{
		RestTemplate restTemplate = new RestTemplate();
		File file = new File();
		file.setInformerId(informer_id);
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

		Informer informer_session = getInformerSession();
	
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			RestTemplate restTemplate = new RestTemplate();
					files= restTemplate.getForObject(
					uriGetAllFilesMongo,
					File[].class,informer_session.getId());
			
			for(File file:files) {
				System.out.println("ID: " + file.getId() + "\n");
				System.out.println("Title: " + file.getTitle() + "\n");
				System.out.println("ID: " + file.getDescription() + "\n");
			}
		}
		else
			System.out.println("No se puede obtener el listado de ficheros ya que el estado del informador es: " + informer_session.getStatus());
	
		return files;
	}
	
	public File editFile(String id, File file) {
		
		File file_update = null;
		
		Informer informer_session = getInformerSession();
		
		//Meter en excepción
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			
			RestTemplate restTemplate = new RestTemplate();
			file_update= restTemplate.getForObject(
			uriGetFileMongoId,
			File.class,id);
			
			if(file.getTitle()!=null)
				file_update.setTitle(file.getTitle());
			if(file.getDescription()!=null)
				file_update.setDescription(file.getDescription());
			if(file.getKeywords()!=null)
				file_update.setKeywords(file.getKeywords());
			
			RestTemplate restTemplate2 = new RestTemplate();
			
			restTemplate2.put(
					uriEditFileMongo,
					file_update,
					File.class);
		}
		
		return file_update;
	}
	
	public void deleteFile(String id) {
		Informer informer_session = getInformerSession();
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.delete(
					uriEditFileMongo,
					String.class, id);
		}
	}
}

