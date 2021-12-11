package com.project.cricket.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "squad_summary")
@ToString
public class Player extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY, cascade = MERGE)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(match, teamId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(match, other.match) && Objects.equals(teamId, other.teamId);
	}

}