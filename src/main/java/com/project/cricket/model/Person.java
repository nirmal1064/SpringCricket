package com.project.cricket.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
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
	@Column(length = 30)
	protected String alphaName;

	@SerializedName("batting_hand")
	@Column(length = 40)
	protected String battingHand;

	@SerializedName("bowling_hand")
	@Column(length = 40)
	protected String bowlingHand;

	@SerializedName("bowling_pacespin")
	@Column(length = 40)
	protected String bowlingPacespin;

	@SerializedName("card_long")
	@Column(length = 40)
	protected String cardLong;

	@SerializedName("card_qualifier")
	@Column(length = 40)
	protected String cardQualifier;

	@SerializedName("card_short")
	@Column(length = 40)
	protected String cardShort;

	protected LocalDate dob;

	@SerializedName("known_as")
	@Column(length = 40)
	protected String knownAs;

	@SerializedName("mobile_name")
	@Column(length = 40)
	protected String mobileName;

	@Id
	@SerializedName("object_id")
	protected int objectId;

	@SerializedName("player_id")
	protected int playerId;

	@SerializedName("player_type")
	protected int playerType;

	@SerializedName("player_type_name")
	@Column(length = 30)
	protected String playerTypeName;

	@SerializedName("popular_name")
	@Column(length = 30)
	protected String popularName;

	@SerializedName("portrait_alt_id")
	@Column(length = 30)
	protected String portraitAltId;

	@SerializedName("portrait_object_id")
	protected int portraitObjectId;

	@SerializedName("status_id")
	protected int statusId;

}
