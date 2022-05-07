package com.project.cricket.task;

import static com.project.cricket.utils.Constants.DATA1;
import static com.project.cricket.utils.Constants.ENGINE_TABLE;
import static com.project.cricket.utils.Constants.FC;
import static com.project.cricket.utils.Constants.FCHASH;
import static com.project.cricket.utils.Constants.ICCTHASH;
import static com.project.cricket.utils.Constants.LA;
import static com.project.cricket.utils.Constants.MATCH_URL;
import static com.project.cricket.utils.Constants.MJSON_URL;
import static com.project.cricket.utils.Constants.NORESULTS;
import static com.project.cricket.utils.Constants.ODIHASH;
import static com.project.cricket.utils.Constants.SUMMARY_LINK;
import static com.project.cricket.utils.Constants.T20;
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
import org.springframework.util.StringUtils;

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
			document = Jsoup.connect(url).timeout(60000).get();
			Elements engineTable = document.getElementsByClass(ENGINE_TABLE).get(0).getElementsByClass(DATA1);
			if (engineTable.isEmpty()) {
				throw new ResultNotFoundException(NORESULTS);
			}
			Elements rows = engineTable.select(TR);
			for (int i = 0; i < rows.size(); i++) {
				Element row = rows.get(i);
				Elements cols = row.select(TD);
				ResultSummary matchResult = getMatchResult(cols);
				resultSummary.add(matchResult);
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

		ResultSummary matchResult = new ResultSummary();
		matchResult.setMatchId(matchId);
		matchResult.setClassId(getClassId(scorecard));
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

	private int getClassId(String scorecard) {
		if (scorecard.contains(TESTHASH)) {
			return 1;
		} else if (scorecard.contains(ODIHASH)) {
			return 2;
		} else if (scorecard.contains(T20IHASH)) {
			return 3;
		} else if (scorecard.contains(FC) || scorecard.contains(FCHASH)) {
			return 4;
		} else if (scorecard.contains(ICCTHASH) || scorecard.contains(LA)) {
			return 5;
		} else if (scorecard.contains(T20)) {
			return 6;
		}
		return classId;
	}

	private int getMatchNumber(String scorecard) {
		scorecard = scorecard.replace(TESTHASH, "");
		scorecard = scorecard.replace(ODIHASH, "");
		scorecard = scorecard.replace(T20IHASH, "");
		scorecard = scorecard.replace(FCHASH, "");
		scorecard = StringUtils.trimWhitespace(scorecard);
		try {
			return Integer.parseInt(scorecard);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
