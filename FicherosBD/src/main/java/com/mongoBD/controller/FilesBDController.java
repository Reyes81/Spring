package com.mongoBD.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mongoBD.domain.File;
import com.mongoBD.service.FilesBDService;

@RestController
@RequestMapping("/api/files")
public class FilesBDController {
	
	@Autowired
	FilesBDService fs;

	@PostMapping("/newFile")
	public ResponseEntity<File> createPost(@RequestBody File file) {
		System.out.println("Id informador: " + file.getInformerId());
		File f = fs.create(file);
		
		return new ResponseEntity<>(f, HttpStatus.OK);
	} 
	
	@GetMapping()
	public ResponseEntity<List<File>> getAll(HttpServletRequest request) {
		List<File> files = fs.findAll();
		return new ResponseEntity<>(files, HttpStatus.OK);
	}
	
	@GetMapping("/informador/{informerId}")
	public List<File> getAllFilesInformer(@PathVariable(value = "informerId") Integer informerId) {
		
		List<File> files = fs.findByInformerId(informerId);
		return files;
	}
	
	@RequestMapping("/edit")
	public void updateFile(@RequestBody File file) {	
			
		fs.updateFile(file);
	}
	
	@RequestMapping("file/delete/{id}")
	public void deleteFile(@PathVariable(value = "id") String id) {
		
		fs.deleteFile(id);
	}
	
	@GetMapping("/pendientes")
	public File[] getPendingFiles()
	{
		RestTemplate restTemplate = new RestTemplate();
		
		File[] files = fs.getPendingFiles();
		
		return files;
		
	}
	
	@GetMapping("/file/{id}")
	public File getFileId(@PathVariable(value = "id") String id) {
		
		File file = fs.getFileId(id);
		
		return file;
		
	}
}
