package com.informersBD.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//@Document


@Data
@Entity
public class File {
	
	enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String date;
	private String title;
	private String description;
	
	@ElementCollection(targetClass=String.class)
	private List<String> keywords;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Float size;
	private Integer previews;
	private Integer downloads;
	
	public File() {
		super();
	}
	
	public File(Integer id, String title, String description, List<String> keywords) {
		super();
		this.id =id;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		
	}

	public File(Integer id,String date, String title, String description, List<String> keywords, Float size, Integer previews,
			Integer downloads) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.description = description;
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