package com.api_informers.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api_informers.domain.User;

@Service
public class UsersService {
	
	public User createUser(String username, String password) {
		
		User user= new User(username,password);
		
		String password_encode = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password_encode);
		return user;
	}

}
