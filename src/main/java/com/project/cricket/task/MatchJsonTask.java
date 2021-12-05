package com.project.cricket.task;

import static com.project.cricket.utils.Constants.MJSON_URL;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.utils.FileOperationUtils;

@Configuration
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MatchJsonTask implements Callable<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchJsonTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private RestTemplate restTemplate;

	private int matchId;
	private boolean writeToFile;

	public void init(int matchId, boolean writeToFile) {
		this.matchId = matchId;
		this.writeToFile = writeToFile;
	}

	@Override
	public String call() throws Exception {
		String matchJson = "";
		try {
			matchJson = getMatchJson(matchId);
			if (writeToFile) {
				String fileName = String.valueOf(matchId) + ".json";
				fileUtils.writeToFile(appConfig.getMatchJsonFileLocation(), fileName, matchJson);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in json task for match {}", matchId, e);
		}
		return matchJson;
	}

	private String getMatchJson(int matchId) {
		String response = "";
		try {
			String url = String.format(MJSON_URL, matchId);
			response = restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return response;
	}

}
