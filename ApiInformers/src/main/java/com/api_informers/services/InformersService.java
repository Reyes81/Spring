package com.api_informers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.Informer;
import com.api_informers.domain.Informer.Status;
import com.api_informers.domain.User;

@Service
public class InformersService {
	
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	@Autowired
	UsersService us;
	
	public Informer updateInformer(Informer informer)
	{
		User user_session = us.getUserSession();
	
		RestTemplate restTemplate = new RestTemplate();
		Informer informer_update  = restTemplate.getForObject(
				uriGetInformer,
				 Informer.class,user_session.getUsername());
		
		if(informer_update.getStatus()==Status.ACTIVO) {
		
			if(informer.getNif()!=null)
				informer_update.setNif(informer.getNif());
			if(informer.getName()!=null)
				informer_update.setName(informer.getName());
			if(informer.getPassword()!=null)
				informer_update.setPassword(informer.getPassword());
			if(informer.getType()!=null)
				informer_update.setType(informer.getType());
			if(informer.geteMail()!=null)
				informer_update.seteMail(informer.geteMail());
			
			us.updateUser(informer_update.geteMail(),informer_update.getPassword());

		}
		//return old_informer; Da error no se por que
		else 
			System.out.println("No se puede editar el informador ya que su estado es: " + informer_update.getStatus());
		
		return informer_update;
		

	}
	

}
