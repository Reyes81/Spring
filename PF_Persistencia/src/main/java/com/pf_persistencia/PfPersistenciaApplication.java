package com.pf_persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Informer;
import com.pf_persistencia.domain.Informer.Status;
import com.pf_persistencia.domain.Informer.Type;
import com.pf_persistencia.domain.User;
import com.pf_persistencia.domain.Validator;
import com.pf_persistencia.repository.FilesRepository;
import com.pf_persistencia.repository.InformersRepository;
import com.pf_persistencia.repository.UsersRepository;
import com.pf_persistencia.repository.ValidatorsRepository;
import com.pf_persistencia.services.ProjectService;

@SpringBootApplication
public class PfPersistenciaApplication implements CommandLineRunner {
	
	//Inyectamos los repositorios
	@Autowired
	FilesRepository fr;
	
	@Autowired
	UsersRepository ur;
	
	@Autowired
	InformersRepository ir;
	
	@Autowired
	ValidatorsRepository vr;
	
	//Inyectamos el servicio
	@Autowired
	ProjectService ps;
	
	
	public static void main(String[] args) {
		SpringApplication.run(PfPersistenciaApplication.class, args);
			
		}
	
	@SuppressWarnings("unused")
	@Override
	public void run(String...strings) throws Exception {
		
		//Inicializamos la BD
		initializeBD();
		testEntities();
	}
	
	public void initializeBD() {
		/*
		//Limpiamos la BD
		fr.deleteAll();
		ur.deleteAll();
		vr.deleteAll();
		ir.deleteAll();
		
		String password_encode = new BCryptPasswordEncoder().encode("12345");
		//Creamos registros en la BD para usuarios,informadores y validadores
		User user1 = new User("informador01@uv.es",password_encode,"INFORMER");
		ps.createUser(user1);
		
		Informer informer1 = new Informer("informador01","0001",Type.FISICA,Status.PENDIENTE,0.0,"informador01@uv.es",password_encode,user1);
		ir.save(informer1);
		
		User user2 = new User("informador02@uv.es",password_encode,"INFORMER");
		ps.createUser(user2);
		
		Informer informer2 = new Informer("informador02","0002",Type.FISICA,Status.ACTIVO,450.0,"informador02@uv.es",password_encode,user2);
		ir.save(informer2);
		
		User user3 = new User("informador03@uv.es",password_encode,"INFORMER");
		ps.createUser(user3);
		
		Informer informer3 = new Informer("informador03","0003",Type.FISICA,Status.ACTIVO,0.0,"informador03@uv.es",password_encode,user3);
		ir.save(informer3);
		
		User user4 = new User("informador04@uv.es",password_encode,"INFORMER");
		ps.createUser(user4);
		
		Informer informer4 = new Informer("informador04","0004",Type.JURIDICA,Status.ACTIVO,0.0,"informador04@uv.es",password_encode,user4);
		ir.save(informer4);
		
		User user5 = new User("informador05@uv.es",password_encode,"INFORMER");
		ps.createUser(user5);
		
		Informer informer5 = new Informer("informador05","0005",Type.JURIDICA,Status.PENDIENTE,0.0,"informador05@uv.es",password_encode,user5);
		ir.save(informer5);
		
		User user6 = new User("validador01@uv.es",password_encode,"VALIDATOR");
		ps.createUser(user6);
		
		Validator validator1 = new Validator("validator01","validator01@uv.es",password_encode,user6);
		vr.save(validator1);
		
		User user7 = new User("validador02@uv.es",password_encode,"VALIDATOR");
		ps.createUser(user7);
		
		Validator validator2 = new Validator("validator02","validator02@uv.es",password_encode,user7);
		vr.save(validator2);
		
		//Creamos registros en la BD para ficheros
		File file1 = new File("1",300, 246,informer1,validator1);
		fr.save(file1);
		
		File file2 = new File("2",100, 45,informer1,validator2);
		fr.save(file2);
		
		File file3 = new File("3",500, 450,informer3,validator1);
		fr.save(file3);
		
		File file4 = new File("4",440, 133,informer1,validator1);
		fr.save(file4);
		
		File file5 = new File("5",830,678,informer2,validator2);
		fr.save(file5);
		
		File file6 = new File("6",120,16,informer1,validator2);
		fr.save(file6);
		
		File file7 = new File("7",315,222,informer1,validator1);
		fr.save(file7);
		
		File file8 = new File("8",744,555,informer4,validator2);
		fr.save(file8);
		
		File file9 = new File("9",620,436,informer1,validator1);
		fr.save(file9);
		
		File file10 = new File("10",221,150,informer3,validator2);
		fr.save(file10);
		
		File file11 = new File("11",100,85,informer2,validator2);
		fr.save(file11);
		
		File file12 = new File("12",100,85,informer3,validator2);
		fr.save(file12);
		
		File file13 = new File("13",65,5,informer3,validator1);
		fr.save(file13);
		
		File file14 = new File("14",5,2,informer3,validator1);
		fr.save(file14);
		
		File file15 = new File("15",25,18,informer3,validator2);
		fr.save(file15);
		*/
	}
	
	public void testEntities() {
		
		//newUser();
		//updateUser();
		//updateInformer();
		//updateValidator();
		//updateFile();
		deleteUser();
	}
	
	public User newUser() {
		String password_encode = new BCryptPasswordEncoder().encode("12345");
		//Creamos un nuevo usuario
		User created_user = ps.createUser(new User("informador08@uv.es",password_encode,"INFORMER"));
		
		System.out.println("----- NUEVO USUARIO -----");
		System.out.println("UserName: " + created_user.getUsername());
		System.out.println("Password: " + created_user.getPassword());
		System.out.println("Role: " + created_user.getRole());
		
		return created_user;
	}
	
	public User updateUser() {
		String password_encode = new BCryptPasswordEncoder().encode("12345");
		User user = ur.findByUsername("informador08@uv.es");
		User update_user = ps.updateUser(user.getId(),new User("informador09@uv.es",password_encode,"INFORMER"));
	
		System.out.println("----- USUARIO ACTUALIZADO -----");
		System.out.println("UserName: " + update_user.getUsername());
		System.out.println("Password: " + update_user.getPassword());
		System.out.println("Role: " + update_user.getRole());
		
		return update_user;
	}
	
	public Informer updateInformer() {
	
		Informer informador = ir.findById(108).get();
		informador.setQuote(325.0);
		
		Informer informador_update = ps.updateInformer(informador);
	
		System.out.println("----- INFORMADOR ACTUALIZADO -----");
		System.out.println("Name: " + informador_update.getName());
		System.out.println("Email: " + informador_update.geteMail());
		System.out.println("Password: " + informador_update.getPassword());
		System.out.println("Tipo: " + informador_update.getType());
		System.out.println("Estado: " + informador_update.getStatus());
		System.out.println("Cuota: " + informador_update.getQuote());
		
		return informador_update;
	}
	
	public Validator updateValidator() {
		
		Validator validador = vr.findById(27).get();
		validador.setName("validador04@uv.es");
		
		Validator validador_update = ps.updateValidator(validador);
	
		System.out.println("----- VALIDADOR ACTUALIZADO -----");
		System.out.println("Name: " + validador_update.getName());
		System.out.println("Email: " + validador_update.geteMail());
		System.out.println("Password: " + validador_update.getPassword());
		
		
		return validador_update;
	}
	
	public File updateFile() {
		
		File file = fr.findById("9").get();
		file.setPreviews(615);
		
		File file_update = ps.updateFile(file);
	
		System.out.println("----- FICHERO ACTUALIZADO -----");
		System.out.println("Id: " + file_update.getId());
		System.out.println("Previews: " + file_update.getPreviews());
		System.out.println("Password: " + file_update.getDownloads());
		
		
		return file_update;
	}
	
	public void deleteUser() {
		ps.deleteUser(131);
	}
	
	public void deleteInformer() {
		ps.deleteInformer(106);
	}
	
	public void deleteValidator() {
		ps.deleteValidator(26);
	}
	
	public void deleteFile() {
		ps.deleteFile("10");
	}
}



