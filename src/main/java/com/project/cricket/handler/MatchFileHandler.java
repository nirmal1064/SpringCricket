package com.project.cricket.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
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
import com.project.cricket.model.MatchJson;
import com.project.cricket.task.MatchFileTask;
import com.project.cricket.utils.Constants;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<MatchJson> getMatchJson(List<Integer> matchIds) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		List<MatchJson> matchJsons = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		List<Future<T>> resultsFuture;
		try {
			List<MatchFileTask> matchFileTasks = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchFileTask matchFileTask = serviceFactory.matchFileTask();
				matchFileTask.init(matchId, Constants.JSON);
				matchFileTasks.add(matchFileTask);
			}
			resultsFuture = service.invokeAll((Collection<? extends Callable<T>>) matchFileTasks);
			addResults(stopWatch, matchJsons, service, resultsFuture);
			LOGGER.info("MatchJson completed for {} matches in {} seconds", matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return matchJsons;
	}

	private <T> void addResults(StopWatch stopWatch, List<MatchJson> matchJsons, ExecutorService service,
			List<Future<T>> resultsFuture) throws InterruptedException, ExecutionException {
		service.shutdown();
		for (Future<T> future : resultsFuture) {
			MatchJson json = (MatchJson) future.get();
			if (json != null) {
				matchJsons.add(json);
			}
		}
		stopWatch.stop();
	}

	@PostConstruct
	public void init() {
		service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
	}

}
