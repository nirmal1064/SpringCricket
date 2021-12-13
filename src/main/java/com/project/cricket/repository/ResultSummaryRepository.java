package com.project.cricket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cricket.entity.ResultSummary;

@Repository
public interface ResultSummaryRepository extends JpaRepository<ResultSummary, Integer> {

	public List<ResultSummary> findByYearBetween(int start, int end);

	public List<ResultSummary> findByClassIdAndYearBetween(int classId, int start, int end);

	public List<ResultSummary> findByClassId(int classId);

}
