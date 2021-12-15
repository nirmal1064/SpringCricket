package com.project.cricket.entity.superclass;

import static javax.persistence.FetchType.LAZY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.Match;
import com.project.cricket.entity.keys.PlayerKey;

import lombok.Data;

@Data
@MappedSuperclass
@IdClass(PlayerKey.class)
public class Person {

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Id
	@SerializedName(value = "team_id")
	private Integer teamId;

	@SerializedName("age_days")
	protected Integer ageDays;

	@SerializedName("age_years")
	protected Integer ageYears;

	@SerializedName("alpha_name")
	@Column(length = 50)
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
	@Column(length = 100)
	protected String knownAs;

	@SerializedName("mobile_name")
	@Column(length = 50)
	protected String mobileName;

	@Id
	@SerializedName("object_id")
	protected Integer objectId;

	@SerializedName("player_id")
	protected Integer playerId;

	@SerializedName("player_type")
	protected Integer playerType;

	@SerializedName("player_type_name")
	@Column(length = 50)
	protected String playerTypeName;

	@SerializedName("popular_name")
	@Column(length = 50)
	protected String popularName;

	@SerializedName("portrait_alt_id")
	@Column(length = 30)
	protected String portraitAltId;

	@SerializedName("portrait_object_id")
	protected Integer portraitObjectId;

	@SerializedName("status_id")
	protected Integer statusId;

}
