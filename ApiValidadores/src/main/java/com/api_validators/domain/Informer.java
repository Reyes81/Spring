package com.api_validators.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class Informer {
	
	public enum Status {
		PENDIENTE,
		ACTIVO,
		INACTIVO
	};
	
	enum Type {
		FISICA,
		JURIDICA
	};
	
	@Id
	private Integer id;
	private Integer user_id;
	private String nif;
	private String name;
	private Type type;
	private Status status;
	private Double quote;
	private String eMail;
	private String password;
	
	public Informer() {}
	
	public Informer(Integer id, String nif, String name, Type type, String eMail, String password,Status status, Double quote ) {
		
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.type = type;
		this.status = status;
		this.quote = quote;
		this.eMail = eMail;
		this.password = password;
	}
	
	public Informer(Integer id, String nif, String name, Type type, String eMail, String password ) {
		
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.type = type;
		this.status = Status.PENDIENTE;
		this.quote = null;
		this.eMail = eMail;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getUserId() {
		return this.user_id;
	}

	public void setUserId(Integer user_id) {
		this.id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Double getQuote() {
		return quote;
	}

	public void setQuote(Double quote) {
		this.quote = quote;
	}
	
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
