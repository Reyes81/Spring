package com.informadoresBD.domain;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Informador {
	
	public enum Status {
		PENDIENTE,
		ACTIVO,
		INACTIVO
	};
	
	public enum Type {
		FISICA,
		JURIDICA
	};
	
	@Id
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("nif")
	private String nif;
	
	@JsonProperty("name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("type")
	private Type type;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	private Status status;
	
	@JsonProperty("quote")
	private Double quote;
	
	@JsonProperty("eMail")
	private String eMail;
	
	@JsonProperty("password")
	private String password;
	
	public Informador() {}
	
	public Informador(Integer id, String nif, String name, Type type, String eMail, String password,Status status, Double quote ) {
		
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.type = type;
		this.status = status;
		this.quote = quote;
		this.eMail = eMail;
		this.password = password;
	}
	
	public Informador(Integer id, String nif, String name, Type type, String eMail, String password ) {
		
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
