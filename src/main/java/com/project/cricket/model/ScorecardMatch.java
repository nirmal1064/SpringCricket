package com.project.cricket.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.cricket.entity.Debut;
import com.project.cricket.entity.ReplacementPlayer;
import com.project.cricket.entity.ScorecardOfficial;

import lombok.Data;

@Data
public class ScorecardMatch {

	private Integer objectId;

	private Set<ScorecardOfficial> umpires = new LinkedHashSet<>();

	private Set<ScorecardOfficial> tvUmpires = new LinkedHashSet<>();

	private Set<ScorecardOfficial> reserveUmpires = new LinkedHashSet<>();

	private Set<ScorecardOfficial> matchReferees = new LinkedHashSet<>();

	private Set<Debut> debutPlayers = new LinkedHashSet<>();

	private Set<ReplacementPlayer> replacementPlayers = new LinkedHashSet<>();

}
