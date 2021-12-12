package com.project.cricket.model;

import lombok.Data;

@Data
public class Bowler {
	private Double 	overs;
	private Integer balls;
	private Integer maidens;
	private Integer conceded;
	private Integer wickets;
	private Double 	economy;
	private Double 	runsPerBall;
	private Integer dots;
	private Integer fours;
	private Integer sixes;
	private Integer wides;
	private Integer noballs;
}
