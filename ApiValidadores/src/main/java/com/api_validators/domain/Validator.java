package com.api_validators.domain;

import lombok.Data;

@Data
public class Validator {
	
	private Integer Id;
	private String name;
	private String eMail;
	private String password;
	
	public Validator() {}

	public Validator(Integer id, String name, String eMail, String password) {

		Id = id;
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
	
}