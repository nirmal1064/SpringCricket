package com.project.cricket.config;

import com.project.cricket.scraper.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();
}
