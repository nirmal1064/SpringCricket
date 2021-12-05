package com.project.cricket.handler;

import java.util.ArrayList;
import java.util.List;
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
import com.project.cricket.utils.ExecutorUtil;

@Component
public class MatchHtmlScraperHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchHtmlScraperHandler.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ExecutorUtil executorUtil;

	@Autowired
	private ServiceFactory serviceFactory;

	public List<String> getMatchScorecard(List<Integer> matchIds, boolean writeToFile) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchHtmlScraperTask> matchHtmlScraperTasks = new ArrayList<>();
			List<Future<String>> resultsFuture = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchHtmlScraperTask matchHtmlScraperTask = serviceFactory.matchHtmlScraperTask();
				matchHtmlScraperTask.init(matchId, writeToFile);
				matchHtmlScraperTasks.add(matchHtmlScraperTask);
			}
			resultsFuture = service.invokeAll(matchHtmlScraperTasks);
			service.shutdown();
			for (Future<String> future : resultsFuture) {
				String json = future.get();
				result.add(json);
			}
			stopWatch.stop();
			LOGGER.info("MatchScorecard summary for {} matches completed in {} seconds", matchIds.size(),
					stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

}
