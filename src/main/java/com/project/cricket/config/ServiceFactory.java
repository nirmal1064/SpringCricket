package com.project.cricket.config;

import com.project.cricket.task.MatchOnlineTask;
import com.project.cricket.task.MatchTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {

	public ResultsScraperTask resultsScraperTask();

	public MatchOnlineTask matchOnlineTask();

	public MatchTask matchTask();

}
