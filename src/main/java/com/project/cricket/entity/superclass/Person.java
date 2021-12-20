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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"match", "teamId", "objectId", "playerType"})
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
	private Integer ageDays;

	@SerializedName("age_years")
	private Integer ageYears;

	@SerializedName("alpha_name")
	@Column(length = 50)
	private String alphaName;

	@SerializedName("batting_hand")
	@Column(length = 40)
	private String battingHand;

	@SerializedName("bowling_hand")
	@Column(length = 40)
	private String bowlingHand;

	@SerializedName("bowling_pacespin")
	@Column(length = 40)
	private String bowlingPacespin;

	@SerializedName("card_long")
	@Column(length = 40)
	private String cardLong;

	@SerializedName("card_qualifier")
	@Column(length = 40)
	private String cardQualifier;

	@SerializedName("card_short")
	@Column(length = 40)
	private String cardShort;

	private LocalDate dob;

	@SerializedName("known_as")
	@Column(length = 100)
	private String knownAs;

	@SerializedName("mobile_name")
	@Column(length = 50)
	private String mobileName;

	@Id
	@SerializedName("object_id")
	private Integer objectId;

	@SerializedName("player_id")
	private Integer playerId;

	@Id
	@SerializedName("player_type")
	private Integer playerType;

	@SerializedName("player_type_name")
	@Column(length = 50)
	private String playerTypeName;

	@SerializedName("popular_name")
	@Column(length = 50)
	private String popularName;

	@SerializedName("portrait_alt_id")
	@Column(length = 30)
	private String portraitAltId;

	@SerializedName("portrait_object_id")
	private Integer portraitObjectId;

	@SerializedName("status_id")
	private Integer statusId;

}
