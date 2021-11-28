package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Innings {

	@SerializedName("ball_limit")
	private int ballLimit;

	private int balls;

	private int batted;

	@SerializedName("batting_team_id")
	private int battingTeamId;

	@SerializedName("bowling_team_id")
	private int bowlingTeamId;

	private int bpo;

	private int byes;

	private int event;

	@SerializedName("event_name")
	private String eventName;

	private int extras;

	@SerializedName("innings_number")
	private String inningsNumber;

	@SerializedName("innings_numth")
	private String inningsNumth;

	private int lead;

	private int legbyes;

	@SerializedName("live_current")
	private int liveCurrent;

	@SerializedName("live_current_name")
	private String liveCurrentName;

	private String minutes;

	private int noballs;

	@SerializedName("old_penalty_or_bonus")
	private int oldPenaltyOrBonus;

	@SerializedName("over_limit")
	private String overLimit;

	@SerializedName("over_limit_run_rate")
	private String overLimitRunRate;

	@SerializedName("over_split_limit")
	private String overSplitLimit;

	private String overs;

	@SerializedName("overs_docked")
	private int oversDocked;

	private int penalties;

	@SerializedName("penalties_field_end")
	private int penaltiesFieldEnd;

	@SerializedName("penalties_field_start")
	private int penaltiesFieldStart;

	@SerializedName("run_rate")
	private String runRate;

	private int runs;

	private int target;

	private int wickets;

	private int wides;

}
