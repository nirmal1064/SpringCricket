package com.project.cricket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "match_innings")
public class Innings {

	@Id
	@Column(name = "match_id")
	private Integer matchId;

	@SerializedName("ball_limit")
	@Column(name = "ball_limit")
	private int ballLimit;

	private int balls;

	private int batted;

	@SerializedName("batting_team_id")
	@Column(name = "batting_team_id")
	private int battingTeamId;

	@SerializedName("bowling_team_id")
	@Column(name = "bowling_team_id")
	private int bowlingTeamId;

	private int bpo;

	private int byes;

	private int event;

	@SerializedName("event_name")
	@Column(name = "event_name")
	private String eventName;

	private int extras;

	@SerializedName("innings_number")
	private int inningsNumber;

	@SerializedName("innings_numth")
	private String inningsNumth;

	private int lead;

	private int legbyes;

	@SerializedName("live_current")
	private int liveCurrent;

	@SerializedName("live_current_name")
	private String liveCurrentName;

	private int minutes;

	private int noballs;

	@SerializedName("old_penalty_or_bonus")
	@Column(name = "old_penalty_or_bonus")
	private int oldPenaltyOrBonus;

	@SerializedName("over_limit")
	@Column(name = "over_limit")
	private double overLimit;

	@SerializedName("over_limit_run_rate")
	@Column(name = "over_limit_run_rate")
	private String overLimitRunRate;

	@SerializedName("over_split_limit")
	@Column(name = "over_split_limit")
	private String overSplitLimit;

	private double overs;

	@SerializedName("overs_docked")
	@Column(name = "overs_docked")
	private int oversDocked;

	private int penalties;

	@SerializedName("penalties_field_end")
	@Column(name = "penalties_field_end")
	private int penaltiesFieldEnd;

	@SerializedName("penalties_field_start")
	@Column(name = "penalties_field_start")
	private int penaltiesFieldStart;

	@SerializedName("run_rate")
	@Column(name = "run_rate")
	private double runRate;

	private int runs;

	private int target;

	private int wickets;

	private int wides;

}
