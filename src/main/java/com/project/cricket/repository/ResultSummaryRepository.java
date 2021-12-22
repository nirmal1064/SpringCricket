package com.project.cricket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cricket.entity.ResultSummary;

@Repository
public interface ResultSummaryRepository extends JpaRepository<ResultSummary, Integer> {

	public List<ResultSummary> findByYearBetween(Integer start, Integer end);

	public List<ResultSummary> findByClassIdAndYearBetween(Integer classId, Integer start, Integer end);

	public List<ResultSummary> findByClassId(Integer classId);

}
