package com.project.cricket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.entity.ResultSummary;
import com.project.cricket.service.ResultsScraperService;
import com.project.cricket.utils.CricUtils;

@RestController
public class ResultsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsController.class);

	@Autowired
	private ResultsScraperService resultsScraperService;

	@Autowired
	private CricUtils cricUtils;

	@GetMapping(value = "/results")
	public ResponseEntity<List<ResultSummary>> getMatchResults(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam(required = false) Integer endYear) {
		LOGGER.info("Getting results");
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperService.getSummary(classId, startYear, endYear, false);
		return cricUtils.getListResponse(summary);
	}

	@PostMapping(value = "/results")
	public ResponseEntity<Integer> postMatchResults(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam(required = false) Integer endYear) {
		LOGGER.info("Posting results");
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperService.postSummary(classId, startYear, endYear, true);
		return cricUtils.getIntegerResponse(summary.size());
	}

}
