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
	
	private String added_date;
	private String title;
	private String description;
	
	@ElementCollection(targetClass = String.class)
	private List<String> keywords;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	private Integer size;
	private Integer previews;
	private Integer downloads;
	
	public File() {
		super();
	}
	
	public File(String title, String description, List<String> keywords, Integer size) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.previews = 0;
		this.downloads = 0;
		this.size = size;
	}
	


	public File(String date, String title, String description, List<String> keywords, Integer size, Integer previews,
			Integer downloads) {
		super();
		this.added_date = date;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.previews = previews;
		this.downloads = downloads;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}