package com.project.cricket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.cricket.controller.ResultsController;
import com.project.cricket.model.ResultSummary;

@SpringBootTest
@ActiveProfiles("test")
public class CricketApplicationIT extends AbstractTestNGSpringContextTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(CricketApplicationIT.class);

	@Autowired
	private ResultsController resultsController;

	@Autowired
	private Environment env;

	@Test
	public void init() {
		String[] activeProfiles = env.getActiveProfiles();
		LOGGER.info("init test in {}", (Object)activeProfiles);
	}

	@Test
	public void testSummaryDifferentYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2020, 2021);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testSummarySameYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2020, 0);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testPostSummaryDifferentYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.postMatchResults(1, 2020, 2021);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testPostSummarySameYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.postMatchResults(1, 2020, 0);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testSummaryExceptions() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.postMatchResults(1, 2022, 0);
		Assert.assertTrue(matchResultsResponse.getBody().size() == 0);
		ResponseEntity<List<ResultSummary>> matchResultsResponse1 = resultsController.getMatchResults(1, 2022, 0);
		Assert.assertTrue(matchResultsResponse1.getBody().size() == 0);
	}
}
