package com.api_consumers.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class File {
	
	final int maxSize=500;
	
	public enum Status {
		PENDIENTE_REVISION,
		PREPARACION,
		ERRONEO,
		PUBLICADO
	};

	private String id;

	private LocalDateTime added_date;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private List<String> keywords;
	
	//@Pattern(regexp="(.*?)\\.(json|JSON)$\r\n")
	private List<Object> data;
	
	private Status status;
	
	private Integer downloads;
	private Integer previews;
	
	
	@NotNull
	@Size(max=maxSize)
	private Double size;
	
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
		this.data = data;
		this.downloads = 0;
		this.previews = 0;
		this.size = size;
		this.informer_id = informer_id;
	}
	
	public File(Integer informer_id,String title, String description, List<String> keywords, Double size) {
		super();
		
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = Status.PENDIENTE_REVISION;
		this.size = size;
		this.downloads = 0;
		this.previews = 0;
		this.informer_id = informer_id;
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
		this.downloads = 0;
		this.previews = 0;
	}
	
	public Integer getInformerId() {
		return informer_id;
	}

	public void setInformerId(Integer informer_id) {
		this.informer_id = informer_id;
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

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setDownloads(Integer downloads)
	{
		this.downloads = downloads;
	}
	
	public void setPreviews(Integer previews)
	{
		this.previews = previews;
	}
	
	public Integer getDownloads() {
		return this.downloads;
	}
	
	public Integer getPreviews() {
		return this.previews;
	}
	
	
}