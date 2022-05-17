package com.SQL_BD.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="file")
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name="id",nullable=false)
	private String id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
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