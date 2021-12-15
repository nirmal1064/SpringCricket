package com.project.cricket.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.project.cricket.entity.PlayersOfTheMatch;
import com.project.cricket.entity.PlayersOfTheSeries;

import lombok.Data;

@Data
public class SupportInfo {

	private Set<PlayersOfTheMatch> playersOfTheMatch = new LinkedHashSet<>();

	private Set<PlayersOfTheSeries> playersOfTheSeries = new LinkedHashSet<>();

}
