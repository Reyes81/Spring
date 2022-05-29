package com.pf_persistencia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_name", nullable=false, unique=true)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="role", nullable=false)
	private String role;
	
	
	public User() {
		super();
	}
	public User(Integer id, String username, String password,String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public User(String username, String password,String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
