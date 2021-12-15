package com.project.cricket.model;

import java.util.List;

import com.project.cricket.entity.Debut;
import com.project.cricket.entity.ReplacementPlayer;
import com.project.cricket.entity.ScorecardOfficial;

import lombok.Data;

@Data
public class ScorecardMatch {

	private Integer objectId;

	private List<ScorecardOfficial> umpires;

	private List<ScorecardOfficial> tvUmpires;

	private List<ScorecardOfficial> reserveUmpires;

	private List<ScorecardOfficial> matchReferees;

	private List<Debut> debutPlayers;

	private List<ReplacementPlayer> replacementPlayers;

}
