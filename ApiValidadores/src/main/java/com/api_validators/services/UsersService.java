package com.api_validators.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api_validators.domain.User;

@Service
public class UsersService {
	
	static final String uriGetUserId = "http://localhost:8081/api/users/id/{id}";
	static final String uriUpdateUser = "http://localhost:8081/api/users/update";
	static final String uriGetUserName = "http://localhost:8081/api/users/username/{username}";
	
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
			System.out.println(user_update);
			restTemplate.put(
					uriUpdateUser,
					user_update,
					 User.class);
			
			return user_update;
			
		}

}
