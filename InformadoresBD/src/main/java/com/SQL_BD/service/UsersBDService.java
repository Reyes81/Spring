package com.SQL_BD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.User;
import com.SQL_BD.repositories.UsersBDRepository;

@Service
public class UsersBDService {
	
	@Autowired
	UsersBDRepository ur;
	
	public User saveUser(User user) {
		ur.save(user);
		return user;
	}
	
	public User getUserByUserName(String username) {
		User user = ur.findByUsername(username);
		return user;
	}

}