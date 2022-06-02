package com.pf_persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_persistencia.domain.Validator;

public interface ValidatorsRepository extends JpaRepository<Validator, Integer>{

	public Validator findByeMail(String email);
	
	public Validator findByName(String name);
}