package com.project.cricket.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.task.MatchHtmlScraperTask;
import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.utils.ExecutorUtil;

@Component
public class MatchHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchHandler.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ExecutorUtil executorUtil;

	@Autowired
	private ServiceFactory serviceFactory;

	public List<String> getMatchJson(List<Integer> matchIds, boolean writeToFile) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchJsonTask> matchJsonTasks = new ArrayList<>();
			List<Future<String>> resultsFuture;
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchJsonTask matchJsonTask = serviceFactory.matchJsonTask();
				matchJsonTask.init(matchId, writeToFile);
				matchJsonTasks.add(matchJsonTask);
			}
			resultsFuture = service.invokeAll(matchJsonTasks);
			addResults(stopWatch, result, service, resultsFuture);
			LOGGER.info("MatchJson summary for {} matches completed in {} seconds", matchIds.size(),
					stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	private void addResults(StopWatch stopWatch, List<String> result, ExecutorService service,
			List<Future<String>> resultsFuture) throws InterruptedException, ExecutionException {
		service.shutdown();
		for (Future<String> future : resultsFuture) {
			String json = future.get();
			result.add(json);
		}
		stopWatch.stop();
	}

	public List<String> getMatchScorecard(List<Integer> matchIds, boolean writeToFile) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchHtmlScraperTask> matchHtmlScraperTasks = new ArrayList<>();
			List<Future<String>> resultsFuture;
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchHtmlScraperTask matchHtmlScraperTask = serviceFactory.matchHtmlScraperTask();
				matchHtmlScraperTask.init(matchId, writeToFile);
				matchHtmlScraperTasks.add(matchHtmlScraperTask);
			}
			resultsFuture = service.invokeAll(matchHtmlScraperTasks);
			addResults(stopWatch, result, service, resultsFuture);
			LOGGER.info("MatchScorecard summary for {} matches completed in {} seconds", matchIds.size(),
					stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

}
