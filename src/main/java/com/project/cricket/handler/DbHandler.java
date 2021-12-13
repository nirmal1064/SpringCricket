package com.project.cricket.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cricket.entity.Innings;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.Official;
import com.project.cricket.entity.Player;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.entity.Series;
import com.project.cricket.model.MatchJson;
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
