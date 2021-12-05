package com.project.cricket.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.handler.MatchHandler;
import com.project.cricket.handler.MatchHtmlScraperHandler;

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private DbController dbController;

	@Autowired
	private MatchHandler matchHandler;

	@Autowired
	private MatchHtmlScraperHandler matchHtmlScraperHandler;

	@PostMapping(value = "/matchjson")
	public ResponseEntity<String> saveMatchJsonToFile(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to saveMatchJsonToFile");
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<Integer> matchIds = dbController.getMatchIds(classId, startYear, endYear);
		List<String> matchJson = matchHandler.getMatchJson(matchIds, true);
		if (!CollectionUtils.isEmpty(matchJson)) {
			return new ResponseEntity<>(matchJson.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/matchscorecard")
	public ResponseEntity<String> saveMatchScorecardToFile(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to saveMatchJsonToFile");
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		Integer matchId = 1239546;
		List<Integer> matchIds = new ArrayList<>();
		matchIds.add(matchId);
		//List<Integer> matchIds = dbController.getMatchIds(classId, startYear, endYear);
		List<String> matchJson = matchHtmlScraperHandler.getMatchScorecard(matchIds, true);
		if (!CollectionUtils.isEmpty(matchJson)) {
			return new ResponseEntity<>(matchJson.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
