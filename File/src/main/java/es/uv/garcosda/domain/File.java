package es.uv.garcosda.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class File {

	@Id
	private String id;
	private String added_date;
	private String title;
	private String description;
	private List<String> keywords;
	private List<Object> data;
	private Integer size;
	
	
	public File() {}
	public File(String added_date, String title, String description, List<String> keywords, List<Object> data, Integer size) {
		this.added_date = added_date;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.data = data;
		this.size = size;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	
	
}
