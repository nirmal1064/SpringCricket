package com.project.cricket.model;

import java.util.List;

import lombok.Data;

@Data
public class ScorecardInnings {

	private Integer inningNumber;

	private PlayerOrTeam team;

	private List<Batsman> inningBatsmen;

	private List<Bowler> inningBowlers;

	private List<Partnership> inningPartnerships;

	private List<Fow> inningWickets;
}
