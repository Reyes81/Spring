package com.SQL_BD.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="validator")
public class Validator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="validator_id",nullable=false)
	private Integer id;
	
	@Column(name="user_id")
	private Integer user_id;
	
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
