package com.api_informers.services;

import org.springframework.stereotype.Service;

import com.api_informers.domain.User;

@Service
public class UsersService {
	
	public User createUser(String username, String password) {
		
		User user= new User(username,password);
		return user;
	}

}
