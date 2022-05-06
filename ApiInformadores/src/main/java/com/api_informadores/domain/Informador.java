package com.api_informadores.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Informador {
	
	enum State {
		PENDIENTE,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("nif_cif")
	private String nif_cif;
	
	@JsonProperty("name_company")
	private String name_company;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("state")
	private State state;
	
	@JsonProperty("quote")
	private Double quote;
	
	@JsonProperty("eMail")
	private String eMail;
	
	@JsonProperty("password")
	private String password;
	
	public Informador() {}
	
	public Informador(Integer id, String nif_cif, String name_company, String type, Double quote, String eMail, String password ) {
		
		this.id = id;
		this.nif_cif = nif_cif;
		this.name_company = name_company;
		this.type = type;
		this.state = State.PENDIENTE;
		this.quote = quote;
		this.eMail = eMail;
		this.password = password;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNif_Cif() {
		return nif_cif;
	}

	public void setNif_Cif(String nif_cif) {
		this.nif_cif = nif_cif;
	}
	
	public String getName_Company() {
		return name_company;
	}

	public void setName_Company(String name_company) {
		this.name_company = name_company;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
