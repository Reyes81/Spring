package com.informersBD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class InformersBDApplication {

	public static void main(String[] args) {
		SpringApplication.run(InformersBDApplication.class, args);
	}

}
