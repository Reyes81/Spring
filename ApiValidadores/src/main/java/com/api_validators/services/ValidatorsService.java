package com.api_validators.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_validators.domain.Informer;
import com.api_validators.domain.User;
import com.api_validators.domain.Validator;

@Service
public class ValidatorsService {
	
	@Autowired
	UsersService us;
	
	static final String uriGetPendingInformers = "http://localhost:8081/api/informadoresBD/informadores/pendientes";
	static final String uriGetInformerId = "http://localhost:8081/api/informadoresBD/informador/id/{id}";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/informadores/modificarInfo";
	static final String uriGetAllInformes = "http://localhost:8081/api/informadoresBD/informadores";
	static final String uriGetValidator = "http://localhost:8081/api/informadoresBD/validador/{username}";
	
	public Validator getValidatorSession() {
		Validator validator_session = null;
		
		User user_session = us.getUserSession();
		
		RestTemplate restTemplate = new RestTemplate();
		validator_session = restTemplate.getForObject(
				uriGetValidator,
				 Validator.class,user_session.getUsername());
		
		System.out.println("Name: " + validator_session.getName());
		
		return validator_session; 
	}
	public Informer updateInformer(Integer id, Informer informer)
	{

		RestTemplate restTemplate = new RestTemplate();
		Informer informer_update  = restTemplate.getForObject(
				uriGetInformerId,
				 Informer.class,id);
		
		System.out.println("UserName: " + informer.geteMail());
		
			if(informer.getNif()!=null)
				informer_update.setNif(informer.getNif());
			if(informer.getName()!=null)
				informer_update.setName(informer.getName());
			if(informer.getType()!=null)
				informer_update.setType(informer.getType());
			if(informer.getStatus()!=null)
				informer_update.setStatus(informer.getStatus());
			if(informer.geteMail()!=null)
				informer_update.seteMail(informer.geteMail());
			if(informer.getPassword()!=null)
				informer_update.setPassword(informer.getPassword());
			if(informer.getQuote()!=null)
				informer_update.setQuote(informer.getQuote());
			
			us.updateUser(informer_update.getUser_id(),informer_update.geteMail(),informer_update.getPassword());
			
			RestTemplate restTemplate2 = new RestTemplate();
			restTemplate2.put(
					uriEditInformer,
					informer_update,
					Informer.class);
			
		return informer_update;
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
}
