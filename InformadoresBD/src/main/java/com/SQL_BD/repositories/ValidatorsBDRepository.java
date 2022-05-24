package com.SQL_BD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SQL_BD.domain.Informer;
import com.SQL_BD.domain.Validator;

public interface ValidatorsBDRepository extends JpaRepository<Validator, Integer>{

	public Validator findByeMail(String email);
}
