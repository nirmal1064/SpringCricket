package com.project.cricket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.cricket.controller.ResultsController;
import com.project.cricket.model.ResultSummary;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CricketApplicationIT extends AbstractTestNGSpringContextTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(CricketApplicationIT.class);

	@Autowired
	private ResultsController resultsController;

	@Test
	public void init() {
		LOGGER.info("init test");
	}

	@Test
	public void testSummary() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2020, 2021);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}
}
