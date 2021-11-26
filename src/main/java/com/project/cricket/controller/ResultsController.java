package com.project.cricket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.model.ResultSummary;
import com.project.cricket.scraper.ResultsScraperHandler;

@RestController
public class ResultsController {

	@Autowired
	private ResultsScraperHandler resultsScraperHandler;

	@GetMapping(value = "/results")
	public ResponseEntity<List<ResultSummary>> getMatchResults(@RequestParam int classId, @RequestParam int startYear, @RequestParam(required = false) int endYear) {
		if (endYear == 0) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperHandler.getSummary(classId, startYear, endYear, false);
		if (!CollectionUtils.isEmpty(summary)) {
			return new ResponseEntity<>(summary, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/results")
	public ResponseEntity<List<ResultSummary>> postMatchResults(@RequestParam int classId, @RequestParam int startYear, @RequestParam(required = false) int endYear) {
		if (endYear == 0) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperHandler.postSummary(classId, startYear, endYear, true);
		if (!CollectionUtils.isEmpty(summary)) {
			return new ResponseEntity<>(summary, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
