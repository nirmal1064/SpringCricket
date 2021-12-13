package com.project.cricket.config;

import com.project.cricket.task.MatchTask;
import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.task.MatchScorecardTask;
import com.project.cricket.task.MatchStringTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();

	public MatchStringTask matchStringTask();

	public MatchJsonTask matchJsonTask();

	public MatchScorecardTask matchScorecardTask();

	public MatchTask matchTask();
}
