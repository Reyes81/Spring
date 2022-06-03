package com.api_validators.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.api_validators.BroadcastConfig;
import com.api_validators.domain.File;
import com.api_validators.domain.FileInformers;
import com.api_validators.domain.File.Status;
import com.api_validators.domain.Informer;
import com.api_validators.domain.User;
import com.api_validators.domain.Validator;

@Service
public class ValidatorsService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	UsersService us;
	
	
	//Definimos URLs a las Apis de MongoDB y SQL
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetPendingFilesInformers= "http://localhost:8081/api/files/informers";
	static final String uriGetInformerId = "http://localhost:8081/api/informadoresBD/informador/id/{id}";
	static final String uriGetInformersQuote = "http://localhost:8081/api/informadoresBD/informadores/cuota";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/informadores/modificarInfo";
	static final String uriDeleteInformer = "http://localhost:8081/api/informadoresBD/informadores/eliminar/{id}";
	static final String uriSuspendInformer = "http://localhost:8081/api/informadoresBD/informadores/inactivo/{id}";
	static final String uriValidateInformer = "http://localhost:8081/api/informadoresBD/informadores/validar/{id}";
	static final String uriGetFileSQL= "http://localhost:8081/api/files/file/{id}";
	static final String uriGetValidator = "http://localhost:8081/api/informadoresBD/validador/{username}";
	
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriGetAllFiles= "http://localhost:8083/api/files";
	static final String uriGetPendingFiles = "http://localhost:8083/api/files/pendientes";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit/validator";
	
	
	//Obtenemos el validador correspondiente al usuario de la sesión
	public Validator getValidatorSession() {
		Validator validator_session = null;
		User user_session = us.getUserSession();
		
		RestTemplate restTemplate = new RestTemplate();
		
		validator_session = restTemplate.getForObject(
						uriGetValidator,
						Validator.class,user_session.getUsername());
		
		return validator_session; 
	}
	
	//VF1. Todos los informadores
	public Informer[] getAllInformers () {
		
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
				  uriGetAllInformes,
				  Informer[].class);
		
		
		return informadores;
	}
	
	
	//VF1. Informadores pendientes de aprobación
	public Informer[] getPendingInformers(){
		
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetPendingInformers,
					 Informer[].class);
		
		return informadores;
	}
	
	//VF1. Informadores que han consumido su cuota anual
	public Informer[] getInformersQuoteConsumed() {	
		
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetInformersQuote,
					 Informer[].class);

        return informadores;
	}
	
	//VF1. Informadores con ficheros erróneos
	public List<Informer> getInformersErrorFiles() {	
		
		//Declaramos arrays para almacenar los ficheros erróneos y los informadores de esos ficheros
		List<File> files_error = new ArrayList<File>();
		List<Informer> informadores_files_error = new ArrayList<Informer>();
		
		RestTemplate restTemplate = new RestTemplate();
		
		//Obtenemos todos los ficheros
		File[] files = restTemplate.getForObject(
				 uriGetAllFiles,
				 File[].class);
		
		//Nos quedamos solo con los ficheros erróneos
		for(File file:files) {
			if(file.getStatus()==Status.ERRONEO) {
				files_error.add(file);
				informadores_files_error.add(file.getInformer());
			}
		}
		
		/*
		//Para cada fichero erróneo obtenemos su informador
		for(File file:files) {
			Informer informador = restTemplate.getForObject(
					 uriGetInformerId,
					 Informer.class, file.getInformer_id());
			
			informadores_files_error.add(informador);
			}*/
		
		//Devolvemos el listado de informadores con ficheros erróneos
		return informadores_files_error;
	}

        
	//VF2
	public void validateInformer(@PathVariable(value = "id") Integer id) {	
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriValidateInformer, Integer.class, id);
	}
	
	//VF3
	public Informer updateInformer(Integer id, Informer informer){

		RestTemplate restTemplate = new RestTemplate();
		Informer informer_update  = restTemplate.getForObject(
								uriGetInformerId,
								Informer.class,id);
			
		us.updateUser(informer_update.getUser_id(),informer.geteMail(),informer.getPassword());
		informer.setUser_id(informer_update.getUser_id());
			
		RestTemplate restTemplate2 = new RestTemplate();
		restTemplate2.put(
						uriEditInformer,
						informer,
						Informer.class);
		
		return informer;
	}
	
	//VF4
	public void deleteInformer(@PathVariable(value = "id") Integer id) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriDeleteInformer, Integer.class, id);
	}
	
	//VF4. EXTRA
	public void suspendInformer(@PathVariable(value = "id") Integer id) {	
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uriSuspendInformer, Integer.class, id);
	}
	
	
	//VF5
	public FileInformers[] getPendingFiles(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		//Ficheros pendientes de revision
		File[] files = restTemplate.getForObject(
					 uriGetPendingFiles,
					 File[].class);
		
		List<FileInformers> file_informers = new ArrayList<FileInformers>();
		
		for(File file:files) {
			FileInformers file_informer = new FileInformers(file.getId(), file.getDescription(), file.getData(), file.getTitle() );
			file_informers.add(file_informer);
		}
	
		FileInformers[] complete_files_informers = restTemplate.postForObject(
							uriGetPendingFilesInformers,
							file_informers,
							FileInformers[].class);
		
		return complete_files_informers;
	}
	

	
	public void publishFile(String id) {
		
		Validator validator_session = getValidatorSession();

		RestTemplate restTemplate = new RestTemplate();
		File file = restTemplate.getForObject(
		uriGetFileMongoId,
		File.class,id);

		file.setStatus(Status.PREPARACION);
		
		RestTemplate restTemplate2 = new RestTemplate();
		RestTemplate restTemplate3 = new RestTemplate();

		restTemplate2.put(
				uriEditFileMongo,
				file,
				File.class);
		
		
		File file_sql = restTemplate.getForObject(
				uriGetFileSQL,
				File.class,id);
		file_sql.setValidator(validator_session);
		
		System.out.println("Validadoor: " + validator_session.getName());
		System.out.println("File_SQL: " + file_sql.getId());
		restTemplate3.put(
				uriEditFileSQL,
				file_sql,
				File.class);
		
		String routingKey = "file.validador";
		rabbitTemplate.convertAndSend(BroadcastConfig.TOPIC_EXCHANGE_NAME, routingKey, file);
	}
}
