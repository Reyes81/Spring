package com.api_validators.domain;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;


@Data
public class File {
	
	enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	
	private Integer id;
	private String date;
	private String title;
	private String description;
	private List<String> keywords;
	private List<Object> data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Float size;
	private Integer previews;
	private Integer downloads;
	
	public File() {
		super();
	}
	
	public File(String title, String description, List<String> keywords, List<Object> data) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.data = data;
	}

	public File(String date, String title, String description, List<String> keywords, Float size, Integer previews,
			Integer downloads, List<Object> data) {
		super();
		this.date = date;
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
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
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
	
	
}