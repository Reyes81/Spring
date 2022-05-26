package com.SQL_BD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Validator;
import com.SQL_BD.service.FilesBDService;
import com.SQL_BD.service.ValidatorsBDService;

@RestController
@RequestMapping("/api/informadoresBD/")
public class ValidatorsBDController {
	
	@Autowired
	ValidatorsBDService vs;
	
	@Autowired
	FilesBDService fs;
	
	@GetMapping(value= "/validador/{username}")
	public ResponseEntity<Validator> getValidator(@PathVariable(value = "username") String username) {
		System.out.println(username);
		Validator validator = vs.getValidator(username);
		return new ResponseEntity<>(validator, HttpStatus.OK);
	}
}
