package com.project.cricket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.controller.db.DbController;
import com.project.cricket.handler.MatchHandler;

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private DbController dbController;

	@Autowired
	private MatchHandler matchHandler;

	@GetMapping(value = "/matchjson")
	public ResponseEntity<String> getMatchJson() {
		LOGGER.info("/matchjson");
		List<Integer> matchIds = dbController.getMatchIds(1, 2020, 2020);
		List<String> matchJson = matchHandler.getMatchJson(matchIds, true);
		if (!CollectionUtils.isEmpty(matchJson)) {
			return new ResponseEntity<>(matchJson.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
