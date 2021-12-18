package com.project.cricket.model;

import java.util.ArrayList;
import java.util.List;

import com.project.cricket.entity.Debut;
import com.project.cricket.entity.ReplacementPlayer;

import lombok.Data;

@Data
public class ScorecardMatch {

	private Integer objectId;

//	private List<ScorecardOfficial> umpires = new ArrayList<>();
//
//	private List<ScorecardOfficial> tvUmpires = new ArrayList<>();
//
//	private List<ScorecardOfficial> reserveUmpires = new ArrayList<>();
//
//	private List<ScorecardOfficial> matchReferees = new ArrayList<>();

	private List<Debut> debutPlayers = new ArrayList<>();

	private List<ReplacementPlayer> replacementPlayers = new ArrayList<>();

}
