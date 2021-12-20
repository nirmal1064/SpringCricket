package com.project.cricket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.project.cricket.repository.BaseRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class CricketApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CricketApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Application Staring up");
		SpringApplication.run(CricketApplication.class, args);
	}

}
