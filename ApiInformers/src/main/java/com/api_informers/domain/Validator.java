package com.api_informers.domain;
import lombok.Data;

@Data
public class Validator {
	
	private Integer id;
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
	
}
