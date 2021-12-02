package com.project.cricket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.cricket.handler.MatchHandler;
import com.project.cricket.model.MatchJson;

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private Gson gson;

	@Autowired
	private MatchHandler matchHandler;

	@GetMapping(value = "/matchjson")
	public ResponseEntity<MatchJson> getMatchJson(@RequestParam Integer matchId) {
		LOGGER.info("/matchjson");
		String response = matchHandler.getMatchJson(matchId);
		if (StringUtils.isEmpty(response)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		MatchJson matchJson = gson.fromJson(response, MatchJson.class);
		return new ResponseEntity<>(matchJson, HttpStatus.OK);
	}

}
