package com.api_informers.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.api_informers.domain.File;
import com.api_informers.domain.Informer;
import com.api_informers.domain.User;
import com.api_informers.services.FileService;
import com.api_informers.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/informador")
public class FileController {

	static final String uriNewFileSQL =  "http://localhost:8081/api/files/newFile";
	static final String uriGetInformer = "http://localhost:8081/api/informadoresBD/informador/{username}";
	
	private static final int List = 0;
	private static final int File = 0;
	
	@Autowired 
	FileService fs;
	
	@Autowired 
	UsersService us;
	
	//PF3. Subir un fichero de datos
	@PostMapping(value="/newFile")
	public void createPost(@RequestParam MultipartFile file, @RequestParam String title, 
										   @RequestParam String description, @RequestParam List<String> keywords,
										   @RequestParam Integer size,
										   HttpServletRequest request) throws IOException {
		
		//Comprobar que el archivo subido es de formato JSON
		String format = file.getContentType();
		
		if(!format.equals("application/json")) {
			throw new IOException("File format not supported. Only JSON is supported.");
		}
		
		User user_session = us.getUserSession();
		ObjectMapper mapper = new ObjectMapper();
		String content = new String(file.getBytes(), StandardCharsets.UTF_8);
		List<Object> data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		
		File fichero = fs.createFileMongoDB(user_session,title, description, keywords, size, data);
		
		System.out.println(fichero.getId()); //Tiene el id puesto
		fs.createFileSQL(fichero.getId(), fichero.getInformerId());
		
	} 
	
	
	//VF1.Obtenemos todos los ficheros de un informador
	@GetMapping("/files")
	 public File[] getFiles() {
		
		File[] files = fs.getFiles();
		return files;
	  }
		
	//PF4. Editar un archivo
	@RequestMapping("/file/edit/{id}")
	public void updateFile(@PathVariable(value="id") String id, @RequestBody File file) {
		fs.editFile(id, file);
	}
		
	//PF4. Editar un archivo
	@RequestMapping("/file/delete/{id}")
	public void deleteFile(@PathVariable(value="id") String id) {
		fs.deleteFile(id);
	}
		
		
	
}
