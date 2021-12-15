package com.project.cricket.model;

import java.util.List;

import com.project.cricket.entity.PlayersOfTheMatch;
import com.project.cricket.entity.PlayersOfTheSeries;

import lombok.Data;

@Data
public class SupportInfo {

	private List<PlayersOfTheMatch> playersOfTheMatch;

	private List<PlayersOfTheSeries> playersOfTheSeries;

}
