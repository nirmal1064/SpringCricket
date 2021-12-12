package com.project.cricket.model;

import lombok.Data;

@Data
public class Batsman {
	private String 	playerRoleType;
	private String 	battedType;
	private Integer runs;
	private Integer balls;
	private Integer minutes;
	private Integer fours;
	private Integer sixes;
	private Double 	strikerate;
	private Boolean isOut;
	private Integer dismissalType;
}
