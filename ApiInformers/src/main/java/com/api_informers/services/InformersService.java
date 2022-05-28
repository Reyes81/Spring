package com.api_informers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.Informer;
import com.api_informers.domain.Informer.Status;
import com.api_informers.domain.User;

@Service
public class InformersService {
	
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	static final String uriEditInformer = "http://localhost:8081/api/informadoresBD/informadores/modificarInfo";
	static final String uriUpdateQuote = "http://localhost:8081/api/informadoresBD/informadores/actualizarCuota";
	static final String uriNewInformer = "http://localhost:8081/api/informadoresBD/new";
	static final String uriNewUser = "http://localhost:8081/api/users/new";
	
	@Autowired
	UsersService us;
	
	public Informer getInformerSession() {
		User user_session = us.getUserSession();
		RestTemplate restTemplate = new RestTemplate();
		
		Informer informer_session = restTemplate.getForObject(
								uriGetInformer,
								Informer.class,user_session.getUsername());
		
		return informer_session;
	}
	
	public Informer updateInformer(Informer informer)
	{
		Informer informer_update = getInformerSession();
		
		if(informer_update.getStatus()==Status.ACTIVO) {
			us.updateUser(informer.geteMail(),informer.getPassword());
			
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.put(
					uriEditInformer,
					informer,
					Informer.class);
		}
		else 
			System.out.println("No se puede editar el informador ya que su estado es: " + informer_update.getStatus());
		
		return informer_update;
		
	}
	
	public Informer newInformer (Informer informer) {
		
		System.out.println("Id: " + informer.getId() + "\n" +
				   "nif: " + informer.getNif() + "\n" +
				   "name: " + informer.getName() + "\n" +
				   "status: " + informer.getStatus() + "\n" +
				   "type: " + informer.getType() + "\n" + 
				   "quote: " + informer.getQuote() + "\n" + 
				   "eMail: " + informer.geteMail() + "\n" +
				   "password: " + informer.getPassword());
		
		RestTemplate restTemplate1 = new RestTemplate();
		
		RestTemplate restTemplate2 = new RestTemplate();
		User user = us.createUser(informer.geteMail(),informer.getPassword());
		
		User user2 = restTemplate2.postForObject(
				  uriNewUser,
				  user,
				  User.class);
		String password_encode = new BCryptPasswordEncoder().encode(informer.getPassword());
		informer.setPassword(password_encode);
		//informer.setUserId(user2);
		
		restTemplate1.postForObject(
				  uriNewInformer,
				  informer,
				  Informer.class);
		
		return informer;
	}
	
	public Double updateQuote(Double size) {
		
		Informer informer = getInformerSession();
		
		Double quote = informer.getQuote() - size;
				
		if(quote >= 0) {
			System.out.println("Quote: " + quote);
			informer.setQuote(quote);
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.put(
					uriUpdateQuote,
					informer,
					Informer.class);
		}
		
		return quote;
	}
}
