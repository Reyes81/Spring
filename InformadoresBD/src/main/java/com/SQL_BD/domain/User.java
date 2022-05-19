package com.SQL_BD.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="user")
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="user_name", nullable=false, unique=true)
	@NotEmpty
	private String username;
	
	@Column(name="password", nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;
	
	@Column(name="role", nullable=false)
	@NotEmpty
	private String role;
	
	
	public User() {
		super();
	}
	public User(Integer id, @NotEmpty String username, @NotEmpty @Size(min = 4) String password,
			@NotEmpty String role) {
		super();
		this.id = id;
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
