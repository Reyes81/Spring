package com.mongoBD.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {
	
	enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	@Id
	private String id;
	private Integer informerId;
	private Integer validatorId;
	private LocalDateTime added_date;
	private String title;
	private String description;
	private List<String> keywords;
	private List<Object> data;
	
	private Status status;
	
	private Double size;
	
	public File() {
		super();
	}
	
	public File(String title, String description, List<String> keywords, Double size,Integer informerId,Integer validatorId) {
		super();
		
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.informerId = informerId;
		this.validatorId = validatorId;
	}

	public File(LocalDateTime date, String title, String description, List<String> keywords, Double size, Integer previews,
			Integer downloads, List<Object> data) {
		super();
		this.added_date = date;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.data = data;
	}


	public LocalDateTime getDate() {
		return added_date;
	}

	public void setDate(LocalDateTime added_date) {
		this.added_date = added_date;
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

	public Integer getInformerId() {
		return informerId;
	}

	public void setInformerId(Integer informerId) {
		this.informerId = informerId;
	}
	
	public Integer getInValidatorId() {
		return validatorId;
	}

	public void setValidatorId(Integer validatorId) {
		this.validatorId = validatorId;
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