package com.SQL_BD.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="file")
public class File {
	
	@Id
	@Column(name="id",nullable=false)
	private String id;
	
	/*
    @Column(name = "validator_user_id")
	private Integer validator_id;
    */
    //TODO Relacion * a 1 con Informer
  	@ManyToOne(optional = true, fetch=FetchType.LAZY)
  	@JsonIgnore
  	@JoinColumn(name = "informer_user_id")
    private Informer informer;
    
  	//TODO Relacion * a 1 con Validator
  	@ManyToOne(optional = true, fetch=FetchType.LAZY)
  	@JoinColumn(name = "validator_user_id")
  	@JsonIgnore
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
	
	public File(Integer previews, Integer downloads,Informer informer, Validator validator) {
		super();
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		this.validator = validator;
		
	}
	
	public File(Integer previews, Integer downloads,Informer informer) {
		super();
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		
	}
	
	public File() {
		super();
	}
	
	
	public Integer getPreviews() {
		return this.previews;
	}
	
	public void setPreviews(Integer previews) {
		this.previews = previews;
	}
	
	public Integer getDownloads() {
		return this.downloads;
	}
	
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	
	public Informer getInformer() {
		return this.informer;
	}
	
	public void setInformer(Informer informer) {
		this.informer=informer;
	}
	
}