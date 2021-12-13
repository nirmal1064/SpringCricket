package com.project.cricket.handler;

import static com.project.cricket.utils.Constants.FIELDUMPIRE;
import static com.project.cricket.utils.Constants.MATCHREFEREE;
import static com.project.cricket.utils.Constants.RESERVEUMPIRE;
import static com.project.cricket.utils.Constants.TVUMPIRE;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.cricket.entity.Batsman;
import com.project.cricket.entity.Bowler;
import com.project.cricket.entity.Fow;
import com.project.cricket.entity.Innings;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.Official;
import com.project.cricket.entity.Partnership;
import com.project.cricket.entity.Player;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.entity.ScorecardOfficial;
import com.project.cricket.entity.Series;
import com.project.cricket.entity.superclass.MatchPerson;
import com.project.cricket.model.DismissalFielder;
import com.project.cricket.model.MatchJson;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.model.ScorecardInnings;
import com.project.cricket.model.ScorecardMatch;
import com.project.cricket.model.SupportInfo;
import com.project.cricket.model.Team;
import com.project.cricket.repository.MatchSummaryRepository;
import com.project.cricket.repository.ResultSummaryRepository;

@Service
public class DbHandler {

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private MatchSummaryRepository matchSummaryRepository;

	/**
	 * Save the resultSummary to the db and return the count of the saved details
	 * @param resultSummary
	 * @return
	 */
	public int saveResultsSummaryToDb(List<ResultSummary> resultSummary) {
		List<ResultSummary> saveResults = resultSummaryRepository.saveAll(resultSummary);
		return saveResults.size();
	}

	private void addMatchAndType(List<ScorecardOfficial> umps, Match match, String type) {
		if (!CollectionUtils.isEmpty(umps)) {
			umps.forEach(e -> {
				e.setMatch(match);
				e.setType(type);
				e.setObjectId(e.getPlayer().getObjectId());
			});
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

	public void saveMatchFromScorecardToDb(MatchScorecard matchScorecard) {
		Match match = new Match();
		match.setMatchId(matchScorecard.getMatchId());

		ScorecardMatch scorecardMatch = matchScorecard.getMatch();
		addMatchAndType(scorecardMatch.getUmpires(), match, FIELDUMPIRE);
		addMatchAndType(scorecardMatch.getTvUmpires(), match, TVUMPIRE);
		addMatchAndType(scorecardMatch.getReserveUmpires(), match, RESERVEUMPIRE);
		addMatchAndType(scorecardMatch.getMatchReferees(), match, MATCHREFEREE);
		addMatchAndId(scorecardMatch.getDebutPlayers(), match);
		scorecardMatch.getReplacementPlayers().forEach(e -> {
			e.setMatch(match);
			e.setObjectId(e.getPlayer().getObjectId());
			e.setTeamId(e.getTeam().getObjectId());
			e.setReplacingPlayerId(e.getReplacingPlayer().getObjectId());
		});

		SupportInfo supportInfo = matchScorecard.getSupportInfo();
		addMatchAndId(supportInfo.getPlayersOfTheMatch(), match);
		addMatchAndId(supportInfo.getPlayersOfTheSeries(), match);

		List<ScorecardInnings> innings = matchScorecard.getScorecard().getInnings();
		for (ScorecardInnings inning : innings) {
			List<Batsman> inningBatsmen = inning.getInningBatsmen();
			for (int i = 0; i < inningBatsmen.size(); i++) {
				Batsman batsman = inningBatsmen.get(i);
				batsman.setMatch(match);
				batsman.setInnings(inning.getInningNumber());
				batsman.setBatsmanId(batsman.getPlayer().getObjectId());
				batsman.setPosition(i);
			}
			List<Bowler> inningBowlers = inning.getInningBowlers();
			for (int i = 0; i < inningBowlers.size(); i++) {
				Bowler bowler = inningBowlers.get(i);
				bowler.setMatch(match);
				bowler.setInnings(inning.getInningNumber());
				bowler.setBowlerId(bowler.getPlayer().getObjectId());
				bowler.setPosition(i);
			}
			List<Partnership> inningPartnerships = inning.getInningPartnerships();
			for (int i = 0; i < inningPartnerships.size(); i++) {
				Partnership partnership = inningPartnerships.get(i);
				partnership.setMatch(match);
				partnership.setInnings(inning.getInningNumber());
				partnership.setPlayer1Id(partnership.getPlayer1().getObjectId());
				partnership.setPlayer2Id(partnership.getPlayer2().getObjectId());
				partnership.setWicketNumber(i);
			}
			List<Fow> inningWickets = inning.getInningWickets();
			inningWickets.forEach(e -> {
				e.setMatch(match);
				e.setInnings(inning.getInningNumber());
				if(e.getDismissalBatsman() != null) {
					e.setBatsmanId(e.getDismissalBatsman().getObjectId());
				}
				if(e.getDismissalBowler() != null) {
					e.setBowlerId(e.getDismissalBowler().getObjectId());
				}
				List<DismissalFielder> dismissalFielders = e.getDismissalFielders();
				for(int i = 0; i < dismissalFielders.size(); i++) {
					DismissalFielder dismissalFielder = dismissalFielders.get(i);
					if(dismissalFielder.getIsKeeper() == 1) {
						e.setIsKeeper(1);
					}
					if(dismissalFielder.getIsSubstitute() == 1) {
						e.setIsSubstitute(1);
					}
					if(i == 0) {
						e.setFielder1Id(dismissalFielder.getPlayer().getObjectId());
					} else if (i == 1) {
						e.setFielder2Id(dismissalFielder.getPlayer().getObjectId());
					} else if (i == 2) {
						e.setFielder3Id(dismissalFielder.getPlayer().getObjectId());
					} else if (i == 3) {
						e.setFielder4Id(dismissalFielder.getPlayer().getObjectId());
					}
				}
			});
		}
		System.err.println("Hello");
	}

	public void saveMatchFromJsonToDb(MatchJson matchJson) {
		Match match = matchJson.getMatch();
		match.setMatchId(matchJson.getMatchId());
		List<Innings> innings = matchJson.getInnings();
		innings.forEach(e -> e.setMatch(matchJson.getMatch()));
		List<Team> team = matchJson.getTeam();
		team.forEach(tm -> tm.getPlayer().forEach(p -> p.setTeamId(tm.getTeamId())));
		List<Player> players = team.stream()
				.map(Team::getPlayer)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		players.addAll(matchJson.getSubstitute());
		players.forEach(p -> p.setMatch(match));
		List<Series> series = matchJson.getSeries();
		series.forEach(s -> s.setMatch(match));
		List<Official> official = matchJson.getOfficial();
		official.forEach(o -> o.setMatch(match));
		match.setInnings(innings);
		match.setPlayer(players);
		match.setOfficial(official);
		match.setSeries(series);
		matchSummaryRepository.save(match);
	}

}
