package com.project.cricket.controller.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.model.ResultSummary;
import com.project.cricket.repository.ResultSummaryRepository;

@RestController
public class DbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@GetMapping(value = "/resultsummary", produces = "application/json")
	public List<ResultSummary> getResultsSummaryFromDb() {
		LOGGER.info("Getting results summary");
		return resultSummaryRepository.findAll();
	}

	@GetMapping(value = "/resultbetweenyears", produces = "application/json")
	public List<ResultSummary> getResultsSummaryBetweenYears(@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary betweem years {} and {}", startYear, endYear);
		return resultSummaryRepository.findByYearBetween(startYear, endYear);
	}

	@GetMapping(value = "/resultsByClass", produces = "application/json")
	public List<ResultSummary> getResultsSummaryClassId(@RequestParam Integer classId) {
		LOGGER.info("Getting results summary for class {} ", classId);
		return resultSummaryRepository.findByClassId(classId);
	}

	@GetMapping(value = "/resultsByClassBetweenYears", produces = "application/json")
	public List<ResultSummary> getResultsSummaryClassIdBetweenYears(@RequestParam Integer classId,
			@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary for class {} between years {} and {}", classId, startYear, endYear);
		return resultSummaryRepository.findByClassIdAndYearBetween(classId, startYear, endYear);
	}

}
