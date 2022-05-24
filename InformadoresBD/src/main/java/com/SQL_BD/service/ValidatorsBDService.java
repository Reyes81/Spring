package com.SQL_BD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SQL_BD.domain.Validator;
import com.SQL_BD.repositories.ValidatorsBDRepository;

@Service
public class ValidatorsBDService {

	@Autowired
	ValidatorsBDRepository vr;
	
	// Obtener un informador por eMail
	public Validator getValidator(String username){
		
	//Crear una excepción por si no existe el informador
		Validator validator = vr.findByeMail(username);	
		return validator;
	}
}
