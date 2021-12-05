package com.project.cricket.model;

import java.util.List;

import lombok.Data;

@Data
public class MatchJson {
	private List<Innings> innings;
	private Match match;
	private List<Official> official;
	private List<Team> team;
}