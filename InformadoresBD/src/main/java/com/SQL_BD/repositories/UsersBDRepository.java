package com.SQL_BD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SQL_BD.domain.User;



public interface UsersBDRepository extends JpaRepository<User, Integer>  {
	
	 public User findByUsername(String username);
	
}
