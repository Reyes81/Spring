package com.pf_persistencia.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Validator;
import com.pf_persistencia.repository.ValidatorsRepository;

@Service
public class ValidatorService {
	
	@Autowired
	ValidatorsRepository vr;
	
	public Validator findById(Integer id) {
		
		Validator validator = vr.findById(id).get();
		
		return validator;
	}
	
	public Validator findByName(String name) {
		Validator validator = vr.findByName(name);
		return validator;
	}

}
