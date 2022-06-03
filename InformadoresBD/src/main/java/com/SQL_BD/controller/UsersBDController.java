package com.SQL_BD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<User> newUser(@RequestBody User user) {
		System.out.println("Estamos en el POST");
		user.setRole("INFORMER");
		User user_ret = us.saveUser(user);
		
		return new ResponseEntity<>(user_ret, HttpStatus.OK);
	}
	
	@GetMapping(value="/username/{username}")
	public ResponseEntity<User> getUserByUserName(@PathVariable(value = "username") String username) {
		User user =us.getUserByUserName(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) {
		User user =us.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/update")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		System.out.println("Estamos en el Put del Updtae");
		User user_update = us.saveUser(user);
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
