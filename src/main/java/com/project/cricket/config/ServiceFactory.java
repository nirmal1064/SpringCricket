package com.project.cricket.config;

import com.project.cricket.task.MatchHtmlScraperTask;
import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();

	public MatchJsonTask matchJsonTask();

	public MatchHtmlScraperTask matchHtmlScraperTask();
}
