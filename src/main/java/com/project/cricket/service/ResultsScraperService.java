package com.project.cricket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.config.ServiceFactory;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.task.ResultsScraperTask;
import com.project.cricket.utils.ExecutorUtil;

@Component
public class ResultsScraperService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsScraperService.class);

	@Autowired
	private ServiceFactory serviceFactory;

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private DbService dbService;

	@Autowired
	private ExecutorUtil executorUtil;

	private ExecutorService service;

	public List<ResultSummary> getSummary(int classId, int startYear, int endYear, boolean saveToDb) {
		LOGGER.info("Getting results for class: {} between {} and {}", classId, startYear, endYear);
		return summaryBetweenYears(classId, startYear, endYear, saveToDb);
	}

	public List<ResultSummary> postSummary(int classId, int startYear, int endYear, boolean saveToDb) {
		LOGGER.info("Posting results for class: {} between {} and {}", classId, startYear, endYear);
		return summaryBetweenYears(classId, startYear, endYear, saveToDb);
	}

	private List<ResultSummary> summaryBetweenYears(int classId, int startYear, int endYear, boolean saveToDb) {
		StopWatch stopWatch = new StopWatch();
		List<ResultSummary> result = new ArrayList<>();
		List<Future<List<ResultSummary>>> resultsFuture = new ArrayList<>();
		List<ResultsScraperTask> resultsScraperTasks = new ArrayList<>();
		int sum = 0;
		try {
			stopWatch.start();
			for (int year = startYear; year <= endYear; year++) {
				ResultsScraperTask resultsScraperTask = serviceFactory.resultsScraperTask();
				resultsScraperTask.init(classId, year);
				resultsScraperTasks.add(resultsScraperTask);
			}
			resultsFuture = service.invokeAll(resultsScraperTasks);
			for (Future<List<ResultSummary>> future : resultsFuture) {
				List<ResultSummary> summary = future.get();
				if (!CollectionUtils.isEmpty(summary)) {
					sum = sum + summary.size();
					if (saveToDb) {
						int recordsCount = dbService.saveResultsSummaryToDb(summary);
						LOGGER.info("{}/{} results summary stored in db for class {} and year {}", recordsCount, summary.size(), classId, summary.get(0).getYear());
					}
				}
				result.addAll(summary);
			}
			stopWatch.stop();
		} catch (InterruptedException e) {
			LOGGER.warn("Interrupted Exception ", e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			LOGGER.info("Exception ", e);
		}
		LOGGER.info("Sum {} and result size {}", sum, result.size());
		LOGGER.info("Total results for class: {} between {} and {} is {} and time taken is {} seconds with {} threads", 
				classId, startYear, endYear, result.size(), stopWatch.getTotalTimeSeconds(), appConfig.getNumOfThreads());
		return result;
	}

	@PostConstruct
	public void init() {
		service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
	}

}
