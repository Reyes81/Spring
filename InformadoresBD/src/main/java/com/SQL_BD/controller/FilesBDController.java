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

import java.util.ArrayList;
import java.util.List;

import com.SQL_BD.domain.File;
import com.SQL_BD.domain.FileByUsername;
import com.SQL_BD.domain.FileInformers;
import com.SQL_BD.domain.Informer;
import com.SQL_BD.service.FilesBDService;
import com.SQL_BD.service.InformersBDService;

@RestController
@RequestMapping("/api/files")
public class FilesBDController {
	
	@Autowired
	FilesBDService fs;
	@Autowired
	InformersBDService is;
	
	@PostMapping(value="/newFile")
	public ResponseEntity<File> newFile(@RequestBody File file) {
		System.out.println(file);
		fs.newFile(file);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	@RequestMapping("/edit")
	public ResponseEntity<File> editFile(@RequestBody File file) {

		File new_file = fs.updateFile(file);
		return new ResponseEntity<>(new_file, HttpStatus.OK);
	}
	
	@RequestMapping("/informers")
	public ResponseEntity<FileInformers[]> getInformers(@RequestBody FileInformers[] file_informer) {
		
			for(FileInformers fi:file_informer)
			{
				System.out.println(fi);
				//Todos los files de un informer
				String file_informer_id = fi.getId();
				File[] files = fs.findInformerByFileId(file_informer_id);
				
				for(File f:files)
				{
					Informer informer = f.getInformer();
					//Integer informer_id = informer.getId();
					String file_id = f.getId();
					//Informer informer = is.getInformerId(informer_id);
					if(file_id.equals(file_informer_id)) {
						String name = informer.getName();
						fi.setInformer(name);
					}
				}
			}

		return new ResponseEntity<FileInformers[]>(file_informer, HttpStatus.OK);
	}
	
	@GetMapping("/file/{id}")
	public ResponseEntity<File> findById(@PathVariable(value = "id") String id) {
		File file = fs.findById(id);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<List<FileByUsername>> findByUsername(@PathVariable(value = "username") String username){
		Informer informer = is.getInformerByName(username);
		File[] files = fs.findByUserId(informer);
		
		System.out.println(files.length);
		List<FileByUsername> files_by_username = new ArrayList<FileByUsername>();
		
		for(File file:files) {
			FileByUsername file_by_username = new FileByUsername(file.getId(), file.getPreviews(), file.getDownloads(), informer.getName());
			files_by_username.add(file_by_username);
		}
		
		return new ResponseEntity<List<FileByUsername>>(files_by_username, HttpStatus.OK);
	
	}


}

