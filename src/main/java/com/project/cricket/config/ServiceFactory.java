package com.project.cricket.config;

import com.project.cricket.task.MatchStringTask;
import com.project.cricket.task.MatchTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {

	public ResultsScraperTask resultsScraperTask();

	public MatchStringTask matchStringTask();

	public MatchTask matchTask();

}
