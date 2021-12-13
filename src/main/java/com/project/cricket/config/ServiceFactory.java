package com.project.cricket.config;

import com.project.cricket.task.MatchAllTask;
import com.project.cricket.task.MatchJsonTask;
import com.project.cricket.task.MatchScorecardTask;
import com.project.cricket.task.MatchTask;
import com.project.cricket.task.ResultsScraperTask;

public interface ServiceFactory {
	public ResultsScraperTask resultsScraperTask();

	public MatchTask matchTask();

	public MatchJsonTask matchJsonTask();

	public MatchScorecardTask matchScorecardTask();

	public MatchAllTask matchAllTask();
}
