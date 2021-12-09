package com.project.cricket.task;

import static com.project.cricket.utils.Constants.HTML;
import static com.project.cricket.utils.Constants.JSON;

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
public class MatchFileTask<T> implements Callable<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchFileTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private Gson gson;

	private int matchId;
	private String task;

	public void init(int matchId, String task) {
		this.matchId = matchId;
		this.task = task;
	}

	@Override
	public T call() throws Exception {
		if (task.equalsIgnoreCase(JSON)) {
			return getMatchJson();
		} else if (task.equalsIgnoreCase(HTML)) {
			return getMatchScorecard();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private T getMatchJson() {
		String matchJsonStr = "";
		try {
			String fileName = String.valueOf(matchId) + ".json";
			matchJsonStr = fileUtils.readFile(appConfig.getMatchJsonFileLocation(), fileName);
			MatchJson matchJson = gson.fromJson(matchJsonStr, MatchJson.class);
			matchJson.setMatchId(matchId);
			return (T) matchJson;
		} catch (Exception e) {
			LOGGER.error("Exception in match {}", matchId, e);
		}
		return null;
	}

	private T getMatchScorecard() {
		return null;
	}

}
