package com.project.cricket.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.handler.MatchHandler;
import com.project.cricket.utils.FileOperationUtils;

@RestController
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private DbController dbController;

	@Autowired
	private MatchHandler matchHandler;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private ApplicationConfiguration appConfig;

	@PostMapping(value = "/matchjson")
	public ResponseEntity<List<String>> saveMatchJsonToFile(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to saveMatchJsonToFile");
		List<Integer> matchIds = getMatchIdsFromDb(classId, startYear, endYear);
		List<String> matchJson = matchHandler.getMatchJson(matchIds, true);
		return returnResponse(matchJson);
	}

	@PostMapping(value = "/matchscorecard")
	public ResponseEntity<List<String>> saveMatchScorecardToFile(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) String matchId) {
		LOGGER.info("Request to saveMatchScorecardToFile");
		List<Integer> matchIds = new ArrayList<>();
		if (classId != null) {
			if (startYear == null) {
				startYear = 1771;
			}
			if (endYear == null) {
				endYear = LocalDate.now().getYear();
			}
			matchIds = getMatchIdsFromDb(classId, startYear, endYear);
		}
		if (matchId != null) {
			List<Integer> ids = Arrays.asList(matchId.split(","))
					.stream()
					.map(String::trim)
					.map(Integer::valueOf)
					.collect(Collectors.toList());
			matchIds.addAll(ids);
		}
		List<String> matchScorecard = matchHandler.getMatchScorecard(matchIds, true);
		return returnResponse(matchScorecard);
	}

	@GetMapping(value = "/getmissingscorecard")
	public ResponseEntity<List<Integer>> getMissingMatchJsonIds(@RequestParam Integer classId, @RequestParam Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to getMissingMatchIds");
		List<Integer> matchIds = getMatchIdsFromDb(classId, startYear, endYear);
		List<Integer> fileNames = fileUtils.getMatchFilesAsInteger(appConfig.getMatchScorecardFileLocation());
		matchIds.removeAll(fileNames);
		if (!CollectionUtils.isEmpty(matchIds)) {
			return new ResponseEntity<>(matchIds, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<List<String>> returnResponse(List<String> responses) {
		if (!CollectionUtils.isEmpty(responses)) {
			return new ResponseEntity<>(responses, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private List<Integer> getMatchIdsFromDb(Integer classId, Integer startYear, Integer endYear) {
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		return dbController.getMatchIds(classId, startYear, endYear);
	}

}
