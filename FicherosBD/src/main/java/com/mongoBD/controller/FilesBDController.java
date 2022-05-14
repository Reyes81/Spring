package com.mongoBD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoBD.domain.File;
import com.mongoBD.service.FilesBDService;

@RestController
@RequestMapping("/api/files")
public class FilesBDController {
	
	@Autowired
	FilesBDService fs;

	@PostMapping("/newFile")
	public ResponseEntity<File> createPost(@RequestBody File file) {
		
		File f = fs.create(file);
		
		return new ResponseEntity<>(f, HttpStatus.OK);
	} 
	
	@GetMapping()
	public ResponseEntity<List<File>> getAll(HttpServletRequest request) {
		List<File> files = fs.findAll();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
}
