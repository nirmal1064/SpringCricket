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

	public List<String> getMatchJson(List<Integer> matchIds, boolean WriteToFile) {
		LOGGER.info("MatchJson summary for {} matches", matchIds.size());
		StopWatch stopWatch = new StopWatch();
		List<String> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			List<MatchJsonTask> matchJsonTasks = new ArrayList<>();
			List<Future<String>> resultsFuture = new ArrayList<>();
			stopWatch.start();
			for (Integer matchId : matchIds) {
				MatchJsonTask matchJsonTask = serviceFactory.matchJsonTask();
				matchJsonTask.init(matchId, WriteToFile);
				matchJsonTasks.add(matchJsonTask);
			}
			resultsFuture = service.invokeAll(matchJsonTasks);
			service.shutdown();
			for (Future<String> future : resultsFuture) {
				String json = future.get();
				result.add(json);
			}
			stopWatch.stop();
			LOGGER.info("MatchJson summary for {} matches completed in {} seconds", matchIds.size(),
					stopWatch.getTotalTimeSeconds());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
}
