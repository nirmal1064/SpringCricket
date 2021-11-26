package com.project.cricket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.model.ResultSummary;
import com.project.cricket.scraper.ResultsScraperHandler;

@RestController
public class ResultsController {

	@Autowired
	private ResultsScraperHandler resultsScraperHandler;

	@GetMapping(value = "/results", produces = "application/json")
	public List<ResultSummary> getMatchResults(@RequestParam Integer classId, @RequestParam Integer year) throws Exception {
		return resultsScraperHandler.getSummaryByClassAndYear(classId, year);
	}

	@GetMapping(value = "/resultsyears", produces = "application/json")
	public List<ResultSummary> getMatchResults(@RequestParam int classId, @RequestParam int startYear, 
			@RequestParam int endYear, @RequestParam boolean saveToDb) {
		return resultsScraperHandler.getSummaryBetweenYears(classId, startYear, endYear, saveToDb);
	}
}
