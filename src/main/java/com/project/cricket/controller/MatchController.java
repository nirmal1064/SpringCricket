package com.project.cricket.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.entity.Match;
import com.project.cricket.service.DbService;
import com.project.cricket.service.MatchFileService;
import com.project.cricket.service.MatchService;
import com.project.cricket.utils.CricUtils;
import com.project.cricket.utils.FileOperationUtils;

@RestController
@Transactional
public class MatchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private DbController dbController;

	@Autowired
	private MatchService matchService;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private MatchFileService matchFileService;

	@Autowired
	private CricUtils cricUtils;

	@Autowired
	private DbService dbService;

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
		List<String> matchJson = matchService.getMatchJson(matchIds, true, overWrite);
		return cricUtils.getListResponse(matchJson);
	}

	@PostMapping(value = "/matchscorecard")
	public ResponseEntity<List<String>> saveMatchScorecardToFile(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) List<Integer> matchId, @RequestParam Boolean overWrite) {
		LOGGER.info("Request to saveMatchScorecardToFile");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, matchId);
		List<String> matchScorecard = matchService.getMatchScorecard(matchIds, true, overWrite);
		return cricUtils.getListResponse(matchScorecard);
	}

	@GetMapping(value = "/getmissingscorecard")
	public ResponseEntity<List<Integer>> getMissingMatchScorecardIds(@RequestParam Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to getMissingMatchIds");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, null);
		List<Integer> fileNames = fileUtils.getMatchFilesAsInteger(appConfig.getMatchScorecardFileLocation());
		matchIds.removeAll(fileNames);
		return cricUtils.getListResponse(matchIds);
	}

	@GetMapping(value = "/getmissingjson")
	public ResponseEntity<List<Integer>> getMissingMatchJsonIds(@RequestParam Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear) {
		LOGGER.info("Request to getMissingMatchIds");
		List<Integer> matchIds = filterInput(classId, startYear, endYear, null);
		List<Integer> fileNames = fileUtils.getMatchFilesAsInteger(appConfig.getMatchJsonFileLocation());
		matchIds.removeAll(fileNames);
		return cricUtils.getListResponse(matchIds);
	}

	@GetMapping(value = "/missingjson")
	public ResponseEntity<List<Integer>> checkMatchJson(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) List<Integer> matchId) {
		List<Integer> matchIds = filterInput(classId, startYear, endYear, matchId);
		List<Match> matches = matchFileService.getMatches(matchIds);
		List<Integer> result = matches.parallelStream().map(Match::getMatchId).collect(Collectors.toList());
		matchIds.removeAll(result);
		return cricUtils.getListResponse(matchIds);
	}

	@PostMapping(value = "/matchfulldb")
	public ResponseEntity<List<Integer>> saveMatchToDbFromFile(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer startYear, 
			@RequestParam(required = false) Integer endYear, @RequestParam(required = false) List<Integer> matchId) {

		List<Integer> matchIds = filterInput(classId, startYear, endYear, matchId);
		List<Integer> matchIdsAlreadyPresent = dbController.getAllMatchIdsFromMatchSummary();
		List<Integer> exceptions = Arrays.asList(1104471);
		matchIds.removeAll(exceptions);
		matchIds.removeAll(matchIdsAlreadyPresent);
		List<Match> matches = matchFileService.getMatches(matchIds);
		List<Integer> result = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		LOGGER.info("Inserting {} matches", matches.size());

		result = dbService.saveMatchesInBatches(matches);

		LOGGER.info("Inserted {} matches", result.size());
		matchIds.removeAll(result);
		stopWatch.stop();
		LOGGER.info("{} Matches saved in db in {} seconds", result.size(), stopWatch.getTotalTimeSeconds());
		return cricUtils.getListResponse(matchIds);

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
