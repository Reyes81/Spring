package com.pf_persistencia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class File {
	
	@Id
	@Column(name="id",nullable=false)
	private String id;
	
    //TODO Relacion * a 1 con Informer
  	@ManyToOne(fetch=FetchType.EAGER)
  	@JoinColumn(name = "informer_user_id")
    private Informer informer;
    
  	//TODO Relacion * a 1 con Validator
  	@ManyToOne(fetch=FetchType.EAGER)
  	@JoinColumn(name = "validator_user_id")
    private Validator validator;
	
	private Integer previews;
	
	private Integer downloads;
	

	public File(String id, Integer previews, Integer downloads,Informer informer, Validator validator) {
		super();
		this.id = id;
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		this.validator = validator;
	}
	
	public File() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Informer getInformer() {
		return informer;
	}

	public void setInformer(Informer informer) {
		this.informer = informer;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public Integer getPreviews() {
		return previews;
	}

	public void setPreviews(Integer previews) {
		this.previews = previews;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	
	
	
}
