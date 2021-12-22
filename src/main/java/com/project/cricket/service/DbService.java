package com.project.cricket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cricket.entity.Match;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.repository.ResultSummaryRepository;

@Service
@Transactional
public class DbService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbService.class);

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private EntityManagerFactory emf;

	/**
	 * Save the resultSummary to the db and return the count of the saved details
	 * @param resultSummary
	 * @return
	 */
	public int saveResultsSummaryToDb(List<ResultSummary> resultSummary) {
		List<ResultSummary> saveResults = resultSummaryRepository.saveAll(resultSummary);
		return saveResults.size();
	}

	public void saveMatches(List<Match> matches) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Match match : matches) {
			em.persist(match);
			match.getInnings().stream().forEach(em::persist);
			match.getPlayer().stream().forEach(em::persist);
			match.getSeries().stream().forEach(em::persist);
			match.getOfficial().stream().forEach(em::persist);
			match.getBatsmen().stream().forEach(em::persist);
			match.getBowlers().stream().forEach(em::persist);
			match.getPartnerships().stream().forEach(em::persist);
			match.getFows().stream().forEach(em::persist);
			match.getDebuts().stream().forEach(em::persist);
			match.getReplacement().stream().forEach(em::persist);
			match.getPlayersOfTheMatch().stream().forEach(em::persist);
			match.getPlayersOfTheSeries().stream().forEach(em::persist);
		}
		em.getTransaction().commit();
		em.clear();
		em.close();
	}

	public List<Integer> saveMatchesInBatches(List<Match> matches) {
		int totalMatches = matches.size();
		List<Integer> result = new ArrayList<>();
		try {
			for (int i = 0; i < totalMatches; i = i + batchSize) {
				if (i + batchSize > totalMatches) {
					List<Match> matchBatch = matches.subList(i, totalMatches);
					saveMatches(matchBatch);
					result.addAll(matchBatch.parallelStream().map(Match::getMatchId).collect(Collectors.toList()));
					LOGGER.info("Final Batch Completed");
					break;
				}
				List<Match> matchBatch = matches.subList(i, i + batchSize);
				saveMatches(matchBatch);
				result.addAll(matchBatch.parallelStream().map(Match::getMatchId).collect(Collectors.toList()));
				LOGGER.info("{} matches completed", i + batchSize);
			}
			LOGGER.info("Sending back results");
			return result;
		} catch (Exception e) {
			LOGGER.error("Error in saving match", e);
		}
		return result;
	}

}
