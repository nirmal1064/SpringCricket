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

import com.project.cricket.entity.ResultSummary;
import com.project.cricket.handler.ResultsScraperHandler;

@RestController
public class ResultsController {

	@Autowired
	private ResultsScraperHandler resultsScraperHandler;

	@GetMapping(value = "/results")
	public ResponseEntity<List<ResultSummary>> getMatchResults(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam(required = false) Integer endYear) {
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperHandler.getSummary(classId, startYear, endYear, false);
		if (CollectionUtils.isEmpty(summary)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

	@PostMapping(value = "/results")
	public ResponseEntity<List<ResultSummary>> postMatchResults(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam(required = false) Integer endYear) {
		if (endYear == null || endYear < startYear) {
			endYear = startYear;
		}
		List<ResultSummary> summary = resultsScraperHandler.postSummary(classId, startYear, endYear, true);
		if (CollectionUtils.isEmpty(summary)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

}
