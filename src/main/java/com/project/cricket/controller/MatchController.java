package com.project.cricket.controller;

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

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private DbController dbController;

	@Autowired
	private MatchHandler matchHandler;

	@PostMapping(value = "/matchjson")
	public ResponseEntity<String> saveMatchJsonToFile(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to saveMatchJsonToFile");
		List<Integer> matchIds = getMatchIdsFromDb(classId, startYear, endYear);
		List<String> matchJson = matchHandler.getMatchJson(matchIds, true);
		return returnResponse(matchJson);
	}

	@PostMapping(value = "/matchscorecard")
	public ResponseEntity<String> saveMatchScorecardToFile(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to saveMatchScorecardToFile");
		List<Integer> matchIds = getMatchIdsFromDb(classId, startYear, endYear);
		List<String> matchScorecard = matchHandler.getMatchScorecard(matchIds, true);
		return returnResponse(matchScorecard);
	}

	private ResponseEntity<String> returnResponse(List<String> matchScorecard) {
		if (!CollectionUtils.isEmpty(matchScorecard)) {
			return new ResponseEntity<>(matchScorecard.get(0), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private List<Integer> getMatchIdsFromDb(Integer classId, Integer startYear, Integer endYear) {
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<Integer> matchIds = dbController.getMatchIds(classId, startYear, endYear);
		return matchIds;
	}

}
