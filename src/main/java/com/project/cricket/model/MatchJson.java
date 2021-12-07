package com.project.cricket.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchJson {
	private String description;
	private List<Innings> innings;
	private Match match;
	private List<Official> official;
	private List<Series> series;
	private List<Team> team;
}
