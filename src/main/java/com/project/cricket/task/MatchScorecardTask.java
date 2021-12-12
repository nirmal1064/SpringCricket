package com.project.cricket.task;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(scopeName = SCOPE_PROTOTYPE)
public class MatchScorecardTask implements Callable<MatchScorecard> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchScorecardTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private Gson gson;

	private int matchId;

	public void init(int matchId) {
		this.matchId = matchId;
	}

	@Override
	public MatchScorecard call() throws Exception {
		String matchJsonStr = "";
		try {
			String fileName = String.valueOf(matchId) + ".json";
			matchJsonStr = fileUtils.readFile(appConfig.getMatchScorecardFileLocation(), fileName);
			MatchScorecard matchScorecard = gson.fromJson(matchJsonStr, MatchScorecard.class);
			matchScorecard.setMatchId(matchId);
			return matchScorecard;
		} catch (Exception e) {
			LOGGER.error("Exception in match {}", matchId, e);
		}
		return null;
	}

}
