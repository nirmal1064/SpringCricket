package com.project.cricket.model;

import lombok.Data;

@Data
public class Fow {
	private Integer dismissalType;
	private String 	batsmanId;
	private String 	bowlerId;
	private String 	fielder1Id;
	private String 	fielder2Id;
	private String 	fielder3Id;
	private String 	fielder4Id;
	private String 	dismissalTextShort;
	private String 	dismissalTextLong;
	private Integer fowOrder;
	private Integer fowWicketNum;
	private Integer fowRuns;
	private Integer fowBalls;
	private Double 	fowOvers;
	private Integer fowOverNumber;
	private Double 	fowOversActual;
	private Integer ballTotalRuns;
	private Integer ballBatsmanRuns;
}
