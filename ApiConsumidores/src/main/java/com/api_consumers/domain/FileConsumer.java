package com.api_consumers.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FileConsumer {
	

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
	
	@NotNull
	private Double size;
	
	private Integer informer_id;
	
	public FileConsumer() {
		super();
	}
	
	public FileConsumer(String id, String title, String description, LocalDateTime added_date, List<Object> data, List<String> keywords, Integer informer_id,  Double size) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.added_date = added_date;
		this.keywords = keywords;
		this.data = data;
		this.size = size;
		this.informer_id = informer_id;
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