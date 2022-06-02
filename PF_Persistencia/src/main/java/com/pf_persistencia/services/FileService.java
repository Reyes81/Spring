package com.pf_persistencia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Informer;
import com.pf_persistencia.repository.FilesRepository;

@Service
public class FileService {
	
	@Autowired
	FilesRepository fr;
	@Autowired
	InformerService is;
	
	
	public File newFile(File file)
	{
		fr.save(file);
		
		return file;
	}
	
	public void deleteFile(File file) {
		fr.delete(file);
	}
	
	public File updateFile(File file)
	{
		File file_update = fr.findById(file.getId()).get();
		file_update.setDownloads(file.getDownloads());
		file_update.setPreviews(file.getPreviews());
		file_update.setValidator(file.getValidator());
		fr.save(file_update);
		return file_update;
	}
	
	public File updateFileValidator(File file)
	{
		File file_update = fr.findById(file.getId()).get();
		file_update.setValidator(file.getValidator());
		fr.save(file_update);
		return file_update;
	}
	

	public File findById(String id) {
		
		File file = fr.findById(id).get();
		
		return file;
	}
	
	public List<File> getFilesByDownloads(){
		List<File> files = fr.findAllByOrderByDownloadsDesc();
		
		return files;
	}
	
	public File previewFile(File file) {
		
		Integer num_preview = file.getPreviews();
		
		File file_preview = fr.findById(file.getId()).get();
		file_preview.setPreviews(num_preview + 1);
		file_preview = updateFile(file_preview);
		
		return file_preview; 
	}
	
	public File downloadFile(File file) {
		
		Integer num_downloads = file.getDownloads();
		
		File file_download = fr.findById(file.getId()).get();
		file_download.setDownloads(num_downloads + 1);
		file_download = updateFile(file_download);
		
		return file_download; 
	}
	
	public List<File> getTopPreviewsDownloads(){
		List<File> files = fr.findTop10ByOrderByPreviewsDescDownloadsDesc();
		
		return files;
	}
}
