package com.SQL_BD.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		fs.newFile(file);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	@RequestMapping("/edit")
	public void editFile(@RequestBody File file) {
		File new_file = fs.updateFile(file);
		
	}
	
	@RequestMapping("/edit/validator")
	public ResponseEntity<String> editFileValidator(@RequestBody File file) {
		File new_file = fs.updateFileValidator(file);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@RequestMapping("/informers")
	public ResponseEntity<FileInformers[]> getInformers(@RequestBody FileInformers[] file_informer) {
		
			for(FileInformers fi:file_informer)
			{
				String file_informer_id = fi.getId();
				File[] files = fs.findInformerByFileId(file_informer_id);
				
				for(File f:files)
				{
					Informer informer = f.getInformer();
					String file_id = f.getId();
					if(file_id.equals(file_informer_id)) {
						String name = informer.getName();
						fi.setInformer(name);
					}
				}
			}

		return new ResponseEntity<FileInformers[]>(file_informer, HttpStatus.OK);
	}
	
	@GetMapping("/file/{id}")
	public File findById(@PathVariable(value = "id") String id) {
		File file = fs.findById(id);
		File file2 = new File();
		file2.setId(file.getId());
		file2.setDownloads(file.getDownloads());
		file2.setInformer(file.getInformer());
		file2.setPreviews(file.getPreviews());
		file2.setValidator(file.getValidator());
		return file2;
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<List<FileByUsername>> findByUsername(@PathVariable(value = "username") String username){
		Informer informer = is.getInformerByName(username);
		List<File> files = informer.getFiles();
		
		List<FileByUsername> files_by_username = new ArrayList<FileByUsername>();
		
		for(File file:files) {
			FileByUsername file_by_username = new FileByUsername(file.getId(), file.getPreviews(), file.getDownloads(), informer.getName());
			files_by_username.add(file_by_username);
		}
		
		return new ResponseEntity<List<FileByUsername>>(files_by_username, HttpStatus.OK);
	
	}
	@DeleteMapping("/delete/{id}")
	public void deleteFile(@PathVariable(value = "id") String id) {
			Boolean delete = fs.deleteFile(id);
	}
	
	@RequestMapping("/all")
	public List<File> findAllById(@RequestBody List<String> files_id) {
		List<File> files = new ArrayList<File>();
		for(String id:files_id) {
			File file = fs.findById(id);
			File file_aux = new File();
			file_aux.setDownloads(file.getDownloads());
			file_aux.setPreviews(file.getPreviews());
			file_aux.setInformer(file.getInformer());
			file_aux.setId(file.getId());
			files.add(file_aux);
		}
		
		files.sort(Comparator.comparing(File::getDownloads).reversed());
		
		return files;
	}
	
	@RequestMapping("/updatePreviews")
	public ResponseEntity<String> updatePreviews(@RequestBody String id){
		File file = fs.findById(id);
		HttpStatus status = HttpStatus.NOT_FOUND;

		if(file != null) {
			file.setPreviews(file.getPreviews() +1);
			fs.updateFile(file);
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>("Previews actualizadas", status);
	
	}


}

