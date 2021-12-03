package com.project.cricket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results_summary")
@Getter
@Setter
public class ResultSummary {
	@Id
	private Integer matchId;
	private int classId;
	private String team1;
	private String team2;
	private String winner;
	private String margin;
	private String ground;
	private int groundId;
	private String matchDate;
	private String scorecard;
	private int year;
	private String matchUrl;
	private String matchJsonUrl;
	private int matchNumber;
}
