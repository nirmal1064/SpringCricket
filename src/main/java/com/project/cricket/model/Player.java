package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Person {

	@SerializedName("batting_style")
	private String battingStyle;

	@SerializedName("batting_style_long")
	private String battingStyleLong;

	@SerializedName("bowling_style")
	private String bowlingStyle;

	@SerializedName("bowling_style_long")
	private String bowlingStyleLong;

	private int captain;

	private int keeper;

	@SerializedName("player_primary_role")
	private String playerPrimaryRole;

	@SerializedName("player_style_id")
	private int playerStyleId;

}