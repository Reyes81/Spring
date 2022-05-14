package com.SQL_BD.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Validator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "validator")
	private List<File> files;
	
	private String name;
	private String eMail;
	private String password;
	
	
	
	public Validator() {}

	public Validator(Integer id, String name, String eMail, String password) {

		
		this.id = id;
		this.name = name;
		this.eMail = eMail;
		this.password = password;
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
