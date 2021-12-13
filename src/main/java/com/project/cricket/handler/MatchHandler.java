package com.project.cricket.handler;

import static com.project.cricket.utils.Constants.HTML;
import static com.project.cricket.utils.Constants.JSON;
import static com.project.cricket.utils.Constants.JSON_SUMMARY_LOG;
import static com.project.cricket.utils.Constants.SCORECARD_SUMMARY_LOG;

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
import org.springframework.util.StringUtils;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.task.MatchStringTask;
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

	private ExecutorService service;

	public List<String> getMatchJson(List<Integer> matchIds, boolean writeToFile, boolean overWrite) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		return handleMatchTask(matchIds, writeToFile, overWrite, JSON, JSON_SUMMARY_LOG);
	}

	public List<String> getMatchScorecard(List<Integer> matchIds, boolean writeToFile, boolean overWrite) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		return handleMatchTask(matchIds, writeToFile, overWrite, HTML, SCORECARD_SUMMARY_LOG);
	}

	private List<String> handleMatchTask(List<Integer> matchIds, boolean writeToFile, 
			boolean overWrite, String type, String comments) {
		List<String> result = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		try {
			List<MatchStringTask> matchTasks = new ArrayList<>();
			List<Future<String>> resultsFuture;
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchStringTask matchTask = serviceFactory.matchStringTask();
				matchTask.init(matchId, writeToFile, overWrite, type, false);
				matchTasks.add(matchTask);
			}
			resultsFuture = service.invokeAll(matchTasks);
			addResults(stopWatch, result, resultsFuture);
			LOGGER.info(comments, matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	private void addResults(StopWatch stopWatch, List<String> result, List<Future<String>> resultsFuture) throws InterruptedException, ExecutionException {
		for (Future<String> future : resultsFuture) {
			String json = future.get();
			if (StringUtils.hasLength(json)) {
				result.add(json);
			}
		}
		stopWatch.stop();
	}

	@PostConstruct
	public void init() {
		service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
	}

}
