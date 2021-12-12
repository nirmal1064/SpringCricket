package com.project.cricket.model;

import lombok.Data;

@Data
public class Partnership {
	private String 	player1Id;
	private String 	player2Id;
	private Integer outPlayerId;
	private Integer player1Runs;
	private Integer player1Balls;
	private Integer player2Runs;
	private Integer player2Balls;
	private Integer runs;
	private Integer balls;
	private Double 	overs;
}
