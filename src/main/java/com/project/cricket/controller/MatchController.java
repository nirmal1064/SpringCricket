package com.project.cricket.controller;

import static com.project.cricket.utils.Constants.MJSON_URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.project.cricket.model.MatchJson;

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Gson gson;

	@GetMapping(value = "/matchjson")
	public ResponseEntity<MatchJson> getMatchJson(@RequestParam Integer matchId) {
		try {
			String url = String.format(MJSON_URL, matchId);
			String response = restTemplate.getForObject(url, String.class);
			MatchJson matchJson = gson.fromJson(response, MatchJson.class);
			return new ResponseEntity<>(matchJson, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
