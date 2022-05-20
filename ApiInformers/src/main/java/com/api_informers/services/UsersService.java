package com.api_informers.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_informers.domain.User;

@Service
public class UsersService {
	
	static final String uriGetUserName = "http://localhost:8081/api/users/username/{username}";
	static final String uriUpdatdeUser = "http://localhost:8081/api/users/update";
	
	public User createUser(String username, String password) {
		
		User user= new User(username,password);
		
		String password_encode = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password_encode);
		return user;
	}
	
	public User getUserSession() {
		
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    RestTemplate restTemplate = new RestTemplate();
			user = restTemplate.getForObject(
					 uriGetUserName,
					 User.class,currentUserName);
		   
		}
		
	    return user;
	}
	
public User updateUser(String username, String password) {
		
		User user_update = getUserSession();
		
		user_update.setUsername(username);
		
		String password_encode = new BCryptPasswordEncoder().encode(password);
		user_update.setPassword(password_encode);
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.put(
				uriUpdatdeUser,
				user_update,
				 User.class);
		
		return user_update;
		
	}

}
