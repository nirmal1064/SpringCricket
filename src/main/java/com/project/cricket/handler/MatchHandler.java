package com.project.cricket.handler;

import static com.project.cricket.utils.Constants.MJSON_URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MatchHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchHandler.class);

	@Autowired
	private RestTemplate restTemplate;

	public String getMatchJson(int matchId) {
		String response = "";
		try {
			String url = String.format(MJSON_URL, matchId);
			response = restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return response;
	}
}
