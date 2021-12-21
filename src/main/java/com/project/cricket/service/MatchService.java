package com.project.cricket.service;

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

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.task.MatchStringTask;
import com.project.cricket.utils.ExecutorUtil;

@Component
public class MatchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchService.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ExecutorUtil executorUtil;

	@Autowired
	private ServiceFactory serviceFactory;

	private ExecutorService service;

	public List<Integer> getMatchJson(List<Integer> matchIds) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		return handleMatchTask(matchIds, JSON, JSON_SUMMARY_LOG);
	}

	public List<Integer> getMatchScorecard(List<Integer> matchIds) {
		LOGGER.info("Match Scorecard summary for {} matches", matchIds.size());
		return handleMatchTask(matchIds, HTML, SCORECARD_SUMMARY_LOG);
	}

	private List<Integer> handleMatchTask(List<Integer> matchIds, String type, String comments) {
		List<Integer> result = new ArrayList<>();
		StopWatch stopWatch = new StopWatch();
		try {
			List<MatchStringTask> matchTasks = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchStringTask matchTask = serviceFactory.matchStringTask();
				matchTask.init(matchId, type);
				matchTasks.add(matchTask);
			}
			List<Future<Integer>> resultsFuture = service.invokeAll(matchTasks);
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

	private void addResults(StopWatch stopWatch, List<Integer> result, List<Future<Integer>> resultsFuture) throws InterruptedException, ExecutionException {
		for (Future<Integer> future : resultsFuture) {
			Integer id = future.get();
			if (id != null) {
				result.add(id);
			}
		}
		stopWatch.stop();
	}

	@PostConstruct
	public void init() {
		service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
	}

}
