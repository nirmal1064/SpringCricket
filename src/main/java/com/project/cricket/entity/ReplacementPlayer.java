package com.project.cricket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.superclass.MatchPerson;
import com.project.cricket.model.PlayerOrTeam;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "replacement_summary")
public class ReplacementPlayer extends MatchPerson {

	private static final long serialVersionUID = 1L;

	@Transient
	private PlayerOrTeam team;

	@Transient
	private PlayerOrTeam replacingPlayer;

	@Column(name = "innings")
	private Integer inning;

	private Integer teamId;

	private Integer replacingPlayerId;

	@SerializedName(value = "over")
	private Double overs;

}
