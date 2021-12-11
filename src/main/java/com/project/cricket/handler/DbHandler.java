package com.project.cricket.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cricket.model.Match;
import com.project.cricket.model.ResultSummary;
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

	public void saveMatchToDb(Match match) {
		matchSummaryRepository.save(match);
	}

}
