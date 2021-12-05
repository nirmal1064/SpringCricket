package com.project.cricket;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.cricket.controller.DbController;
import com.project.cricket.controller.ResultsController;
import com.project.cricket.handler.DbHandler;
import com.project.cricket.model.ResultSummary;

@SpringBootTest
@ActiveProfiles("test")
public class CricketApplicationIT extends AbstractTestNGSpringContextTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(CricketApplicationIT.class);

	@Autowired
	private ResultsController resultsController;

	@Autowired
	private DbController dbController;

	@Autowired
	private DbHandler dbHandler;

	@Autowired
	private Environment env;

	private List<ResultSummary> matchResults = new ArrayList<>();

	@BeforeClass
	public void init() {
		String[] activeProfiles = env.getActiveProfiles();
		LOGGER.info("init test in {}", (Object)activeProfiles);
		matchResults = resultsController.getMatchResults(1, 2020, null).getBody();
	}

	@Test
	public void testSummaryDifferentYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2020, 2021);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testSummarySameYear() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2020, null);
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
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.postMatchResults(1, 2020, null);
		List<ResultSummary> matchResults = matchResultsResponse.getBody();
		Assert.assertTrue(matchResults.size() > 0);
	}

	@Test
	public void testPostSummaryExceptions() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.postMatchResults(1, 1234, 0);
		Assert.assertNull(matchResultsResponse.getBody());
	}

	@Test
	public void testGetSummaryExceptions() {
		ResponseEntity<List<ResultSummary>> matchResultsResponse = resultsController.getMatchResults(1, 2022, 0);
		Assert.assertNull(matchResultsResponse.getBody());
	}

	@Test
	public void testDbController() {
		int resultSize = dbHandler.saveResultsSummaryToDb(matchResults);
		Assert.assertNotEquals(resultSize, 0, "No Records to save in db");
		Assert.assertTrue(!CollectionUtils.isEmpty(dbController.getResultsSummaryFromDb()));
		Assert.assertTrue(!CollectionUtils.isEmpty(dbController.getResultsSummaryBetweenYears(2020, 2021)));
		Assert.assertTrue(!CollectionUtils.isEmpty(dbController.getResultsSummaryClassId(1)));
		Assert.assertTrue(!CollectionUtils.isEmpty(dbController.getResultsSummaryClassIdBetweenYears(1, 2020, 2021)));
	}

	@Test
	public void testMatchJson() {
//		MatchJson matchJson = matchController.getMatchJson(1239546).getBody();
//		MatchJson matchJson2 = matchController.getMatchJson(5545454).getBody();
//		Assert.assertNotNull(matchJson);
//		Assert.assertNull(matchJson2);
	}

}
