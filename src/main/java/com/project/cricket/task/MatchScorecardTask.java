package com.project.cricket.task;

import static com.project.cricket.utils.Constants.FIELDUMPIRE;
import static com.project.cricket.utils.Constants.LONG;
import static com.project.cricket.utils.Constants.MATCHREFEREE;
import static com.project.cricket.utils.Constants.RESERVEUMPIRE;
import static com.project.cricket.utils.Constants.SHORT;
import static com.project.cricket.utils.Constants.TVUMPIRE;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.entity.Batsman;
import com.project.cricket.entity.Bowler;
import com.project.cricket.entity.Fow;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.Partnership;
import com.project.cricket.entity.ReplacementPlayer;
import com.project.cricket.entity.ScorecardOfficial;
import com.project.cricket.entity.superclass.MatchPerson;
import com.project.cricket.model.DismissalFielder;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.model.PlayerOrTeam;
import com.project.cricket.model.Scorecard;
import com.project.cricket.model.ScorecardInnings;
import com.project.cricket.model.ScorecardMatch;
import com.project.cricket.model.SupportInfo;
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
			Match match = getMatchFromScorecard(matchScorecard);
			LOGGER.info("{}", match.getMatchId());
			return matchScorecard;
		} catch (Exception e) {
			LOGGER.error("Exception in match {}", matchId, e);
		}
		return null;
	}

	private Match getMatchFromScorecard(MatchScorecard matchScorecard) {
		Match match = new Match();
		match.setMatchId(matchScorecard.getMatchId());

		ScorecardMatch scorecardMatch = matchScorecard.getMatch();
		addMatchAndType(scorecardMatch.getUmpires(), match, FIELDUMPIRE);
		addMatchAndType(scorecardMatch.getTvUmpires(), match, TVUMPIRE);
		addMatchAndType(scorecardMatch.getReserveUmpires(), match, RESERVEUMPIRE);
		addMatchAndType(scorecardMatch.getMatchReferees(), match, MATCHREFEREE);
		addMatchAndId(scorecardMatch.getDebutPlayers(), match);
		parseReplacements(match, scorecardMatch.getReplacementPlayers());

		match.setDebuts(scorecardMatch.getDebutPlayers());
		match.setReplacement(scorecardMatch.getReplacementPlayers());

		SupportInfo supportInfo = matchScorecard.getSupportInfo();
		addMatchAndId(supportInfo.getPlayersOfTheMatch(), match);
		addMatchAndId(supportInfo.getPlayersOfTheSeries(), match);
		match.setPlayersOfTheMatch(supportInfo.getPlayersOfTheMatch());
		match.setPlayersOfTheSeries(supportInfo.getPlayersOfTheSeries());

		parseInnings(match, matchScorecard.getScorecard());

		return match;
	}

	private void parseInnings(Match match, Scorecard scorecard) {
		if (scorecard != null) {
			List<ScorecardInnings> innings = scorecard.getInnings();
			if (!CollectionUtils.isEmpty(innings)) {
				for (ScorecardInnings inning : innings) {
					processBatsmen(match, inning, inning.getInningBatsmen());
					processBowlers(match, inning, inning.getInningBowlers());
					processPartnership(match, inning, inning.getInningPartnerships());
					processWickets(match, inning, inning.getInningWickets());

					match.getBatsmen().addAll(inning.getInningBatsmen());
					match.getBowlers().addAll(inning.getInningBowlers());
					match.getPartnerships().addAll(inning.getInningPartnerships());
					match.getFows().addAll(inning.getInningWickets());
				}
			}
		}
	}

	private void parseReplacements(Match match, List<ReplacementPlayer> replacementPlayers) {
		if (!CollectionUtils.isEmpty(replacementPlayers)) {
			replacementPlayers.forEach(e -> {
				e.setMatch(match);
				e.setObjectId(e.getPlayer().getObjectId());
				e.setTeamId(e.getTeam().getObjectId());
				e.setReplacingPlayerId(e.getReplacingPlayer().getObjectId());
			});
		}
	}

	private void addMatchAndType(List<ScorecardOfficial> umps, Match match, String type) {
		if (!CollectionUtils.isEmpty(umps)) {
			umps.forEach(e -> {
				e.setMatch(match);
				e.setType(type);
				e.setObjectId(e.getPlayer().getObjectId());
			});
			match.getOfficials().addAll(umps);
		}
	}

	private void addMatchAndId(List<? extends MatchPerson> persons, Match match) {
		if (!CollectionUtils.isEmpty(persons)) {
			persons.forEach(person -> {
				person.setMatch(match);
				person.setObjectId(person.getPlayer().getObjectId());
			});
		}
	}

	private void processBatsmen(Match match, ScorecardInnings inning, List<Batsman> inningBatsmen) {
		if (!CollectionUtils.isEmpty(inningBatsmen)) {
			for (int i = 0; i < inningBatsmen.size(); i++) {
				Batsman batsman = inningBatsmen.get(i);
				batsman.setMatch(match);
				batsman.setInnings(inning.getInningNumber());
				batsman.setBatsmanId(batsman.getPlayer().getObjectId());
				batsman.setPosition(i);
			}
		}
	}

	private void processBowlers(Match match, ScorecardInnings inning, List<Bowler> inningBowlers) {
		if (!CollectionUtils.isEmpty(inningBowlers)) {
			for (int i = 0; i < inningBowlers.size(); i++) {
				Bowler bowler = inningBowlers.get(i);
				bowler.setMatch(match);
				bowler.setInnings(inning.getInningNumber());
				bowler.setBowlerId(bowler.getPlayer().getObjectId());
				bowler.setPosition(i);
			}
		}
	}

	private void processPartnership(Match match, ScorecardInnings inning, List<Partnership> inningPartnerships) {
		if (!CollectionUtils.isEmpty(inningPartnerships)) {
			for (int i = 0; i < inningPartnerships.size(); i++) {
				Partnership partnership = inningPartnerships.get(i);
				partnership.setMatch(match);
				partnership.setInnings(inning.getInningNumber());
				partnership.setPlayer1Id(partnership.getPlayer1().getObjectId());
				partnership.setPlayer2Id(partnership.getPlayer2().getObjectId());
				partnership.setWicketNumber(i);
			}
		}
	}

	private void processWickets(Match match, ScorecardInnings inning, List<Fow> inningWickets) {
		if (!CollectionUtils.isEmpty(inningWickets)) {
			inningWickets.forEach(e -> {
				e.setMatch(match);
				e.setInnings(inning.getInningNumber());
				if(e.getDismissalBatsman() != null) {
					e.setBatsmanId(e.getDismissalBatsman().getObjectId());
				}
				if(e.getDismissalBowler() != null) {
					e.setBowlerId(e.getDismissalBowler().getObjectId());
				}
				parseDismissalFielders(e, e.getDismissalFielders());
				parseDismissalText(e, e.getDismissalText());
			});
		}
	}

	private void parseDismissalText(Fow e, Map<String, String> dismissalText) {
		if (dismissalText != null) {
			if (dismissalText.containsKey(SHORT)) {
				e.setDismissalTextShort(dismissalText.get(SHORT));
			}
			if (dismissalText.containsKey(LONG)) {
				e.setDismissalTextLong(dismissalText.get(LONG));
			}
		}
	}

	private void parseDismissalFielders(Fow e, List<DismissalFielder> dismissalFielders) {
		if (!CollectionUtils.isEmpty(dismissalFielders)) {
			e.setIsKeeper(0);
			e.setIsSubstitute(0);
			for(int i = 0; i < dismissalFielders.size(); i++) {
				DismissalFielder dismissalFielder = dismissalFielders.get(i);
				PlayerOrTeam player = dismissalFielder.getPlayer();
				if(dismissalFielder.getIsKeeper() == 1) {
					e.setIsKeeper(1);
				}
				if(dismissalFielder.getIsSubstitute() == 1) {
					e.setIsSubstitute(1);
				}
				if(i == 0) {
					e.setFielder1Id(player != null ? player.getObjectId() : null);
				} else if (i == 1) {
					e.setFielder2Id(player != null ? player.getObjectId() : null);
				} else if (i == 2) {
					e.setFielder3Id(player != null ? player.getObjectId() : null);
				} else if (i == 3) {
					e.setFielder4Id(player != null ? player.getObjectId() : null);
				}
			}
		}
	}

}
