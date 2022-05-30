package com.pf_persistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Informer;
import com.pf_persistencia.domain.User;
import com.pf_persistencia.domain.Validator;
import com.pf_persistencia.repository.FilesRepository;
import com.pf_persistencia.repository.InformersRepository;
import com.pf_persistencia.repository.UsersRepository;
import com.pf_persistencia.repository.ValidatorsRepository;

@Service
public class ProjectService {
	
	//Inyectamos los repositorios
		@Autowired
		FilesRepository fr;
		
		@Autowired
		UsersRepository ur;
		
		@Autowired
		InformersRepository ir;
		
		@Autowired
		ValidatorsRepository vr;
		
		
		//Crear,Actualizar y Eliminar informador
		public Informer createInformer(Informer informer) {
			ir.save(informer);
			
			return informer;
		}
		
		public Informer updateInformer(Integer id,Informer informer) {
			
			Informer informer_update = ir.findById(id).get();
			
			informer_update.setName(informer.getName());
			informer_update.setNif(informer.getNif());
			informer_update.seteMail(informer.geteMail());
			informer_update.setPassword(informer.getPassword());
			informer_update.setQuote(informer.getQuote());
			informer_update.setStatus(informer.getStatus());
			informer_update.setType(informer.getType());
			informer_update.setUserId(informer.getUserId());
			
			ir.save(informer_update);
			
			return informer_update;
		}
		
		public void deleteInformer(Integer id) {
			
			ir.deleteById(id);
		}
		
		//Crear,Actualizar y Eliminar usuario
		public User createUser(User user) {
			ur.save(user);
			
			return user;
		}
		
		public User updateUser(Integer id,User user) {
			
			User user_update = ur.findById(id).get();
			
			user_update.setUsername(user.getUsername());
			user_update.setPassword(user.getPassword());
			user_update.setRole(user.getRole());
			
			ur.save(user_update);
			
			return user_update;
		}
		
		public void deleteUser(Integer id) {
			
			ur.deleteById(id);
		}
		
		
		//Crear,Actualizar y Eliminar validador
		public Validator createValidator(Validator validator) {
			vr.save(validator);
			
			return validator;
		}
		
		public Validator updateValidator(Integer id,Validator validator) {
			
			Validator validator_update = vr.findById(id).get();
			
			validator_update.setName(validator.getName());
			validator_update.seteMail(validator.geteMail());
			validator_update.setPassword(validator.getPassword());
			validator_update.setFiles(validator.getFiles());
			validator_update.setUserId(validator.getUserId());
			
			vr.save(validator_update);
			
			return validator_update;
		}
		
		public void deleteValidator(Integer id) {
			
			vr.deleteById(id);
		}
		
		//Crear, Actualizar y Eliminar fichero
		public File createFile(File file) {
			fr.save(file);
			
			return file;
		}
		
		public File updateFile(String id, File file) {
			
			File file_update = fr.findById(id).get();
			
			file_update.setId(file.getId());
			file_update.setPreviews(file.getPreviews());
			file_update.setDownloads(file.getDownloads());
			file_update.setInformer(file.getInformer());
			file_update.setValidator(file.getValidator());
			
			fr.save(file_update);
			
			return file_update;
		}
}
