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
	@Column(name="id",nullable=false)
	private String id;
	
    @Column(name = "informer_user_id", nullable=false)
	private Integer informer_id;
	
    @Column(name = "validator_user_id")
	private Integer validator_id;
	
	private Integer previews;
	
	private Integer downloads;
	

	public File(Integer id) {
		super();
		this.previews = 0;
		this.downloads =0;
	}
	
	public File() {
		super();
	}
	
	
}