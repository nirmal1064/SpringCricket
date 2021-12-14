package com.project.cricket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScorecard {

	private Integer matchId;

	private ScorecardMatch match;

	private Scorecard scorecard;

	private SupportInfo supportInfo;

}
