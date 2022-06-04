package com.pf_persistencia_no_relacional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pf_persistencia_no_relacional.domain.File;
import com.pf_persistencia_no_relacional.domain.File.Status;
import com.pf_persistencia_no_relacional.services.FileService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class PfPersistenciaNoRelacionalApplication implements CommandLineRunner {
	
	@Autowired
	FileService fs;

	public static void main(String[] args) {
		SpringApplication.run(PfPersistenciaNoRelacionalApplication.class, args);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void run(String...strings) throws Exception {
		clearBD();
		//Inicializamos la BD
		initializeBD();
		//testEntities();
		//clearBD();
	}
	
	public void initializeBD() {
		List<String> keywords = Arrays.asList("nombre", "apellidos", "descargas");
		List<String> keywords2 = Arrays.asList("apellidos");
		String content = "[{\"nombre\":\"Nombre 1\", \"apellidos\":\"Apellidos 1\"},{\"nombre\":\"Nombre 2\", \"apellidos\":\"Apellidos 2\"},{\"nombre\":\"Nombre 3\", \"apellidos\":\"Apellidos 3\"}]";
		ObjectMapper mapper = new ObjectMapper();
		List<Object> data = new ArrayList<Object>();
		
		try { data = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
		} catch (JsonProcessingException e) {e.printStackTrace();}
		
		File file = new File(LocalDateTime.now(), "Primer fichero", "Primer fichero para inicializar la base de datos", keywords, 20.5, data, Status.PENDIENTE_REVISION);
		File file2 = new File(LocalDateTime.now(), "Segundo fichero", "Segundo fichero para inicializar la base de datos", keywords, 100.0, data, Status.PENDIENTE_REVISION);
		File file3 = new File(LocalDateTime.now(), "Tercer fichero", "Tercer fichero para inicializar la base de datos", keywords, 75.0, data, Status.PENDIENTE_REVISION);
		File file4 = new File(LocalDateTime.now(), "Cuarto fichero", "Cuarto fichero para inicializar la base de datos", keywords2, 25.5, data, Status.ERRONEO);
		File file5 = new File(LocalDateTime.now(), "Quinto fichero", "Quinto fichero para inicializar la base de datos", keywords2, 35.0, data, Status.PUBLICADO);
		
		fs.create(file);
		fs.create(file2);
		fs.create(file3);
		fs.create(file4);
		fs.create(file5);
	}
	
	public void testEntities() {
		showAllFiles();
		//showPendingFiles();
		//updateFile();
		//getFilesByKeywords();
		//getFileById();
		//deleteFileById();
	}
	
	public void showAllFiles() {
		System.out.println("----- LISTAR TODOS LOS FICHEROS -----");
		List<File> files = fs.findAll();
		
		for(File f:files) {
			System.out.println("Id: "+f.getId()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Keywords: "+f.getKeywords()+" Size: "+f.getSize()+" Data: "+f.getData()+" Status: "+f.getStatus()+" Added date: "+f.getDate());
		}
	}
	
	public void showPendingFiles() {
		System.out.println("\n----- LISTAR TODOS LOS FICHEROS PENDIENTES -----");
		List<File> pending_files = fs.getPendingFiles();
		
		for(File f:pending_files) {
			System.out.println("Id: "+f.getId()+" Status: "+f.getStatus()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Keywords: "+f.getKeywords()+" Size: "+f.getSize()+" Data: "+f.getData()+" Added date: "+f.getDate());
		}
	}
	
	public void updateFile() {
		System.out.println("\n----- ACTUALIZAR UN FICHERO -----");
		File file = fs.getFileTitle("Primer fichero");
		file.setTitle("Primer fichero actualizado");
		file.setDescription("Descripcion actualizada");
		file.setStatus(Status.PUBLICADO);
		File updated_file = fs.updateFile(file);
		System.out.println("Id: "+updated_file.getId()+" Title: "+updated_file.getTitle()+" Description: "+updated_file.getDescription()+" Keywords: "+updated_file.getKeywords()+" Size: "+updated_file.getSize()+" Data: "+updated_file.getData()+" Status: "+updated_file.getStatus()+" Added date: "+updated_file.getDate());
	}
	

	public void getFilesByKeywords() {
		System.out.println("\n----- LISTAR LOS FICHEROS QUE CONTENGAN UNA KEYWORD: nombre -----");
		List<File> files_keywords = fs.getFilesByKeyWords(1, "nombre");
		for(File f:files_keywords) {
			System.out.println("Id: "+f.getId()+" Keywords: "+f.getKeywords()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Size: "+f.getSize()+" Data: "+f.getData()+" Status: "+f.getStatus()+" Added date: "+f.getDate());
		}
		
		System.out.println("\n----- LISTAR LOS FICHEROS QUE CONTENGAN UNA KEYWORD: nombre ORDENADOS POR TAMAÑO -----");
		List<File> files_keywords_size = fs.getFilesByKeyWords(2, "nombre");
		for(File f:files_keywords_size) {
			System.out.println("Id: "+f.getId()+" Keywords: "+f.getKeywords()+" Size: "+f.getSize()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Data: "+f.getData()+" Status: "+f.getStatus()+" Added date: "+f.getDate());
		}
		
		System.out.println("\n----- LISTAR LOS FICHEROS QUE CONTENGAN UNA KEYWORD: nombre ORDRENADOS POR FECHA -----");
		List<File> files_keywords_date = fs.getFilesByKeyWords(3, "nombre");
		for(File f:files_keywords_date) {
			System.out.println("Id: "+f.getId()+" Keywords: "+f.getKeywords()+" Added date: "+f.getDate()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Size: "+f.getSize()+" Data: "+f.getData()+" Status: "+f.getStatus());
		}
	}
	
	public void getFileById() {
		
		//Para probar hay que coger un fichero que sepamos su id o hacer un findAll() y elegir un id ya que cada vez
		//que se inicializa la BD cambian los id por lo que el aparece en este método no coincidirá
		//en futuras pruebas.
		System.out.println("\n----- OBTENER UN FICHERO POR ID -----");
		File file = fs.getFileByID("629b22b5ec6ecf7f1c922ab1");
		System.out.println("Id: "+file.getId()+" Keywords: "+file.getKeywords()+" Added date: "+file.getDate()+" Title: "+file.getTitle()+" Description: "+file.getDescription()+" Size: "+file.getSize()+" Data: "+file.getData()+" Status: "+file.getStatus());
		
	}
	
	public void deleteFileById() {
		
		//Para probar hay que coger un fichero que sepamos su id o hacer un findAll() y elegir un id ya que cada vez
		//que se inicializa la BD cambian los id por lo que el aparece en este método no coincidirá
		//en futuras pruebas.
		System.out.println("\n----- ELIMINAR UN FICHERO POR ID -----");
		fs.deleteFile("629b22b5ec6ecf7f1c922ab4");
		
		//Listamos todos los ficheros para comprobar que ha sido eliminado el fichero
		List<File> files = fs.findAll();
		for(File f:files) {
			System.out.println("Id: "+f.getId()+" Title: "+f.getTitle()+" Description: "+f.getDescription()+" Keywords: "+f.getKeywords()+" Size: "+f.getSize()+" Data: "+f.getData()+" Status: "+f.getStatus()+" Added date: "+f.getDate());
		}
	}
	
	public void clearBD() {
		fs.deleteAll();
	}

}
