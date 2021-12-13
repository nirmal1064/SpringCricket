package com.project.cricket.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.entity.Match;
import com.project.cricket.model.MatchJson;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.task.MatchScorecardTask;
import com.project.cricket.task.MatchTask;
import com.project.cricket.utils.ExecutorUtil;

@Component
public class MatchFileHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchFileHandler.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ExecutorUtil executorUtil;

	@Autowired
	private ServiceFactory serviceFactory;

	private ExecutorService service;

	public List<MatchScorecard> getMatchScorecard(List<Integer> matchIds) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		List<MatchScorecard> matchScorecards = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		stopWatch.shortSummary();
		List<Future<MatchScorecard>> resultsFuture;
		try {
			List<MatchScorecardTask> matchScorecardTasks = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchScorecardTask matchScorecardTask = serviceFactory.matchScorecardTask();
				matchScorecardTask.init(matchId);
				matchScorecardTasks.add(matchScorecardTask);
			}
			resultsFuture = service.invokeAll(matchScorecardTasks);
			addResults(stopWatch, matchScorecards, resultsFuture);
			LOGGER.info("Match Scorecard completed for {} matches in {} seconds", matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return matchScorecards;
	}

	public List<Match> getMatches(List<Integer> matchIds) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		List<Match> matches = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		List<Future<Match>> resultsFuture;
		try {
			List<MatchTask> matchJsonTasks = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchTask matchTask = serviceFactory.matchTask();
				matchTask.init(matchId);
				matchJsonTasks.add(matchTask);
			}
			resultsFuture = service.invokeAll(matchJsonTasks);
			addResults(stopWatch, matches, resultsFuture);
			LOGGER.info("MatchJson completed for {} matches in {} seconds", matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return matches;
	}

	public List<MatchJson> getMatchJson(List<Integer> matchIds) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		List<MatchJson> matchJsons = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		List<Future<MatchJson>> resultsFuture;
		try {
			List<MatchJsonTask> matchJsonTasks = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchJsonTask matchJsonTask = serviceFactory.matchJsonTask();
				matchJsonTask.init(matchId);
				matchJsonTasks.add(matchJsonTask);
			}
			resultsFuture = service.invokeAll(matchJsonTasks);
			addResults(stopWatch, matchJsons, resultsFuture);
			LOGGER.info("MatchJson completed for {} matches in {} seconds", matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return matchJsons;
	}

	private <T> void addResults(StopWatch stopWatch, List<T> results, List<Future<T>> resultsFuture) throws InterruptedException, ExecutionException {
		for (Future<T> future : resultsFuture) {
			T json = future.get();
			if (json != null) {
				results.add(json);
			}
		}
		stopWatch.stop();
	}

	@PostConstruct
	public void init() {
		service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
	}

}
