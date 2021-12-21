package com.project.cricket.task;

import static com.project.cricket.utils.Constants.DATA1;
import static com.project.cricket.utils.Constants.ENGINE_TABLE;
import static com.project.cricket.utils.Constants.FCHASH;
import static com.project.cricket.utils.Constants.MATCH_URL;
import static com.project.cricket.utils.Constants.MJSON_URL;
import static com.project.cricket.utils.Constants.NORESULTS;
import static com.project.cricket.utils.Constants.ODIHASH;
import static com.project.cricket.utils.Constants.SUMMARY_LINK;
import static com.project.cricket.utils.Constants.T20IHASH;
import static com.project.cricket.utils.Constants.TD;
import static com.project.cricket.utils.Constants.TESTHASH;
import static com.project.cricket.utils.Constants.TR;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.project.cricket.entity.ResultSummary;
import com.project.cricket.exceptions.ResultNotFoundException;
import com.project.cricket.utils.CricUtils;

@Configuration
@Scope(value = SCOPE_PROTOTYPE)
public class ResultsScraperTask implements Callable<List<ResultSummary>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultsScraperTask.class);

	@Autowired
	private CricUtils cricUtils;

	private int classId;
	private int year;

	public void init(int classId, int year) {
		this.classId = classId;
		this.year = year;
	}

	@Override
	public List<ResultSummary> call() throws Exception {
		return scrapeWebsite();
	}

	private List<ResultSummary> scrapeWebsite() {
		List<ResultSummary> resultSummary = new ArrayList<>();
		String url = String.format(SUMMARY_LINK, classId, year);
		LOGGER.info("Requesting summary for {}", url);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			Elements engineTable = document.getElementsByClass(ENGINE_TABLE).get(0).getElementsByClass(DATA1);
			if (engineTable.isEmpty()) {
				throw new ResultNotFoundException(NORESULTS);
			}
			Elements rows = engineTable.select(TR);
			for (int i = 0; i < rows.size(); i++) {
				Element row = rows.get(i);
				Elements cols = row.select(TD);
				ResultSummary matchResult = getMatchResult(cols);
				if(matchResult != null) {
					resultSummary.add(matchResult);
				}
			}
			return resultSummary;
		} catch (Exception e) {
			LOGGER.error("Exception in getting results for class {} and year {}", classId, year, e);
			return resultSummary;
		}
	}

	private ResultSummary getMatchResult(Elements cols) {
		String team1 = cricUtils.cleanString(cols.get(0).text());
		String team2 = cricUtils.cleanString(cols.get(1).text());
		String winner = cricUtils.cleanString(cols.get(2).text());
		String margin = cricUtils.cleanString(cols.get(3).text());
		String ground = cricUtils.cleanString(cols.get(4).text());
		String matchDate = cricUtils.cleanString(cols.get(5).text());
		String scorecard = cricUtils.cleanString(cols.get(6).text());
		Integer matchId = Integer.valueOf(cricUtils.getIdFromElement(cols.get(6)));
		int groundId = Integer.parseInt(cricUtils.getIdFromElement(cols.get(4)));
		String matchUrl = String.format(MATCH_URL, matchId);
		String matchJsonUrl = String.format(MJSON_URL, matchId);
		int matchNumber = getMatchNumber(scorecard);

		if (verifyScorecard(scorecard)) {
			return null;
		}

		ResultSummary matchResult = new ResultSummary();
		matchResult.setMatchId(matchId);
		matchResult.setClassId(classId);
		matchResult.setTeam1(team1);
		matchResult.setTeam2(team2);
		matchResult.setWinner(winner);
		matchResult.setMargin(margin);
		matchResult.setGround(ground);
		matchResult.setGroundId(groundId);
		matchResult.setMatchDate(matchDate);
		matchResult.setScorecard(scorecard);
		matchResult.setYear(year);
		matchResult.setMatchUrl(matchUrl);
		matchResult.setMatchJsonUrl(matchJsonUrl);
		matchResult.setMatchNumber(matchNumber);
		return matchResult;
	}

	private boolean verifyScorecard(String scorecard) {
		return (this.classId == 4 || this.classId == 5 || this.classId == 6) && (scorecard.contains(TESTHASH) || scorecard.contains(ODIHASH) || scorecard.contains(T20IHASH));
	}

	private int getMatchNumber(String scorecard) {
		scorecard = scorecard.replace(TESTHASH, "");
		scorecard = scorecard.replace(ODIHASH, "");
		scorecard = scorecard.replace(T20IHASH, "");
		scorecard = scorecard.replace(FCHASH, "");
		scorecard = cricUtils.cleanString(scorecard);
		try {
			return Integer.parseInt(scorecard);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
