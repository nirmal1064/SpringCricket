package com.project.cricket.entity;

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
import com.project.cricket.entity.keys.PlayerKey;
import com.project.cricket.entity.superclass.Person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "squad_summary")
@ToString
@IdClass(PlayerKey.class)
@EqualsAndHashCode(callSuper = true)
public class Player extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Id
	private Integer teamId;

	@Column(length = 30)
	private String teamName;

	@SerializedName("batting_style")
	@Column(length = 10)
	private String battingStyle;

	@SerializedName("batting_style_long")
	@Column(length = 30)
	private String battingStyleLong;

	@SerializedName("bowling_style")
	@Column(length = 10)
	private String bowlingStyle;

	@SerializedName("bowling_style_long")
	@Column(length = 30)
	private String bowlingStyleLong;

	private Integer captain;

	@SerializedName("full_sub")
	private Integer fullSub;

	@SerializedName("full_sub_innings")
	private Integer fullSubInnings;

	@SerializedName("full_sub_name")
	@Column(length = 30)
	private String fullSubName;

	@SerializedName("full_sub_overs")
	private Double fullSubOvers;

	@SerializedName("full_sub_player_id")
	private Integer fullSubPlayerId;

	private Integer keeper;

	@SerializedName("player_primary_role")
	@Column(length = 30)
	private String playerPrimaryRole;

	@SerializedName("player_style_id")
	private Integer playerStyleId;

}