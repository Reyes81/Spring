package com.SQL_BD.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="validator")
public class Validator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="validator_id",nullable=false)
	private Integer id;
	
	@OneToMany(mappedBy = "validator", cascade= {CascadeType.ALL})
	private List<File> files;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	private String name;
	@Column(name="e_mail", nullable=false)
	private String eMail;
	private String password;
	

	public Validator() {}

	public Validator(Integer id, String name, String eMail, String password) {

		this.id = id;
		this.name = name;
		this.eMail = eMail;
		this.password = password;
	}
	
	public Validator(String name, String eMail, String password,User user) {

		this.name = name;
		this.eMail = eMail;
		this.password = password;
		this.userId = user;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	
	}
}
