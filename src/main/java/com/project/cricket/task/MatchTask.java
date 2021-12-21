package com.project.cricket.task;

import static com.project.cricket.utils.Constants.DNB;
import static com.project.cricket.utils.Constants.LONG;
import static com.project.cricket.utils.Constants.SHORT;
import static com.project.cricket.utils.Constants.SUB;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

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
import com.project.cricket.entity.Debut;
import com.project.cricket.entity.Fow;
import com.project.cricket.entity.Innings;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.Official;
import com.project.cricket.entity.Partnership;
import com.project.cricket.entity.Player;
import com.project.cricket.entity.ReplacementPlayer;
import com.project.cricket.entity.Series;
import com.project.cricket.entity.superclass.MatchPerson;
import com.project.cricket.model.DismissalFielder;
import com.project.cricket.model.MatchJson;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.model.PlayerOrTeam;
import com.project.cricket.model.Scorecard;
import com.project.cricket.model.ScorecardInnings;
import com.project.cricket.model.ScorecardMatch;
import com.project.cricket.model.SupportInfo;
import com.project.cricket.model.Team;
import com.project.cricket.utils.FileOperationUtils;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MatchTask implements Callable<Match> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MatchTask.class);

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private FileOperationUtils fileUtils;

	@Autowired
	private Gson gson;

	private int matchId;

	private Match match;

	public void init(int matchId) {
		this.matchId = matchId;
	}

	@Override
	public Match call() throws Exception {
		try {
			LOGGER.info("Match Task Started for {}", matchId);
			MatchJson matchJson = getMatchJson();
			MatchScorecard matchScorecard = getMatchScorecard();
			match = matchJson.getMatch();
			parseMatchJson(matchJson);
			parseMatchScorecard(matchScorecard);
			LOGGER.info("Match Task Completed for {}", matchId);
			return match;
		} catch (Exception e) {
			LOGGER.error("Exception in match {}", matchId, e);
			return null;
		}
	}

	private MatchJson getMatchJson() {
		String matchJsonStr = "";
		String fileName = String.valueOf(matchId) + ".json";
		matchJsonStr = fileUtils.readFile(appConfig.getMatchJsonFileLocation(), fileName);
		MatchJson matchJson = gson.fromJson(matchJsonStr, MatchJson.class);
		matchJson.setMatchId(matchId);
		return matchJson;
	}

	public MatchScorecard getMatchScorecard() {
		String matchJsonStr = "";
		String fileName = String.valueOf(matchId) + ".json";
		matchJsonStr = fileUtils.readFile(appConfig.getMatchScorecardFileLocation(), fileName);
		MatchScorecard matchScorecard = gson.fromJson(matchJsonStr, MatchScorecard.class);
		matchScorecard.setMatchId(matchId);
		return matchScorecard;
	}

	private void parseMatchJson(MatchJson matchJson) {

		match.setMatchId(matchJson.getMatchId());

		List<Innings> innings = matchJson.getInnings();
		if (checkIfInningsZero(innings)) {
			innings = null;
		}
		if (!CollectionUtils.isEmpty(innings)) {
			innings.forEach(e -> e.setMatch(matchJson.getMatch()));
		} else {
			LOGGER.info("Innings is Empty for {}", matchId);
		}

		List<Player> players = new ArrayList<>();
		List<Team> team = matchJson.getTeam();
		if (!CollectionUtils.isEmpty(team)) {
			team.forEach(tm -> tm.getPlayer().forEach(p -> p.setTeamId(tm.getTeamId())));
			players = team.stream()
					.map(Team::getPlayer)
					.flatMap(List::stream)
					.collect(Collectors.toList());
			players.addAll(matchJson.getSubstitute());
			players.forEach(p -> p.setMatch(match));
		} else {
			LOGGER.info("Team is Empty for {}", matchId);
		}

		List<Series> series = matchJson.getSeries();
		if (!CollectionUtils.isEmpty(series)) {
			series.forEach(s -> s.setMatch(match));
		} else {
			LOGGER.info("Series is Empty for {}", matchId);
		}

		List<Official> official = matchJson.getOfficial();
		if (!CollectionUtils.isEmpty(official)) {
			official.forEach(o -> o.setMatch(match));
		} else {
			LOGGER.info("Official is Empty for {}", matchId);
		}

		match.setInnings(innings);
		match.setPlayer(players);
		match.setSeries(series);
		match.setOfficial(official);
	}

	private boolean checkIfInningsZero(List<Innings> innings) {
		for (Innings inning : innings) {
			if (inning.getInningsNumber() == 0) {
				LOGGER.info("Innings number zero for {}", match.getMatchId());
				return true;
			}
		}
		return false;
	}

	private void parseMatchScorecard(MatchScorecard matchScorecard) {

		ScorecardMatch scorecardMatch = matchScorecard.getMatch();
		addMatchAndIdToDebuts(scorecardMatch.getDebutPlayers());
		parseReplacements(scorecardMatch.getReplacementPlayers());

		SupportInfo supportInfo = matchScorecard.getSupportInfo();
		addMatchAndId(supportInfo.getPlayersOfTheMatch());
		addMatchAndId(supportInfo.getPlayersOfTheSeries());

		if (!CollectionUtils.isEmpty(supportInfo.getPlayersOfTheMatch())) {
			match.setPlayersOfTheMatch(removeDuplicates(supportInfo.getPlayersOfTheMatch()));
		}
		if (!CollectionUtils.isEmpty(supportInfo.getPlayersOfTheSeries())) {
			match.setPlayersOfTheSeries(removeDuplicates(supportInfo.getPlayersOfTheSeries()));
		}
		parseInnings(matchScorecard.getScorecard());
	}

	private <T> List<T> removeDuplicates(List<T> items) {
		Set<T> set = new LinkedHashSet<>();
		set.addAll(items);
		items.clear();
		items.addAll(set);
		return items;
	}

	private void parseInnings(Scorecard scorecard) {
		if (scorecard != null) {
			List<ScorecardInnings> innings = scorecard.getInnings();
			if (!CollectionUtils.isEmpty(innings)) {
				for (ScorecardInnings inning : innings) {
					processBatsmen(inning, inning.getInningBatsmen());
					processBowlers(inning, inning.getInningBowlers());
					processPartnership(inning, inning.getInningPartnerships());
					processWickets(inning, inning.getInningWickets());

					match.getBatsmen().addAll(inning.getInningBatsmen());
					match.getBowlers().addAll(inning.getInningBowlers());
					match.getPartnerships().addAll(inning.getInningPartnerships());
					match.getFows().addAll(inning.getInningWickets());
				}
			} else {
				LOGGER.info("Scorecard innings is Empty for {}", matchId);
			}
		} else {
			LOGGER.info("Scorecard is Empty for {}", matchId);
		}
	}

	private void parseReplacements(List<ReplacementPlayer> replacementPlayers) {
		if (!CollectionUtils.isEmpty(replacementPlayers)) {
			replacementPlayers.forEach(e -> {
				e.setMatch(match);
				e.setObjectId(e.getPlayer().getObjectId());
				e.setTeamId(e.getTeam().getObjectId());
				e.setReplacingPlayerId(e.getReplacingPlayer().getObjectId());
			});
			match.setReplacement(replacementPlayers);
		}
	}

	private void addMatchAndId(List<? extends MatchPerson> persons) {
		if (!CollectionUtils.isEmpty(persons)) {
			persons.forEach(person -> {
				person.setMatch(match);
				person.setObjectId(person.getPlayer().getObjectId());
			});
		}
	}

	private void addMatchAndIdToDebuts(List<Debut> debuts) {
		if (!CollectionUtils.isEmpty(debuts)) {
			debuts.forEach(person -> {
				person.setMatch(match);
				person.setObjectId(person.getPlayer().getObjectId());
			});
			match.setDebuts(debuts);
		}
	}

	private void processBatsmen(ScorecardInnings inning, List<Batsman> inningBatsmen) {
		if (!CollectionUtils.isEmpty(inningBatsmen)) {
			int position = 1;
			for (int i = 0; i < inningBatsmen.size(); i++) {
				Batsman batsman = inningBatsmen.get(i);
				batsman.setMatch(match);
				batsman.setInnings(inning.getInningNumber());
				batsman.setBatsmanId(batsman.getPlayer().getObjectId());
				if (!(batsman.getBattedType().equalsIgnoreCase(SUB) || batsman.getBattedType().equalsIgnoreCase(DNB))) {
					batsman.setPosition(position++);
				}
			}
		} else {
			LOGGER.info("SCorecard innings batsmen is Empty for {}", matchId);
		}
	}

	private void processBowlers(ScorecardInnings inning, List<Bowler> inningBowlers) {
		if (!CollectionUtils.isEmpty(inningBowlers)) {
			for (int i = 0; i < inningBowlers.size(); i++) {
				Bowler bowler = inningBowlers.get(i);
				bowler.setMatch(match);
				bowler.setInnings(inning.getInningNumber());
				bowler.setBowlerId(bowler.getPlayer().getObjectId());
				bowler.setPosition(i+1);
			}
		} else {
			LOGGER.info("Scorecard innings bowlers is Empty for {}", matchId);
		}
	}

	private void processPartnership(ScorecardInnings inning, List<Partnership> inningPartnerships) {
		if (!CollectionUtils.isEmpty(inningPartnerships)) {
			for (int i = 0; i < inningPartnerships.size(); i++) {
				Partnership partnership = inningPartnerships.get(i);
				partnership.setMatch(match);
				partnership.setInnings(inning.getInningNumber());
				partnership.setPlayer1Id(partnership.getPlayer1().getObjectId());
				partnership.setPlayer2Id(partnership.getPlayer2().getObjectId());
				partnership.setWicketNumber(i+1);
				if (partnership.getPlayer1().getId().equals(partnership.getOutPlayerId())) {
					partnership.setOutPlayerObjectId(partnership.getPlayer1Id());
				} else if (partnership.getPlayer2().getId().equals(partnership.getOutPlayerId())) {
					partnership.setOutPlayerObjectId(partnership.getPlayer2Id());
				}
			}
		} else {
			LOGGER.info("Partnership is Empty for {}", matchId);
		}
	}

	private void processWickets(ScorecardInnings inning, List<Fow> inningWickets) {
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
		} else {
			LOGGER.info("Fow is Empty for {}", matchId);
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
		} else {
			LOGGER.info("dismissalText is NULL");
		}
	}

	private void parseDismissalFielders(Fow e, List<DismissalFielder> dismissalFielders) {
		if (!CollectionUtils.isEmpty(dismissalFielders)) {
			e.setIsKeeper(0);
			e.setIsSubstitute(0);
			for(int i = 0; i < dismissalFielders.size(); i++) {
				DismissalFielder dismissalFielder = dismissalFielders.get(i);
				if(dismissalFielder.getIsKeeper() == 1) {
					e.setIsKeeper(1);
				}
				if(dismissalFielder.getIsSubstitute() == 1) {
					e.setIsSubstitute(1);
				}
				parseFielders(e, i, dismissalFielder);
			}
		}
	}

	private void parseFielders(Fow e, int i, DismissalFielder dismissalFielder) {
		PlayerOrTeam player = dismissalFielder.getPlayer();
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
