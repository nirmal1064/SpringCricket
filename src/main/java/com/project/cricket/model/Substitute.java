package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Substitute extends Player {

	private static final long serialVersionUID = 1L;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_short_name")
	private String teamShortName;

}