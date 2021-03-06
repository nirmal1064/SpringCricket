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

import com.project.cricket.entity.keys.BatsmanKey;
import com.project.cricket.model.PlayerOrTeam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"match", "innings", "batsmanId"})
@Entity
@Table(name = "batting_scorecard")
@IdClass(BatsmanKey.class)
public class Batsman implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Transient
	private PlayerOrTeam player;

	@Id
	private Integer innings;

	private Integer position;

	private String 	playerRoleType;

	private String 	battedType;

	@Id
	private Integer batsmanId;

	private Integer runs;

	private Integer balls;

	private Integer minutes;

	private Integer fours;

	private Integer sixes;

	private Double 	strikerate;

	private Boolean isOut;

	private Integer dismissalType;
}
