package com.project.cricket.scraper;

import static com.project.cricket.utils.Constants.MATCH_URL;
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

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MatchHtmlScraperTask implements Callable<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchHtmlScraperTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	private int matchId;
	private boolean writeToFile;

	public void init(int matchId, boolean writeToFile) {
		this.matchId = matchId;
		this.writeToFile = writeToFile;
	}

	@Override
	public String call() throws Exception {
		LOGGER.info("{} {}", matchId, writeToFile);
		scrapeWebsite();
		return null;
	}

	private void scrapeWebsite() {
		String url = String.format(MATCH_URL, matchId);
		LOGGER.info("scrapting html for {}", url);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			Element element = document.getElementById(NEXT_DATA);
			String scorecard = element.data();
			if (writeToFile) {
				String fileName = String.valueOf(matchId) + ".json";
				fileUtils.writeToFile(appConfig.getMatchScorecardFileLocation(), fileName, scorecard);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in extracting scorecard for match {}", matchId, e);
		}
	}

}
