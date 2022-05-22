package com.api_informers.services;

import java.util.List;

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
	static final String uriNewFileMongo= "http://localhost:8083/api/files/newFile";
	static final String uriGetAllFilesMongo= "http://localhost:8083/api/files/informador/{informerId}";
	
	@Autowired
	UsersService us;
	
	public File createFileMongoDB(User user_session,String title,String description, List<String> keywords, Integer size, List<Object> data) {
	
		File file = null;
		RestTemplate restTemplate = new RestTemplate();
		Informer informer_session  = restTemplate.getForObject(
				uriGetInformer,
				 Informer.class,user_session.getUsername());
		
		
		if(informer_session.getStatus() == Status.ACTIVO) {
			file = new File(informer_session.getId(),title,description, keywords, data,size);
			System.out.println("Id informador: " + file.getInformer_id());
			RestTemplate restTemplate2 = new RestTemplate();
			
			//Fichero a la BD de MongoDB
				restTemplate2.postForObject(
				uriNewFileMongo,
				file,
				File.class);
				System.out.println("Fichero creado con éxito");
		}
		else {
			System.out.println("No se puede crear un fichero ya que el estado del informador es: " + informer_session.getStatus());
		}
		
	
	return file;
	}
	
	public File[] getFiles() {
		
		File[] files = null;
		User user_session = us.getUserSession();
		
		RestTemplate restTemplate2 = new RestTemplate();
		Informer informer_session  = restTemplate2.getForObject(
				uriGetInformer,
				 Informer.class,user_session.getUsername());
		
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
}
