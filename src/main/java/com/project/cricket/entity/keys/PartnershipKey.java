package com.project.cricket.entity.keys;

import java.io.Serializable;

import com.project.cricket.entity.Match;

import lombok.Data;

@Data
public class PartnershipKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Match match;
	private Integer innings;
	private Integer player1Id;
	private Integer player2Id;
	private Integer wicketNumber;

}
