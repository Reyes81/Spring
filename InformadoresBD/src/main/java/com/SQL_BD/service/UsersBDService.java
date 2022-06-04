package com.SQL_BD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.User;
import com.SQL_BD.repositories.UsersBDRepository;

@Service
public class UsersBDService {
	
	static final String uriGetUserName = "http://localhost:8081/api/users/username/{username}";
	
	@Autowired
	UsersBDRepository ur;
	
	@Autowired
	InformersBDService is;
	
	public User saveUser(User user) {
		ur.save(user);
		User user_rec = ur.findByUsername(user.getUsername()); 
		return user_rec;
	}
	
	public User getUserByUserName(String username) {
		User user = ur.findByUsername(username);
		return user;
	}
	
	public User getUserById(Integer id) {
		User user = ur.getById(id);
		return user;
	}
	
	public User updateUser(User user) {
		ur.save(user); 
		return user;
	}
	
	public void deleteUser(Integer id) {
		
		ur.deleteById(is.getInformerId(id).getUserId().getId());
	}
}