package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Official extends Person {

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_id")
	private Integer teamId;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;

}