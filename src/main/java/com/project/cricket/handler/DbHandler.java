package com.project.cricket.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cricket.entity.Match;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.repository.MatchSummaryRepository;
import com.project.cricket.repository.ResultSummaryRepository;

@Service
@Transactional
public class DbHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbHandler.class);

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

	public List<Integer> saveAllMatches(List<Match> matches) {
		try {
			List<Match> savedMatches = matchSummaryRepository.saveAllAndFlush(matches);
			return savedMatches.stream().map(Match::getMatchId).collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error("Error in saving match", e);
		}
		return new ArrayList<>();
	}

	public Integer saveMatchToDb(Match match) {
		try {
			Match savedMatch = matchSummaryRepository.saveAndFlush(match);
			return savedMatch.getMatchId();
		} catch (Exception e) {
			LOGGER.error("Error in saving match {}", match.getMatchId(), e);
		}
		return null;
	}

}
