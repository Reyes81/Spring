package com.SQL_BD.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class File {
	
	@Id
	private String id;
	
	@ManyToOne
    //@JoinColumn(name = "id")
	private Informer informer;
	
	@ManyToOne
    //@JoinColumn(name = "id")
	private Validator validator;

	public File(String id) {
		super();
		this.id = id;
	}
	
	public File() {
		super();
	
	}
	
	
}