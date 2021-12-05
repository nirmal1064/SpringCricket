package com.project.cricket.config;

import com.project.cricket.handler.MatchJsonTask;
import com.project.cricket.scraper.MatchHtmlScraperTask;
import com.project.cricket.scraper.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();

	public MatchJsonTask matchJsonTask();

	public MatchHtmlScraperTask matchHtmlScraperTask();
}
