package com.mongoBD.domain;

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
	private String added_date;
	private String title;
	private String description;
	private List<String> keywords;
	private List<Object> data;
	
	private Status status;
	
	private Integer size;
	private Integer previews;
	private Integer downloads;
	
	public File() {
		super();
	}
	
	public File(String title, String description, List<String> keywords, Integer size,Integer informerId,Integer validatorId) {
		super();
		
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.previews = 0;
		this.downloads = 0;
		this.size = size;
		this.informerId = informerId;
		this.validatorId = validatorId;
	}

	public File(String date, String title, String description, List<String> keywords, Integer size, Integer previews,
			Integer downloads, List<Object> data) {
		super();
		this.added_date = date;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.previews = previews;
		this.downloads = downloads;
		this.data = data;
	}


	public String getDate() {
		return added_date;
	}

	public void setDate(String added_date) {
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

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAdded_date() {
		return added_date;
	}

	public void setAdded_date(String added_date) {
		this.added_date = added_date;
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