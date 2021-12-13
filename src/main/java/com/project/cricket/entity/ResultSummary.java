package com.project.cricket.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "results_summary")
@Data
public class ResultSummary implements Serializable {

	private static final long serialVersionUID = 1L;

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
