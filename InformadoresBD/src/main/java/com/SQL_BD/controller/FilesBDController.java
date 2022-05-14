package com.SQL_BD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SQL_BD.domain.File;
import com.SQL_BD.service.FilesBDService;

@RestController
@RequestMapping("/api/files")
public class FilesBDController {
	
	@Autowired
	FilesBDService fs;
	
	@PostMapping(value="/newFile")
	public ResponseEntity<File> newFile(@RequestBody String id) {
		File file = fs.newFile(id);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
}

