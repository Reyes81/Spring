package com.api_informers.domain;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class File {
	
	final int maxSize=500;
	
	enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	private String id;

	private String added_date;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private List<String> keywords;
	
	//@Pattern(regexp="(.*?)\\.(json|JSON)$\r\n")
	private List<Object> data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@NotNull
	@Size(max=maxSize)
	private Double size;

	private Integer previews;
	
	private Integer downloads;
	
	private Integer informer_id;
	
	public File() {
		super();
	}
	
	public File(Integer informer_id,String title, String description, List<String> keywords, List<Object> data, Double size) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.previews = 0;
		this.downloads = 0;
		this.data = data;
		this.size = size;
		this.informer_id = informer_id;
	}
	
	public File(Integer informer_id,String title, String description, List<String> keywords, Double size) {
		super();
		
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.previews = 0;
		this.downloads = 0;
		this.size = size;
		this.informer_id = informer_id;
	}

	public File(String date, String title, String description, List<String> keywords, Double size, Integer previews,
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

	public Integer getInformerId() {
		return informer_id;
	}

	public void setInformerId(Integer informer_id) {
		this.informer_id = informer_id;
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

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
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