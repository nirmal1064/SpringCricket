package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	@SerializedName("age_days")
	private int ageDays;

	@SerializedName("age_years")
	private int ageYears;

	@SerializedName("alpha_name")
	private String alphaName;

	@SerializedName("batting_hand")
	private String battingHand;

	@SerializedName("bowling_hand")
	private String bowlingHand;

	@SerializedName("bowling_pacespin")
	private String bowlingPacespin;

	@SerializedName("card_long")
	private String cardLong;

	@SerializedName("card_qualifier")
	private String cardQualifier;

	@SerializedName("card_short")
	private String cardShort;

	private String dob;

	@SerializedName("known_as")
	private String knownAs;

	@SerializedName("mobile_name")
	private String mobileName;

	@SerializedName("object_id")
	private int objectId;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("player_type")
	private int playerType;

	@SerializedName("player_type_name")
	private String playerTypeName;

	@SerializedName("popular_name")
	private String popularName;

	@SerializedName("portrait_alt_id")
	private String portraitAltId;

	@SerializedName("portrait_object_id")
	private int portraitObjectId;

	@SerializedName("status_id")
	private int statusId;

}
