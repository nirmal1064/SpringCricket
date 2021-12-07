package com.project.cricket.model;

import javax.persistence.Column;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Substitute extends Player {

	private static final long serialVersionUID = 1L;

	@SerializedName("full_sub")
	private int fullSub;

	@SerializedName("full_sub_innings")
	private int fullSubInnings;

	@SerializedName("full_sub_name")
	@Column(length = 30)
	private String fullSubName;

	@SerializedName("full_sub_overs")
	private double fullSubOvers;

	@SerializedName("full_sub_player_id")
	private int fullSubPlayerId;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_short_name")
	private String teamShortName;

}