package com.api_validators.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_validators.BroadcastConfig;
import com.api_validators.domain.File;
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
	
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetInformerId = "http://localhost:8081/api/informadoresBD/informador/id/{id}";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/informadores/modificarInfo";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriGetValidator = "http://localhost:8081/api/informadoresBD/validador/{username}";
	static final String uriGetFileMongoId= "http://localhost:8083/api/files/file/{fileId}";
	static final String uriEditFileMongo= "http://localhost:8083/api/files/edit";
	static final String uriEditFileSQL= "http://localhost:8081/api/files/edit";
	
	public Validator getValidatorSession() {
		Validator validator_session = null;
		User user_session = us.getUserSession();
		
		RestTemplate restTemplate = new RestTemplate();
		
		validator_session = restTemplate.getForObject(
						uriGetValidator,
						Validator.class,user_session.getUsername());
		
		return validator_session; 
	}
	
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
	
	public Informer[] getAllInformers () {
		
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
				  uriGetAllInformes,
				  Informer[].class);
		
		
		return informadores;
	}
	
	public Informer[] getPendingInformers(){
		
		RestTemplate restTemplate = new RestTemplate();
		Informer[] informadores = restTemplate.getForObject(
					 uriGetPendingInformers,
					 Informer[].class);
		
		return informadores;
	}
	
	public void publishFile(String id) {
		
		Validator validator_session = getValidatorSession();
		
		RestTemplate restTemplate = new RestTemplate();
		File file= restTemplate.getForObject(
		uriGetFileMongoId,
		File.class,id);
		
		file.setStatus(Status.PREPARACION);
		file.setValidatorId(validator_session.getId());
		
		RestTemplate restTemplate2 = new RestTemplate();
		RestTemplate restTemplate3 = new RestTemplate();
		
		restTemplate2.put(
				uriEditFileMongo,
				file,
				File.class);
		
		restTemplate3.put(
				uriEditFileSQL,
				file,
				File.class);
		
		String routingKey = "file.validador";
		rabbitTemplate.convertAndSend(BroadcastConfig.TOPIC_EXCHANGE_NAME, routingKey, file);
	}
}
