package com.project.cricket.service;

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
import com.project.cricket.task.MatchTask;
import com.project.cricket.utils.ExecutorUtil;

@Component
public class MatchFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchFileService.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ExecutorUtil executorUtil;

	@Autowired
	private ServiceFactory serviceFactory;

	private ExecutorService service;

	public List<Match> getMatches(List<Integer> matchIds) {
		LOGGER.info("Match summary for {} matches", matchIds.size());
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
			LOGGER.info("MatchTask completed for {} matches in {} seconds", matchIds.size(), stopWatch.getTotalTimeSeconds());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return matches;
	}

	private void addResults(StopWatch stopWatch, List<Match> results, List<Future<Match>> resultsFuture) throws InterruptedException, ExecutionException {
		for (Future<Match> future : resultsFuture) {
			Match json = future.get();
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
