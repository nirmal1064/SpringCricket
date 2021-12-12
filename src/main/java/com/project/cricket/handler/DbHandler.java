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

import com.project.cricket.model.Batsman;
import com.project.cricket.model.Bowler;
import com.project.cricket.model.DismissalFielder;
import com.project.cricket.model.Fow;
import com.project.cricket.model.Innings;
import com.project.cricket.model.Match;
import com.project.cricket.model.MatchJson;
import com.project.cricket.model.MatchScorecard;
import com.project.cricket.model.Official;
import com.project.cricket.model.Partnership;
import com.project.cricket.model.Player;
import com.project.cricket.model.ResultSummary;
import com.project.cricket.model.ScorecardInnings;
import com.project.cricket.model.ScorecardMatch;
import com.project.cricket.model.ScorecardOfficial;
import com.project.cricket.model.Series;
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

	public void saveMatchFromScorecardToDb(MatchScorecard matchScorecard) {
		Match match = new Match();
		match.setMatchId(matchScorecard.getMatchId());

		ScorecardMatch scorecardMatch = matchScorecard.getMatch();
		addMatchAndType(scorecardMatch.getUmpires(), match, FIELDUMPIRE);
		addMatchAndType(scorecardMatch.getTvUmpires(), match, TVUMPIRE);
		addMatchAndType(scorecardMatch.getReserveUmpires(), match, RESERVEUMPIRE);
		addMatchAndType(scorecardMatch.getMatchReferees(), match, MATCHREFEREE);
		scorecardMatch.getDebutPlayers().forEach(e -> {
			e.setMatch(match);
			e.setObjectId(e.getPlayer().getObjectId());
		});

		SupportInfo supportInfo = matchScorecard.getSupportInfo();
		if (!CollectionUtils.isEmpty(supportInfo.getPlayersOfTheMatch())) {
			supportInfo.getPlayersOfTheMatch().forEach(e -> {
				e.setMatch(match);
				e.setObjectId(e.getPlayer().getObjectId());
			});
		}
		if (!CollectionUtils.isEmpty(supportInfo.getPlayersOfTheSeries())) {
			supportInfo.getPlayersOfTheSeries().forEach(e -> {
				e.setMatch(match);
				e.setObjectId(e.getPlayer().getObjectId());
			});
		}

		List<ScorecardInnings> innings = matchScorecard.getScorecard().getInnings();
		for (ScorecardInnings inning : innings) {
			List<Batsman> inningBatsmen = inning.getInningBatsmen();
			inningBatsmen.forEach(e -> {
				e.setMatch(match);
				e.setInnings(inning.getInningNumber());
				e.setBatsmanId(e.getPlayer().getObjectId());
			});
			List<Bowler> inningBowlers = inning.getInningBowlers();
			inningBowlers.forEach(e -> {
				e.setMatch(match);
				e.setInnings(inning.getInningNumber());
				e.setBowlerId(e.getPlayer().getObjectId());
			});
			List<Partnership> inningPartnerships = inning.getInningPartnerships();
			inningPartnerships.forEach(e -> {
				e.setMatch(match);
				e.setInnings(inning.getInningNumber());
				e.setPlayer1Id(e.getPlayer1().getObjectId());
				e.setPlayer2Id(e.getPlayer2().getObjectId());
			});
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
					//DismissalFielder dismissalFielder = dismissalFielders.get(i);
					switch(i) {
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					default:
						break;
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
