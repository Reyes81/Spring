package com.SQL_BD.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class Informer {
	
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "informer")
	private List<File> files;
	
	private String nif;
	

	private String name;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
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

	/*
	public List<Fichero> getFicheros() {
		return ficheros;
	}

	public void setFicheros(List<Fichero> ficheros) {
		this.ficheros = ficheros;
	}
	*/
	
}