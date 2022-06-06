package com.api_consumers.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FileByUsername {
	

	private String id;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;

	private LocalDateTime added_date;
	
	private String format;
	
	@NotNull
	private Double size;
	
	private Integer previews;
	
	private Integer downloads;
	
	private String username;
	

	public FileByUsername() {
		super();
	}
	
	public FileByUsername(String id, String title, String description, LocalDateTime added_date, String format, Double size, Integer previews, Integer downloads, String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.added_date = added_date;
		this.format = format;
		this.size = size;
		this.previews = previews;
		this.downloads = downloads;
		this.username = username;
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

	public String getFormat() {
		return this.format;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	
	public Integer getDownloads() {
		return this.downloads;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}