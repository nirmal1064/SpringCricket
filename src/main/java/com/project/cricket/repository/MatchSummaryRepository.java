package com.project.cricket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cricket.entity.Match;

@Repository
public interface MatchSummaryRepository extends JpaRepository<Match, Integer> {

}
