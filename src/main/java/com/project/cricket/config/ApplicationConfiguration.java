package com.project.cricket.config;

import static com.project.cricket.utils.Constants.DATETIMEFORMAT;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import lombok.Getter;

@Configuration
@Getter
public class ApplicationConfiguration {

	@Value("${threads}")
	private int numOfThreads;

	@Value("${file.matchjson.location}")
	private String matchJsonFileLocation;

	@Value("${file.matchscorecard.location}")
	private String matchScorecardFileLocation;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	@SuppressWarnings("rawtypes")
	@Bean
	public FactoryBean serviceBean() {
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean.setServiceLocatorInterface(ServiceFactory.class);
		return serviceLocatorFactoryBean;
	}

	@Bean
	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
			@Override
			public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				String str = json.getAsJsonPrimitive().getAsString();
				if (StringUtils.hasLength(str)) {
					return LocalDate.parse(str);
				}
				return null;
			}
		});
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				String str = json.getAsJsonPrimitive().getAsString();
				if (StringUtils.hasLength(str)) {
					try {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIMEFORMAT);
						return LocalDateTime.parse(str, dtf);
					} catch (Exception e) {
						try {
							LocalDate.parse(str);
							return LocalDate.parse(str).atStartOfDay();
						} catch (Exception e2) {
							LOGGER.info("LocalDateTime Exception for {}", json.getAsString());
						}
						LOGGER.info("LocalDateTime Exception for {}", json.getAsString());
						return null;
					}
				}
				return null;
			}
		});
		gsonBuilder.registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
			@Override
			public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				String str = json.getAsJsonPrimitive().getAsString();
				if (StringUtils.hasLength(str)) {
					return LocalTime.parse(str);
				}
				return null;
			}
		});
		gsonBuilder.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
			@Override
			public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				String str = json.getAsJsonPrimitive().getAsString();
				try {
					return Integer.valueOf(str);
				} catch (Exception e) {
					return null;
				}
			}
		});
		return gsonBuilder.create();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@PostConstruct
	public void init() {
		LOGGER.info("App Config initialised");
	}

}
