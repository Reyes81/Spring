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
		
		public void createInformer(Informer informer) {
			ir.save(informer);
		}
		
		public void createUser(User user) {
			ur.save(user);
		}
		
		public void createValidator(Validator validator) {
			vr.save(validator);
		}
		
		public void createFile(File file) {
			fr.save(file);
		}

}
