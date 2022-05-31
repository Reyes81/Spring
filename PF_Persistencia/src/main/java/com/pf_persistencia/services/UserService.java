package com.pf_persistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pf_persistencia.domain.User;
import com.pf_persistencia.repository.UsersRepository;

@Service
public class UserService {
	
	@Autowired
	UsersRepository ur;
	

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
}