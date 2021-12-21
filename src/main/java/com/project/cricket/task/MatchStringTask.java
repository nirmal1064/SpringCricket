package com.project.cricket.task;

import static com.project.cricket.utils.Constants.HTML;
import static com.project.cricket.utils.Constants.JSON;
import static com.project.cricket.utils.Constants.MATCH_URL;
import static com.project.cricket.utils.Constants.MJSON_URL;
import static com.project.cricket.utils.Constants.NEXT_DATA;

import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MatchStringTask implements Callable<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchStringTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private RestTemplate restTemplate;

	private int matchId;
	private boolean writeToFile;
	private boolean overWrite;
	private String task;
	private boolean fromFile;

	public void init(int matchId, boolean writeToFile, boolean overWrite, String task, boolean fromFile) {
		this.matchId = matchId;
		this.writeToFile = writeToFile;
		this.overWrite = overWrite;
		this.task = task;
		this.fromFile = fromFile;
	}

	@Override
	public String call() throws Exception {
		if (task.equalsIgnoreCase(JSON)) {
			return matchJson();
		} else if (task.equalsIgnoreCase(HTML)) {
			return scrapeWebsite();
		}
		return null;
	}

	private String matchJson() {
		String matchJson = "";
		try {
			String fileName = String.valueOf(matchId) + ".json";
			if (fromFile) {
				matchJson = fileUtils.readFile(appConfig.getMatchJsonFileLocation(), fileName);
				return matchJson;
			} else {
				matchJson = getMatchJson(matchId);
				if (writeToFile) {
					boolean flag = fileUtils.writeToFile(appConfig.getMatchJsonFileLocation(), fileName, matchJson, overWrite);
					if (!flag) {
						return String.valueOf(matchId);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in json task for match {}", matchId, e);
		}
		return null;
	}

	private String scrapeWebsite() {
		String fileName = String.valueOf(matchId) + ".json";
		if (fromFile) {
			return fileUtils.readFile(appConfig.getMatchScorecardFileLocation(), fileName);
		}
		String url = String.format(MATCH_URL, matchId);
		LOGGER.info("scrapting html for {}", url);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			Element element = document.getElementById(NEXT_DATA);
			String scorecard = element.data();
			if (writeToFile) {
				boolean flag = fileUtils.writeToFile(appConfig.getMatchScorecardFileLocation(), fileName, StringUtils.trimWhitespace(scorecard), overWrite);
				if (!flag) {
					return String.valueOf(matchId);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception in extracting scorecard for match {}", matchId, e);
		}
		return null;
	}

	private String getMatchJson(int matchId) {
		String response = "";
		String url = String.format(MJSON_URL, matchId);
		response = restTemplate.getForObject(url, String.class);
		return StringUtils.trimWhitespace(response);
	}
}
