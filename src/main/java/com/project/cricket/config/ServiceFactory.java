package com.project.cricket.config;

import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.task.MatchTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();

	public MatchTask matchTask();

	public MatchJsonTask matchJsonTask();
}
