package com.project.cricket.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.project.cricket.model.ResultSummary;
import com.project.cricket.repository.ResultSummaryRepository;

@Configuration
public class DbHandler {

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	/**
	 * Save the resultSummary to the db and return the count of the saved details
	 * @param resultSummary
	 * @return
	 */
	public int saveResultsSummaryToDb(List<ResultSummary> resultSummary) {
		List<ResultSummary> saveResults = resultSummaryRepository.saveAll(resultSummary);
		return saveResults.size();
	}
}
