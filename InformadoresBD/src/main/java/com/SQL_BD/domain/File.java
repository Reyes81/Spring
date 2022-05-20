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
	@Column(name="id",nullable=false)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "informer_user_id", nullable=false)
	private Informer informer;
	
	@ManyToOne
    @JoinColumn(name = "validator_user_id",nullable=false)
	private Validator validator;
	
	private Integer previews;
	
	private Integer downloads;

	public File(Integer id, Integer previews, Integer downloads) {
		super();
		this.id = id;
		this.previews = previews;
		this.downloads =downloads;
	}
	
	public File() {
		super();
	
	}
	
	
}