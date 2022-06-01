package com.pf_persistencia;

import java.util.List;

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
import com.pf_persistencia.services.FileService;
import com.pf_persistencia.services.InformerService;
import com.pf_persistencia.services.ProjectService;
import com.pf_persistencia.services.UserService;

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
	
	@Autowired
	InformerService is;
	
	@Autowired
	UserService us;
	
	@Autowired
	FileService fs;
	
	public static void main(String[] args) {
		SpringApplication.run(PfPersistenciaApplication.class, args);
			
		}
	
	@SuppressWarnings("unused")
	@Override
	public void run(String...strings) throws Exception {
		//clearBD();
		//Inicializamos la BD
		//initializeBD();
		//testEntities();
		/* Servicios especificados en los requisitos de DBCDS */
		requirementsDBCDS();
		
	}
	
	public void clearBD() {
		//Limpiamos la BD
		fr.deleteAll();
		ur.deleteAll();
		vr.deleteAll();
		ir.deleteAll();
	}
	
	public void initializeBD() {
	
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
		
	}
	
	public void testEntities() {
		
		//newUser();
		//updateUser();
		//updateInformer();
		//updateValidator();
		//updateFile();
		//deleteUser();
	}
	
	public void requirementsDBCDS() {
		String password_encode = new BCryptPasswordEncoder().encode("1234");
		//User user = new User("validador01@uv.es",password_encode,"VALIDATOR");
		//ps.createUser(user);
		
		//Validator validator = new Validator("validator01","validator01@uv.es",password_encode,user);
		//vr.save(validator);
		
		//APIproducer();
		//APIvalidator();
		APIconsumer();
		
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
	
	public void APIproducer() {
		
		//PF1. Solicitud de registro de un nuevo informador//
		User user = new User("informador01@uv.es","1234","INFORMER");
		User user2 = new User("informador02@uv.es","1234","INFORMER");
		User user3 = new User("informador03@uv.es","1234","INFORMER");
		
		us.saveUser(user);
		us.saveUser(user2);
		us.saveUser(user3);
		
		Informer informer = new Informer("informador01","0001",Type.FISICA,Status.PENDIENTE,0.0,"informador01@uv.es","1234",user);
		
		Informer informer2 = new Informer("informador02","0002",Type.FISICA,Status.PENDIENTE,0.0,"informador02@uv.es","1234",user2);
		Informer informer3 = new Informer("informador03","0003",Type.JURIDICA,Status.PENDIENTE,0.0,"informador03@uv.es","1234",user3);
		
		is.saveInformer(informer);
		is.saveInformer(informer2);
		is.saveInformer(informer3);
		
		System.out.println("----- PF1. Registrar informador -----");
		System.out.println("----- Informador registrado -----");
		System.out.println("Name: " + informer.getName());
		System.out.println("Email: " + informer.geteMail());
		System.out.println("Password: " + informer.getPassword());
		System.out.println("Tipo: " + informer.getType());
		System.out.println("Estado: " + informer.getStatus());
		System.out.println("Cuota: " + informer.getQuote());
		System.out.println("----- Informador registrado -----");
		System.out.println("Name: " + informer2.getName());
		System.out.println("Email: " + informer2.geteMail());
		System.out.println("Password: " + informer2.getPassword());
		System.out.println("Tipo: " + informer2.getType());
		System.out.println("Estado: " + informer2.getStatus());
		System.out.println("Cuota: " + informer2.getQuote());
		System.out.println("----- Informador registrado -----");
		System.out.println("Name: " + informer3.getName());
		System.out.println("Email: " + informer3.geteMail());
		System.out.println("Password: " + informer3.getPassword());
		System.out.println("Tipo: " + informer3.getType());
		System.out.println("Estado: " + informer3.getStatus());
		System.out.println("Cuota: " + informer3.getQuote());
		
		
		//PF2. Solicitud de modificación de la información de un productor//
		informer.setName("informador01_actualizado");
		informer.seteMail("informador01actualizado@uv.es");
		Informer updated_informer = is.updateInformer(informer);
		
		System.out.println("----- PF2. Modificar informador -----");
		System.out.println("----- Informador modificado -----");
		System.out.println("Name: " + updated_informer.getName());
		System.out.println("Email: " + updated_informer.geteMail());
		System.out.println("Password: " + updated_informer.getPassword());
		System.out.println("Tipo: " + updated_informer.getType());
		System.out.println("Estado: " + updated_informer.getStatus());
		System.out.println("Cuota: " + updated_informer.getQuote());
		
		//PF3. Subir un fichero de datos//
		File file = new File("00011", 100, 65, updated_informer, null);
		File file2 = new File("00022", 100, 30, informer2, null);
		File file3 = new File("00033", 100, 85, informer2, null);
		File file4 = new File("00044", 100, 20, informer3, null);
		File file5 = new File("00055", 100, 5, informer3, null);
		File file6 = new File("00066", 100, 20, informer3, null);
		
		is.saveFile(file);
		is.saveFile(file2);
		is.saveFile(file3);
		is.saveFile(file4);
		is.saveFile(file5);
		is.saveFile(file6);
		
		System.out.println("----- PF3. Subir un fichero de datos -----");
		System.out.println("----- Fichero creado -----");
		System.out.println("Id: "+ file.getId());
		System.out.println("Previews: "+ file.getPreviews());
		System.out.println("Downloads: "+ file.getDownloads());
		System.out.println("Informer id: "+ file.getInformer().getId());
		System.out.println("----- Fichero creado -----");
		System.out.println("Id: "+ file2.getId());
		System.out.println("Previews: "+ file2.getPreviews());
		System.out.println("Downloads: "+ file2.getDownloads());
		System.out.println("Informer id: "+ file2.getInformer().getId());
		System.out.println("----- Fichero creado -----");
		System.out.println("Id: "+ file3.getId());
		System.out.println("Previews: "+ file3.getPreviews());
		System.out.println("Downloads: "+ file3.getDownloads());
		System.out.println("Informer id: "+ file3.getInformer().getId());
		
		//P4. Consultar el listado de ficheros del productor//
		List<File> files = is.getFiles(updated_informer);
		System.out.println("----- PF4. Consultar ficheros de un productor -----");
		for(File f:files) {
			System.out.println("Id: "+ f.getId());
			System.out.println("Previews: "+ f.getPreviews());
			System.out.println("Downloads: "+ f.getDownloads());
			System.out.println("Informer id: "+ f.getInformer().getId());
		}
		/*
		//PF6. Eliminar un fichero de datos del productor//
		System.out.println("----- PF6. Eliminar un fichero de un productor -----");
		fs.deleteFile(files.get(0));
		*/
	}
	
	public void APIvalidator() {
		
		//VF1. Obtener el listado de productores//
		List<Informer> all_informers = is.getAllInformers();
	
		System.out.println("----- VF1. Obtener el listado de productores -----");
		for(Informer informer:all_informers) {
			System.out.println("Name: " + informer.getName());
			System.out.println("Email: " + informer.geteMail());
			System.out.println("Password: " + informer.getPassword());
			System.out.println("Tipo: " + informer.getType());
			System.out.println("Estado: " + informer.getStatus());
			System.out.println("Cuota: " + informer.getQuote());
		}
		
		//VF2. Aprobar un nuevo productor//
		Informer approved_informer = is.approveInformer(all_informers.get(1).getId());
		System.out.println("----- VF2. Aprobar un nuevo productor -----");
		System.out.println("----- Productor aprobado -----");
		System.out.println("Name: " + approved_informer.getName());
		System.out.println("Email: " + approved_informer.geteMail());
		System.out.println("Password: " + approved_informer.getPassword());
		System.out.println("Tipo: " + approved_informer.getType());
		System.out.println("Estado: " + approved_informer.getStatus());
		System.out.println("Cuota: " + approved_informer.getQuote());
		
		//VF3. Modificación de la información de un productor//
		approved_informer.setName("informador02actualizado");
		approved_informer.seteMail("informador02actualizado@uv.es");
		Informer updated_informer = is.updateInformer(approved_informer);
		
		System.out.println("----- VF3. Modificar informacion de un informador -----");
		System.out.println("----- Informador modificado -----");
		System.out.println("Name: " + updated_informer.getName());
		System.out.println("Email: " + updated_informer.geteMail());
		System.out.println("Password: " + updated_informer.getPassword());
		System.out.println("Tipo: " + updated_informer.getType());
		System.out.println("Estado: " + updated_informer.getStatus());
		System.out.println("Cuota: " + updated_informer.getQuote());
		
		//VF4. Eliminar un productor//
		System.out.println("----- VF4. Eliminar un productor -----");
		System.out.println("----- Informador eliminado -----");
		is.deleteInformer(all_informers.get(0).getId());
		
		//VF6. Preparación y publicación de un fichero//
		Validator validator = vr.findAll().get(0);
		File file = new File("00044", 100, 95, updated_informer, validator);
		is.saveFile(file);
		fs.updateFileValidator(file);
		
		System.out.println("----- VF6. Preparacion y publicacion de un fichero -----");
		System.out.println("----- Fichero publicado -----");
		System.out.println("Id: "+ file.getId());
		System.out.println("Previews: "+ file.getPreviews());
		System.out.println("Downloads: "+ file.getDownloads());
		System.out.println("Informer id: "+ file.getInformer().getId());
		System.out.println("Validator id: "+ file.getValidator().getId());
		
		// Se ponen aquí porque al principio todos están con cuota 0 y pendientes de aprobación
		//VF1-B. Obtener el listado de productores pendientes de aprobación
		  List<Informer> informers_status = is.getPendingInformers();
		  System.out.println("----- VF1-B. Obtener el listado de productores pendientes de aprobación -----");
		  for(Informer informer:informers_status) {
				System.out.println("Name: " + informer.getName());
				System.out.println("Email: " + informer.geteMail());
				System.out.println("Password: " + informer.getPassword());
				System.out.println("Tipo: " + informer.getType());
				System.out.println("Estado: " + informer.getStatus());
				System.out.println("Cuota: " + informer.getQuote());
			}
		  
		//VF1-C. Obtener el listado de productores on la cuota agotada
		  List<Informer> informers_quote = is.getQuoteConsumed();
		  System.out.println("----- VF1-C. Obtener el listado de productores con cuota agotada -----");
		  for(Informer informer:informers_quote) {
				System.out.println("Name: " + informer.getName());
				System.out.println("Email: " + informer.geteMail());
				System.out.println("Password: " + informer.getPassword());
				System.out.println("Tipo: " + informer.getType());
				System.out.println("Estado: " + informer.getStatus());
				System.out.println("Cuota: " + informer.getQuote());
			}
	}
	
	public void APIconsumer() {
		//Creamos nuevos registros para las consultas requeridas en consumidores
		/*
		User user04 = new User("informador04@uv.es","1234","INFORMER");
		User user05 = new User("informador05@uv.es","1234","INFORMER");
		User user06 = new User("informador06@uv.es","1234","INFORMER");
		User user07 = new User("validador02@uv.es","1234","VALIDATOR");
		
		us.saveUser(user04);
		us.saveUser(user05);
		us.saveUser(user06);
		us.saveUser(user07);
		
		Informer informer4 = new Informer("informador04","0004",Type.FISICA,Status.PENDIENTE,0.0,"informador04@uv.es","1234",user04);
		
		Informer informer5 = new Informer("informador05","0005",Type.FISICA,Status.ACTIVO,420.0,"informador05@uv.es","1234",user05);
		Informer informer6 = new Informer("informador05","0006",Type.JURIDICA,Status.PENDIENTE,0.0,"informador06@uv.es","1234",user06);
		
		is.saveInformer(informer4);
		is.saveInformer(informer5);
		is.saveInformer(informer6);
		
		Validator validator2 = new Validator("validator02","validator02@uv.es","1234",user07);
		vr.save(validator2);
		
		File file1 = new File("00077", 152, 95,informer4, validator2);
		File file2 = new File("00088", 86, 40, informer4, null);
		File file3 = new File("00099", 99, 55, informer4, validator2);
		File file4 = new File("00110", 1200, 50, informer4, null);
		File file5 = new File("00120", 60, 6, informer4, validator2);
		File file6 = new File("00130", 500, 90, informer5, null);
		
		is.saveFile(file1);
		is.saveFile(file2);
		is.saveFile(file3);
		is.saveFile(file4);
		is.saveFile(file5);
		is.saveFile(file6);
		*/
		//CF1. Listado de ficheros ordenados por numero de descargas//
		List<File> files_downloads = fs.getFilesByDownloads();
		
		System.out.println("----- CF1. Listado de ficheros ordenados por descargas -----");
		for(File file:files_downloads) {
			System.out.println("Id: "+ file.getId());
			System.out.println("Previews: "+ file.getPreviews());
			System.out.println("Downloads: "+ file.getDownloads());
			System.out.println("Informer id: "+ file.getInformer().getId());
		}
		
		//CF2. Listado de ficheros por nombre del productor//
		Informer informer = is.getInformerByName("informador02actualizado");
		List<File> files_username = informer.getFiles();
		
		System.out.println("----- CF2. Listado de ficheros por nombre del productor -----");
		for(File file:files_username) {
			System.out.println("Id: "+ file.getId());
			System.out.println("Previews: "+ file.getPreviews());
			System.out.println("Downloads: "+ file.getDownloads());
			System.out.println("Informer id: "+ file.getInformer().getId());
		}
		
		//CF3.Previsualizar un fichero de un fichero
		System.out.println("----- CF3. Previsualizar un fichero -----");
		File file = fs.findById("00130");
		File file_preview = fs.previewFile(file);
		System.out.println("Id: "+ file_preview.getId());
		System.out.println("Previews: "+ file_preview.getPreviews());
		System.out.println("Downloads: "+ file_preview.getDownloads());
		System.out.println("Informer id: "+ file_preview.getInformer().getId());
		
		//CF4.Descarga de un fichero de un fichero
		System.out.println("----- CF4. Descargar un fichero -----");
		File file_find = fs.findById("00033");
		File file_download = fs.downloadFile(file_find);
		System.out.println("Id: "+ file_download.getId());
		System.out.println("Previews: "+ file_download.getPreviews());
		System.out.println("Downloads: "+ file_download.getDownloads());
		System.out.println("Informer id: "+ file_download.getInformer().getId());
	}
}



