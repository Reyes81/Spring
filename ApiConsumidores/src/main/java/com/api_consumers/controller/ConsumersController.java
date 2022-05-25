package com.api_consumers.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_consumers.domain.File;
import com.api_consumers.domain.FileConsumer;
import com.api_consumers.services.ConsumersService;

@RestController
@RequestMapping("/api/consumidor")
public class ConsumersController {
	
	@Autowired
	ConsumersService cs;
	
		//CF4.Descarga de un fichero
		@GetMapping("/files/{id}")
		 public FileConsumer downloadFile(@PathVariable(value="id") String id) {
			
			FileConsumer file = cs.downloadFile(id);
			
			return file;
		  }

}
