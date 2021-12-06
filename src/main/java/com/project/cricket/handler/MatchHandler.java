package com.project.cricket.handler;

import static com.project.cricket.utils.Constants.HTML;
import static com.project.cricket.utils.Constants.JSON;

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
import org.springframework.util.StringUtils;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.task.MatchTask;
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

	public List<String> getMatchJson(List<Integer> matchIds, boolean writeToFile, boolean overWrite) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchTask> matchTasks = new ArrayList<>();
			List<Future<String>> resultsFuture;
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchTask matchTask = serviceFactory.matchTask();
				matchTask.init(matchId, writeToFile, overWrite, JSON, false);
				matchTasks.add(matchTask);
			}
			resultsFuture = service.invokeAll(matchTasks);
			addResults(stopWatch, result, service, resultsFuture);
			LOGGER.info("MatchJson summary for {} matches completed in {} seconds ", matchIds.size(),
					stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	public List<String> getMatchScorecard(List<Integer> matchIds, boolean writeToFile, boolean overWrite) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchTask> matchTasks = new ArrayList<>();
			List<Future<String>> resultsFuture;
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchTask matchTask = serviceFactory.matchTask();
				matchTask.init(matchId, writeToFile, overWrite, HTML, false);
				matchTasks.add(matchTask);
			}
			resultsFuture = service.invokeAll(matchTasks);
			addResults(stopWatch, result, service, resultsFuture);
			LOGGER.info("MatchScorecard summary for {} matches completed in {} seconds ", matchIds.size(),
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
			if (StringUtils.hasLength(json)) {
				result.add(json);
			}
		}
		stopWatch.stop();
	}

}
