package com.pf_persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pf_persistencia.domain.User;

public interface UsersRepository extends JpaRepository<User, Integer>  {
	
	 public User findByUsername(String username);
	
}
