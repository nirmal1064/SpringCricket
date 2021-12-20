package com.project.cricket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cricket.config.ApplicationConfiguration;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.repository.MatchSummaryRepository;
import com.project.cricket.repository.ResultSummaryRepository;
import com.project.cricket.utils.ExecutorUtil;

@Service
@Transactional
public class DbService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbService.class);

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Autowired
	private ApplicationConfiguration appConfig;

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private MatchSummaryRepository matchSummaryRepository;

	@Autowired
    private EntityManagerFactory emf;

	@Autowired
	private ExecutorUtil executorUtil;

	private static AtomicInteger counter;


	/**
	 * Save the resultSummary to the db and return the count of the saved details
	 * @param resultSummary
	 * @return
	 */
	public int saveResultsSummaryToDb(List<ResultSummary> resultSummary) {
		List<ResultSummary> saveResults = resultSummaryRepository.saveAll(resultSummary);
		return saveResults.size();
	}

	private void saveMatches(List<Match> matches) {
		LOGGER.info("Saving {} matches from batch {}", matches.size(), counter);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Match match : matches) {
			em.persist(match);
//			match.getInnings().stream().forEach(e -> em.persist(e));
//			match.getPlayer().stream().forEach(e -> em.persist(e));
//			match.getSeries().stream().forEach(e -> em.persist(e));
//			match.getOfficial().stream().forEach(e -> em.persist(e));
//			match.getBatsmen().stream().forEach(e -> em.persist(e));
//			match.getBowlers().stream().forEach(e -> em.persist(e));
//			match.getPartnerships().stream().forEach(e -> em.persist(e));
//			match.getFows().stream().forEach(e -> em.persist(e));
//			match.getDebuts().stream().forEach(e -> em.persist(e));
//			match.getReplacement().stream().forEach(e -> em.persist(e));
//			match.getPlayersOfTheMatch().stream().forEach(e -> em.persist(e));
//			match.getPlayersOfTheSeries().stream().forEach(e -> em.persist(e));
		}
		LOGGER.info("Saving {} matches from batch {}", matches.size(), counter);
		em.getTransaction().commit();
		em.clear();
	}

	@Async
	public CompletableFuture<List<Match>> saveMatchesFuture(List<Match> matches){
		try {
			final long start = System.currentTimeMillis();
			LOGGER.info("Saving {} matches", matches.size());
			List<Match> result = matchSummaryRepository.saveAll(matches);
			LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}
		return null;
	}

	public List<Integer> saveInBatches(List<Match> matches) {
		counter = new AtomicInteger(1);
		List<Integer> result = new ArrayList<>();
		int totalMatches = matches.size();
		List<List<Match>> batchMatches = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			for (int i = 0; i < totalMatches; i = i + batchSize) {
				if (i + batchSize > totalMatches) {
					batchMatches.add(matches.subList(i, totalMatches));
				} else {
					batchMatches.add(matches.subList(i, i + batchSize));
				}
			}
			LOGGER.info("Total batchsize {}", batchMatches.size());
			List<Callable<List<Match>>> callables = batchMatches.stream()
					.map(subMatches -> (Callable<List<Match>>) () -> {
						int count = counter.getAndIncrement();
						LOGGER.info("Executing batch {}", count);
						saveMatches(subMatches);
						//matchSummaryRepository.saveAllAndFlush(subMatches);
						LOGGER.info("Executed batch {}", count);
						return subMatches;
					}).collect(Collectors.toList());

			LOGGER.info("Submitting matches to executorservice ");
			List<Future<List<Match>>> invokeAll = service.invokeAll(callables);
			LOGGER.info("Executorservice Completed");
			for (Future<List<Match>> future : invokeAll) {
				List<Match> list = future.get();
				if (list != null) {
					result.addAll(list.stream().map(Match::getMatchId).collect(Collectors.toList()));
				}
			}
			LOGGER.info("Sending back results");
			service.shutdown();
			LOGGER.info("Completed in saving matches with size {}", result.size());
		} catch (Exception e) {
			LOGGER.error("Exception in saving match ", e);
		}
		return result;
	}

	public List<Integer> saveAllMatches(List<Match> matches) {
		LOGGER.info("Saving {} matches", matches.size());
		List<Integer> result = new ArrayList<>();
		try {
			ExecutorService service = executorUtil.getThreadPool(appConfig.getNumOfThreads());
			for (Match match : matches) {
				service.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Match save = matchSummaryRepository.save(match);
							result.add(save.getMatchId());
							LOGGER.info("Match {} with id {} completed", result.size(), match.getMatchId());
						} catch (Exception e) {
							LOGGER.error("Match {} save failed", match.getMatchId());
						}
					}
				});
			}
			service.shutdown();
			LOGGER.info("Completed in saving matches with size {}", result.size());
			return result;
		} catch (Exception e) {
			LOGGER.error("Exception in saving match ", e);
		}
		return new ArrayList<>();
	}

	public List<Integer> saveMatchesInBatches(List<Match> matches) {
		int totalMatches = matches.size();
		List<Integer> result = new ArrayList<>();
		try {
			for (int i = 0; i < totalMatches; i = i + batchSize) {
				if (i+ batchSize > totalMatches) {
					List<Match> matchBatch = matches.subList(i, totalMatches);
					matchSummaryRepository.saveAll(matchBatch);
					//saveMatches(matchBatch);
					result.addAll(matchBatch.parallelStream().map(Match::getMatchId).collect(Collectors.toList()));
					LOGGER.info("Final Batch Completed");
					break;
				}
				List<Match> matchBatch = matches.subList(i, i + batchSize);
				matchSummaryRepository.saveAll(matchBatch);
				//saveMatches(matchBatch);
				result.addAll(matchBatch.parallelStream().map(Match::getMatchId).collect(Collectors.toList()));
				LOGGER.info("{} matches completed", i + batchSize);
			}
			LOGGER.info("Sending back results");
			return result;
		} catch (Exception e) {
			LOGGER.error("Error in saving match", e);
		}
		return new ArrayList<>();
	}

	public List<Integer> saveMatchesAll(List<Match> matches) {
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
