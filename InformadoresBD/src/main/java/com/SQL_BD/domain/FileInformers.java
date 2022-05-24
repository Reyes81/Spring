package com.SQL_BD.domain;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class FileInformers {
	private String id;
	private String title;
	private String description;
	private List<Object> data;
	private String informer_name;
	
	public FileInformers()
	{
		this.id = "";
		this.title = "";
		this.description = "";
		this.informer_name="";
		
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setInformer(String informer) {
		this.informer_name = informer;
	}
	
	public void setData(List<Object> data) {
		this.data = data;
	}
	
}
