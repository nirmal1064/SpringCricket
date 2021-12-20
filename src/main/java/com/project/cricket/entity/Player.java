package com.project.cricket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.superclass.Person;

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

	@Column(length = 30)
	private String teamName;

	@SerializedName("batting_style")
	@Column(length = 50)
	private String battingStyle;

	@SerializedName("batting_style_long")
	private String battingStyleLong;

	@SerializedName("bowling_style")
	@Column(length = 50)
	private String bowlingStyle;

	@SerializedName("bowling_style_long")
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
		result = prime * result;
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
		return true;
	}

}