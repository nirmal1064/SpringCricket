package com.project.cricket.model;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.model.keys.InningsKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "match_innings")
@IdClass(InningsKey.class)
@EqualsAndHashCode
public class Innings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@SerializedName("ball_limit")
	private Integer ballLimit;

	private Integer balls;

	private Integer batted;

	@SerializedName("batting_team_id")
	private Integer battingTeamId;

	@SerializedName("bowling_team_id")
	private Integer bowlingTeamId;

	private Integer bpo;

	private Integer byes;

	private Integer event;

	@SerializedName("event_name")
	private String eventName;

	private Integer extras;

	@Id
	@SerializedName("innings_number")
	private Integer inningsNumber;

	@SerializedName("innings_numth")
	private String inningsNumth;

	@Column(name = "leadby")
	private Integer lead;

	private Integer legbyes;

	@SerializedName("live_current")
	private Integer liveCurrent;

	@SerializedName("live_current_name")
	private String liveCurrentName;

	private Integer minutes;

	private Integer noballs;

	@SerializedName("old_penalty_or_bonus")
	private Integer oldPenaltyOrBonus;

	@SerializedName("over_limit")
	private Double overLimit;

	@SerializedName("over_limit_run_rate")
	private String overLimitRunRate;

	@SerializedName("over_split_limit")
	private String overSplitLimit;

	private Double overs;

	@SerializedName("overs_docked")
	private Integer oversDocked;

	private Integer penalties;

	@SerializedName("penalties_field_end")
	private Integer penaltiesFieldEnd;

	@SerializedName("penalties_field_start")
	private Integer penaltiesFieldStart;

	@SerializedName("run_rate")
	private Double runRate;

	private Integer runs;

	private Integer target;

	private Integer wickets;

	private Integer wides;

	@Override
	public String toString() {
		return "Innings [match=" + match + ", inningsNumber=" + inningsNumber + "]";
	}

}
