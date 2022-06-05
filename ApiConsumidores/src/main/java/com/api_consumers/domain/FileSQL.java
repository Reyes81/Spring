package com.api_consumers.domain;

public class FileSQL {

	private String id;
	

    private Informer informer;
    
  
    private Validator validator;

	private Integer previews;
	
	private Integer downloads;
	
	public FileSQL(String id, Integer previews, Integer downloads,Informer informer, Validator validator) {
		super();
		this.id = id;
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		this.validator = validator;
	}
	
	public FileSQL(Integer previews, Integer downloads,Informer informer, Validator validator) {
		super();
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		this.validator = validator;
		
	}
	
	public FileSQL(Integer previews, Integer downloads,Informer informer) {
		super();
		this.previews = previews;
		this.downloads =downloads;
		this.informer = informer;
		
	}
	
	public FileSQL() {
		super();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getPreviews() {
		return this.previews;
	}
	
	public void setPreviews(Integer previews) {
		this.previews = previews;
	}
	
	public Integer getDownloads() {
		return this.downloads;
	}
	
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}
	
	public Informer getInformer() {
		return this.informer;
	}
	
	public void setInformer(Informer informer) {
		this.informer=informer;
	}
	
	public Validator getValidator() {
		return this.validator;
	}
	
	public void setValidator(Validator validator) {
		this.validator=validator;
	}
	
}