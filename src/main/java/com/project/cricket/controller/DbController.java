package com.project.cricket.controller;

import static com.project.cricket.utils.Constants.FC;
import static com.project.cricket.utils.Constants.FCHASH;
import static com.project.cricket.utils.Constants.ICCTHASH;
import static com.project.cricket.utils.Constants.LA;
import static com.project.cricket.utils.Constants.ODIHASH;
import static com.project.cricket.utils.Constants.T20;
import static com.project.cricket.utils.Constants.T20IHASH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cricket.entity.Match;
import com.project.cricket.entity.ResultSummary;
import com.project.cricket.repository.MatchSummaryRepository;
import com.project.cricket.repository.ResultSummaryRepository;
import com.project.cricket.utils.Constants;

/**
 * 
 * @author Nirmal Kumar
 *
 */
@RestController
public class DbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

	@Autowired
	private ResultSummaryRepository resultSummaryRepository;

	@Autowired
	private MatchSummaryRepository matchSummaryRepository;

	@GetMapping(value = "/resultsummary", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryFromDb() {
		LOGGER.info("Getting results summary");
		return resultSummaryRepository.findAll();
	}

	@GetMapping(value = "/resultbetweenyears", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryBetweenYears(@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary betweem years {} and {}", startYear, endYear);
		return resultSummaryRepository.findByYearBetween(startYear, endYear);
	}

	@GetMapping(value = "/resultsByClass", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryClassId(@RequestParam Integer classId) {
		LOGGER.info("Getting results summary for class {} ", classId);
		return resultSummaryRepository.findByClassId(classId);
	}

	@GetMapping(value = "/resultsByClassBetweenYears", produces = APPLICATION_JSON_VALUE)
	public List<ResultSummary> getResultsSummaryClassIdBetweenYears(@RequestParam Integer classId,
			@RequestParam Integer startYear, @RequestParam Integer endYear) {
		LOGGER.info("Getting results summary for class {} between years {} and {}", classId, startYear, endYear);
		return resultSummaryRepository.findByClassIdAndYearBetween(classId, startYear, endYear);
	}

	@GetMapping(value = "/matchids", produces = APPLICATION_JSON_VALUE)
	public List<Integer> getMatchIds(@RequestParam Integer classId, @RequestParam Integer startYear, @RequestParam Integer endYear) {
		return resultSummaryRepository.findByClassIdAndYearBetween(classId, startYear, endYear).stream().map(ResultSummary::getMatchId).collect(Collectors.toList());
	}

	@GetMapping(value = "/missingids", produces = APPLICATION_JSON_VALUE)
	public List<Integer> getMissingMatchIds(@RequestParam Integer classId) {
		List<Integer> summaryMatchIds = resultSummaryRepository.findByClassId(classId).stream().map(ResultSummary::getMatchId).collect(Collectors.toList());
		List<Integer> matchIds = getAllMatchIdsFromMatchSummary();
		summaryMatchIds.removeAll(matchIds);
		return summaryMatchIds;
	}

	@GetMapping(value = "/missingyear", produces = APPLICATION_JSON_VALUE)
	public Set<Integer> getMissingYear() {
		List<ResultSummary> results = resultSummaryRepository.findAll();
		Set<Integer> noMatchYears = new HashSet<>(Arrays.asList(1875,1916,1917,1881,1918,1868,1874,1865,1861,1863,1862,1859,1860,1785));
		Set<Integer> yearsFromDb = results.stream().map(ResultSummary::getYear).collect(Collectors.toSet());
		Set<Integer> years = new HashSet<>();
		for (int i = 1772; i <= 2021; i++) {
			years.add(i);
		}
		years.removeAll(yearsFromDb);
		years.removeAll(noMatchYears);
		return years;
	}

	@GetMapping(value = "/dummy", produces = APPLICATION_JSON_VALUE)
	public Set<String> getssas() {
		List<ResultSummary> results = resultSummaryRepository.findAll();
		Predicate<ResultSummary> filter = p -> !p.getScorecard().contains(Constants.TESTHASH)
				&& !p.getScorecard().contains(ODIHASH)
				&& !p.getScorecard().contains(T20IHASH)
				&& !p.getScorecard().contains(T20)
				&& !p.getScorecard().contains(LA)
				&& !p.getScorecard().contains(ICCTHASH)
				&& !p.getScorecard().contains(FC)
				&& !p.getScorecard().contains(FCHASH);
		Set<String> collect = results.stream().filter(filter).map(ResultSummary::getScorecard).collect(Collectors.toSet());
		LOGGER.info("{}", collect);
		return collect;
	}

	public List<Integer> getAllMatchIdsFromMatchSummary() {
		return matchSummaryRepository.findAll().stream().map(Match::getMatchId).collect(Collectors.toList());
	}

}
