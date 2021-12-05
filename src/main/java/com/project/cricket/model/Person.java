package com.project.cricket.model;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Person {

	@SerializedName("age_days")
	protected int ageDays;

	@SerializedName("age_years")
	protected int ageYears;

	@SerializedName("alpha_name")
	protected String alphaName;

	@SerializedName("batting_hand")
	protected String battingHand;

	@SerializedName("bowling_hand")
	protected String bowlingHand;

	@SerializedName("bowling_pacespin")
	protected String bowlingPacespin;

	@SerializedName("card_long")
	protected String cardLong;

	@SerializedName("card_qualifier")
	protected String cardQualifier;

	@SerializedName("card_short")
	protected String cardShort;

	protected LocalDate dob;

	@SerializedName("known_as")
	protected String knownAs;

	@SerializedName("mobile_name")
	protected String mobileName;

	@SerializedName("object_id")
	protected int objectId;

	@SerializedName("player_id")
	protected String playerId;

	@SerializedName("player_type")
	protected int playerType;

	@SerializedName("player_type_name")
	protected String playerTypeName;

	@SerializedName("popular_name")
	protected String popularName;

	@SerializedName("portrait_alt_id")
	protected String portraitAltId;

	@SerializedName("portrait_object_id")
	protected int portraitObjectId;

	@SerializedName("status_id")
	protected int statusId;

}
