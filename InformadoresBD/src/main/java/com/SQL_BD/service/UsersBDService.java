package com.SQL_BD.service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.User;
import com.SQL_BD.repositories.UsersBDRepository;

@Service
public class UsersBDService {
	
	@Autowired
	UsersBDRepository ur;
	
	public User saveUser(User user) {
		ur.save(user);
		User user_rec = ur.findByUsername(user.getUsername()); 
		return user_rec;
	}
	
	public User getUserByUserName(String username) {
		User user = ur.findByUsername(username);
		return user;
	}
	
	public User updateUser(String old_username, String username, String password) {
		
		User update_user = ur.findByUsername(old_username);
		
		update_user.setUsername(username);
		/*
		String password_encode = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password_encode);
		*/
		update_user.setPassword(password);
		
		ur.save(update_user);
		
		return update_user;
		
	}
}