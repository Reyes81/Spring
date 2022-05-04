package com.api_informadores.domain;

import lombok.Data;

@Data
public class Fichero {
	
	//Conviene utilizar Date pero de momento lo dejo con String
	private String date;
	private String title;
	private String keywords;
	
	//Conviene utilizar Enum Pendiente de Revisión, En Preparación, Erróneo, Publicado
	private String state;
	
	private Float size;
	private Integer previews;
	private Integer downloads;
	
	public Fichero() {
		super();
	}

	public Fichero(String date, String title, String keywords, String state, Float size, Integer previews,
			Integer downloads) {
		super();
		this.date = date;
		this.title = title;
		this.keywords = keywords;
		this.state = state;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

