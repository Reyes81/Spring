package com.pf_persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pf_persistencia.domain.File;
import com.pf_persistencia.domain.Informer;
import com.pf_persistencia.domain.Validator;


@Repository
public interface FilesRepository extends JpaRepository<File, String>{
	
	public List<File> findAllByOrderByDownloadsDesc();
	
	public List<File> findTop10ByOrderByPreviewsDescDownloadsDesc();
	
	public List<File> findAllByValidator(Validator validator);
	
	public List<File> findAllByValidatorAndInformer(Validator validator, Informer informer);
	

}
