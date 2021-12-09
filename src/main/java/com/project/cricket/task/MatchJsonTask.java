package com.project.cricket.task;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.model.MatchJson;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MatchJsonTask implements Callable<MatchJson> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchJsonTask.class);

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
	public MatchJson call() throws Exception {
		String matchJsonStr = "";
		try {
			String fileName = String.valueOf(matchId) + ".json";
			matchJsonStr = fileUtils.readFile(appConfig.getMatchJsonFileLocation(), fileName);
			MatchJson matchJson = gson.fromJson(matchJsonStr, MatchJson.class);
			matchJson.setMatchId(matchId);
			return matchJson;
		} catch (Exception e) {
			LOGGER.error("Exception in match {}", matchId, e);
		}
		return null;
	}

}
