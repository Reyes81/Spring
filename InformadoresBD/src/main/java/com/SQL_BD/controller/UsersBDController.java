package com.SQL_BD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SQL_BD.domain.User;
import com.SQL_BD.service.UsersBDService;

@RestController
@RequestMapping("/api/users")
public class UsersBDController {
	
	@Autowired
	UsersBDService us;
	
	@PostMapping(value= "/new")
	public User newUser(@RequestBody User user) {
		System.out.println("Estamos en el POST");
		user.setRole("INFORMER");
		User user_ret = us.saveUser(user);
		
		return user_ret;
	}
	
	@GetMapping(value= "/username/{username}")
	public User getUserByUserName(@PathVariable(value = "username") String username) {
		System.out.println("Estamos en el GET");
		User user =us.getUserByUserName(username);
		return user;
	}
	
	@RequestMapping(value= "/update")
	public User updtaeUser(@RequestBody User user) {
		System.out.println("Estamos en el Put del Updtae");
		User user_update = us.saveUser(user);
		
		return user_update;
	}
}
