package com.pf_persistencia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="informer")
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
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name="informer_id",nullable=false)
	private Integer id;
	
	@OneToMany(mappedBy = "informer", cascade= {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<File> files = new ArrayList<File>();
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	@Column(name="nif_cif", nullable=false)
	private String nif;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="type", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Type type;
	
	@Column(name="status", nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@Column(name="quote")
	private Double quote;
	
	@Column(name="e_mail", nullable=false)
	private String eMail;
	
	@Column(name="password", nullable=false)
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
		//this.status = Status.PENDIENTE;
		this.quote = null;
		this.eMail = eMail;
		this.password = password;
	}
	
	public Informer(Integer id,String name,String nif, Type type, Status status, Double quote, String eMail, String password,User user ) {
		
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.type = type;
		this.status = status;
		this.quote = quote;
		this.eMail = eMail;
		this.password = password;
		this.userId = user;
	}
	
	public Informer(String name,String nif, Type type, Status status, Double quote, String eMail, String password,User user ) {
		
		this.nif = nif;
		this.name = name;
		this.type = type;
		this.status = status;
		this.quote = quote;
		this.eMail = eMail;
		this.password = password;
		this.userId = user;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUserId() {
		return this.userId;
	}
	
	public void setUserId(User userId) {
		this.userId= userId;
	}
	
	/*
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer user_id) {
		this.userId = user_id;
	}
	*/

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
	
	
	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	public void setFile(File file) {
		this.files.add(file);
	}
	
	
}
