package com.informersBD.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.informersBD.domain.File;


@Repository
public interface FilesBDRepository extends JpaRepository<File, Integer>{

}
