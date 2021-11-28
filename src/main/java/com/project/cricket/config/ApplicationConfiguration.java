package com.project.cricket.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Configuration
public class ApplicationConfiguration {

	@Value("${threads}")
	private int numOfThreads;

	/**
	 * @return the numOfThreads
	 */
	public int getNumOfThreads() {
		return numOfThreads;
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public FactoryBean serviceBean() {
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean.setServiceLocatorInterface(ServiceFactory.class);
		return serviceLocatorFactoryBean;
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
