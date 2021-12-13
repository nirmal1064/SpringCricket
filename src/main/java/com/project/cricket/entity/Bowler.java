package com.project.cricket.entity;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.cricket.entity.keys.BowlerKey;
import com.project.cricket.model.PlayerOrTeam;

import lombok.Data;

@Data
@Entity
@Table(name = "bowling_scorecard")
@IdClass(BowlerKey.class)
public class Bowler implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Transient
	private PlayerOrTeam player;

	@Id
	private Integer innings;

	@Id
	private Integer bowlerId;

	private Double 	overs;

	private Integer balls;

	private Integer maidens;

	private Integer conceded;

	private Integer wickets;

	private Double 	economy;

	private Double 	runsPerBall;

	private Integer dots;

	private Integer fours;

	private Integer sixes;

	private Integer wides;

	private Integer noballs;

	private Integer position;
}
