package com.pf_persistencia_no_relacional.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Document(collection="files")
public class File {
	
	public enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	@Id
	private String id;
	private LocalDateTime addeddate;
	private String title;
	private String description;
	private List<String> keywords;
	private List<Object> data;
	
	private Status status;
	
	private Double size;
	
	public File() {
		super();
	}
	
	public File(String title, String description, List<String> keywords, Double size) {
		super();
		
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
	}

	public File(LocalDateTime date, String title, String description, List<String> keywords, Double size, List<Object> data, Status status) {
		super();
		this.addeddate = date;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = status;
		this.size = size;
		this.data = data;
	}


	public LocalDateTime getDate() {
		return addeddate;
	}

	public void setDate(LocalDateTime added_date) {
		this.addeddate = added_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Status getStatus() {
		return status;
	}

	public void setState(Status status) {
		this.status = status;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}