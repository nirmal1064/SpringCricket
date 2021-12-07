package com.project.cricket.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "squad_summary")
public class Player extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "match_id")
	private Integer matchId;

	@Id
	@Column(name = "team_id")
	private Integer teamId;

	@Column(name = "team_name", length = 30)
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

	private int captain;

	private int keeper;

	@SerializedName("player_primary_role")
	@Column(length = 30)
	private String playerPrimaryRole;

	@SerializedName("player_style_id")
	private int playerStyleId;

}