package com.project.cricket.model;

import java.util.ArrayList;
import java.util.List;

import com.project.cricket.entity.PlayersOfTheMatch;
import com.project.cricket.entity.PlayersOfTheSeries;

import lombok.Data;

@Data
public class SupportInfo {
	private List<PlayersOfTheMatch> playersOfTheMatch = new ArrayList<>();
	private List<PlayersOfTheSeries> playersOfTheSeries = new ArrayList<>();
}
