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
  	@ManyToOne(fetch=FetchType.LAZY)
  	@JoinColumn(name = "informer_user_id")
    private Informer informer;
    
  	//TODO Relacion * a 1 con Validator
  	@ManyToOne(fetch=FetchType.LAZY)
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
	
}
