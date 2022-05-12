package com.example.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.services.UserService;



@RestController
public class UserController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService us;
	
	@GetMapping("/api/whoami")
	public String getAuthenticatedUser(Principal principal) {
		LOGGER.debug("Whoami");
		return principal.getName();
	}
	
	@GetMapping("/api/users/{id}")
	public Optional<User> getUser(@PathVariable("id") String id) {
		LOGGER.debug("View user "+id);
		return us.findById(id);
	}
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		LOGGER.debug("View all users");
		return us.findAll();
	}
	
	@PostMapping("/api/users")
	public ResponseEntity<User> create(@RequestBody User user) {
		LOGGER.debug("Create user");
		User u = us.create(user);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/users/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		LOGGER.debug("Delete user id: "+id);
		this.us.delete(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	
	
}
