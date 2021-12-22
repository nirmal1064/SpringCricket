package com.project.cricket.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.entity.Match;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.repository.MatchSummaryRepository;
import com.project.cricket.repository.ResultSummaryRepository;

/**
 * 
 * @author Nirmal Kumar
 *
 */
@RestController
public class DbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private MatchSummaryRepository matchSummaryRepository;

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

	@GetMapping(value = "/missingids", produces = APPLICATION_JSON_VALUE)
	public List<Integer> getMissingMatchIds(@RequestParam Integer classId) {
		List<Integer> summaryMatchIds = resultSummaryRepository.findByClassId(classId).stream().map(ResultSummary::getMatchId).collect(Collectors.toList());
		List<Integer> matchIds = getAllMatchIdsFromMatchSummary();
		summaryMatchIds.removeAll(matchIds);
		return summaryMatchIds;
	}

	public List<Integer> getAllMatchIdsFromMatchSummary() {
		return matchSummaryRepository.findAll().stream().map(Match::getMatchId).collect(Collectors.toList());
	}

}
