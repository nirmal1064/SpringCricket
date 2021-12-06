package com.project.cricket.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * 
	 * @param classId
	 * @param startYear
	 * @param endYear
	 * @param matchId
	 * @return
	 */
	@PostMapping(value = "/matchjson")
	public ResponseEntity<List<String>> saveMatchJsonToFile(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) List<Integer> matchId, @RequestParam Boolean overWrite) {
		LOGGER.info("Request to saveMatchJsonToFile");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, matchId);
		List<String> matchJson = matchHandler.getMatchJson(matchIds, true, overWrite);
		return returnResponse(matchJson);
	}

	@PostMapping(value = "/matchscorecard")
	public ResponseEntity<List<String>> saveMatchScorecardToFile(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) List<Integer> matchId, @RequestParam Boolean overWrite) {
		LOGGER.info("Request to saveMatchScorecardToFile");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, matchId);
		List<String> matchScorecard = matchHandler.getMatchScorecard(matchIds, true, overWrite);
		return returnResponse(matchScorecard);
	}

	@GetMapping(value = "/getmissingscorecard")
	public ResponseEntity<List<Integer>> getMissingMatchScorecardIds(@RequestParam Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to getMissingMatchIds");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, null);
		List<Integer> fileNames = fileUtils.getMatchFilesAsInteger(appConfig.getMatchScorecardFileLocation());
		matchIds.removeAll(fileNames);
		return returnResponse(matchIds);
	}

	@GetMapping(value = "/getmissingjson")
	public ResponseEntity<List<Integer>> getMissingMatchJsonIds(@RequestParam Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to getMissingMatchIds");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, null);
		List<Integer> fileNames = fileUtils.getMatchFilesAsInteger(appConfig.getMatchJsonFileLocation());
		matchIds.removeAll(fileNames);
		return returnResponse(matchIds);
	}

	private <T> ResponseEntity<List<T>> returnResponse(List<T> response) {
		if (!CollectionUtils.isEmpty(response)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private List<Integer> filterInput(Integer classId, Integer startYear, Integer endYear, List<Integer> matchId) {
		List<Integer> matchIds = new ArrayList<>();
		if (classId != null) {
			if (startYear == null) {
				startYear = 1771;
			}
			if (endYear == null) {
				endYear = LocalDate.now().getYear();
			}
			matchIds = dbController.getMatchIds(classId, startYear, endYear);
		}
		if (!CollectionUtils.isEmpty(matchId)) {
			matchIds.addAll(matchId);
		}
		return matchIds;
	}

}
