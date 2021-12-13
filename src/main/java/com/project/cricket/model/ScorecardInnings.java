package com.project.cricket.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ScorecardInnings {

	private Integer inningNumber;

	private PlayerOrTeam team;

	private List<Batsman> inningBatsmen = new ArrayList<>();

	private List<Bowler> inningBowlers = new ArrayList<>();

	private List<Partnership> inningPartnerships = new ArrayList<>();

	private List<Fow> inningWickets = new ArrayList<>();
}
