package com.project.cricket.task;

import static com.project.cricket.utils.Constants.HTML;
import static com.project.cricket.utils.Constants.JSON;
import static com.project.cricket.utils.Constants.MATCH_URL;
import static com.project.cricket.utils.Constants.MJSON_URL;
import static com.project.cricket.utils.Constants.NEXT_DATA;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MatchOnlineTask implements Callable<Integer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchOnlineTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private RestTemplate restTemplate;

	private int matchId;
	private String task;

	public void init(int matchId, String task) {
		this.matchId = matchId;
		this.task = task;
	}

	@Override
	public Integer call() throws Exception {
		if (task.equalsIgnoreCase(JSON)) {
			return matchJson();
		} else if (task.equalsIgnoreCase(HTML)) {
			return scrapeWebsite();
		}
		return null;
	}

	private Integer matchJson() {
		String matchJson = "";
		try {
			String fileName = String.valueOf(matchId) + ".json";
			matchJson = getMatchJson(matchId);
			boolean flag = fileUtils.writeToFile(appConfig.getMatchJsonFileLocation(), fileName, matchJson);
			if (!flag) {
				return matchId;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in json task for match {}", matchId, e);
		}
		return null;
	}

	private Integer scrapeWebsite() {
		String fileName = String.valueOf(matchId) + ".json";
		String url = String.format(MATCH_URL, matchId);
		LOGGER.info("scrapting html for {}", url);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			Element element = document.getElementById(NEXT_DATA);
			String scorecard = element.data();
			boolean flag = fileUtils.writeToFile(appConfig.getMatchScorecardFileLocation(), fileName, StringUtils.trimWhitespace(scorecard));
			if (!flag) {
				return matchId;
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
		if (StringUtils.hasText(response)) {
			return StringUtils.trimWhitespace(response);
		}
		return response;
	}
}
