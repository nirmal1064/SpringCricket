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

import com.project.cricket.entity.keys.PartnershipKey;
import com.project.cricket.model.PlayerOrTeam;

import lombok.Data;

@Data
@Entity
@Table(name = "partnership_summary")
@IdClass(PartnershipKey.class)
public class Partnership implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private PlayerOrTeam player1;

	@Transient
	private PlayerOrTeam player2;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Id
	private Integer innings;

	@Id
	private Integer	player1Id;

	@Id
	private Integer	player2Id;

	private Integer outPlayerId;

	private Integer wicketNumber;

	private Integer player1Runs;

	private Integer player1Balls;

	private Integer player2Runs;

	private Integer player2Balls;

	private Integer runs;

	private Integer balls;

	private Double 	overs;
}
