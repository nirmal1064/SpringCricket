package com.project.cricket.model;

import lombok.Data;

/**
 * Basic class that can be used to represent a match and player
 * @author Nirmal
 *
 */
@Data
public abstract class MatchPerson {
	private ScorecardPlayer player;
	private Integer matchId;
	private Integer objectId;
}
