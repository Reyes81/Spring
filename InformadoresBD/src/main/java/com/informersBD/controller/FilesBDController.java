package com.informersBD.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informersBD.domain.File;
import com.informersBD.service.FilesBDService;

@Component
@RestController
@RequestMapping("/api/informadoresBD/")
public class FilesBDController {
	
	@Autowired
	FilesBDService fs;
	
	@PostMapping(value= "/newFile")
	public ResponseEntity<File> newInformer(@RequestBody File file) {
		fs.newFile(file);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
}
