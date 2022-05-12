package com.api_validadores.domain;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class Fichero {
	
	enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};
	
	private Integer id;
	private Integer informer_id;
	
	//Conviene utilizar Date pero de momento lo dejo con String
	private String date;
	private String title;
	private List<String> keywords;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Float size;
	private Integer previews;
	private Integer downloads;
	
	public Fichero() {
		super();
	}

	public Fichero(String date, String title, List<String> keywords, Float size, Integer previews,
			Integer downloads) {
		super();
		this.date = date;
		this.title = title;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.previews = previews;
		this.downloads = downloads;
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
}