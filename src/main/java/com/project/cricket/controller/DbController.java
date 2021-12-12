package com.project.cricket.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.handler.DbHandler;
import com.project.cricket.handler.MatchFileHandler;
import com.project.cricket.model.MatchJson;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.model.ResultSummary;
import com.project.cricket.repository.ResultSummaryRepository;

@RestController
public class DbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private MatchFileHandler matchFileHandler;

	@Autowired
	private DbHandler dbHandler;

	@GetMapping(value = "/resultsummary", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryFromDb() {
		LOGGER.info("Getting results summary");
		return resultSummaryRepository.findAll();
	}

	@GetMapping(value = "/resultbetweenyears", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryBetweenYears(@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary betweem years {} and {}", startYear, endYear);
		return resultSummaryRepository.findByYearBetween(startYear, endYear);
	}

	@GetMapping(value = "/resultsByClass", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryClassId(@RequestParam Integer classId) {
		LOGGER.info("Getting results summary for class {} ", classId);
		return resultSummaryRepository.findByClassId(classId);
	}

	@GetMapping(value = "/resultsByClassBetweenYears", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryClassIdBetweenYears(@RequestParam Integer classId,
			@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary for class {} between years {} and {}", classId, startYear, endYear);
		return resultSummaryRepository.findByClassIdAndYearBetween(classId, startYear, endYear);
	}

	@GetMapping(value = "/matchids", produces = APPLICATION_JSON_VALUE)
	public List<Integer> getMatchIds(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam Integer endYear) {
		return resultSummaryRepository.findByClassIdAndYearBetween(classId, startYear, endYear).stream().map(ResultSummary::getMatchId).collect(Collectors.toList());
	}

	@PostMapping(value = "/matchjsondb")
	public List<Integer> matchJsonDb() {
		List<Integer> matchIds = getMatchIds(3, 1877, 2021);
		matchIds.removeAll(matchIds);
		matchIds.add(62396);
		List<MatchJson> matchJsons = matchFileHandler.getMatchJson(matchIds);
		for (MatchJson matchJson : matchJsons) {
			dbHandler.saveMatchFromJsonToDb(matchJson);
		}
		List<Integer> resultIds = matchJsons.stream().map(MatchJson::getMatchId).collect(Collectors.toList());
		matchIds.removeAll(resultIds);
		return matchIds;
	}

	@PostMapping(value = "/matchscorecarddb")
	public List<Integer> matchScorecardDb() {
		List<Integer> matchIds = getMatchIds(3, 1877, 2021);
		matchIds.removeAll(matchIds);
		matchIds.add(62396);
		List<MatchScorecard> matchScorecards = matchFileHandler.getMatchScorecard(matchIds);
		for (MatchScorecard matchScorecard : matchScorecards) {
			LOGGER.info("{}", matchScorecard);
			if (matchScorecard.getMatch() != null) {
				LOGGER.info("{}", matchScorecard.getMatch().getDebutPlayers().size());
			} else {
				LOGGER.info("Null");
			}
			//dbHandler.saveMatchFromJsonToDb(matchScorecard);
		}
		//List<Integer> resultIds = matchScorecards.stream().map(MatchJson::getMatchId).collect(Collectors.toList());
		//matchIds.removeAll(resultIds);
		return matchIds;
	}


}
