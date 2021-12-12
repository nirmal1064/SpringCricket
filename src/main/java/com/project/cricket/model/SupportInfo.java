package com.project.cricket.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SupportInfo {
	private List<PlayerAward> playersOfTheMatch = new ArrayList<>();
	private List<PlayerAward> playersOfTheSeries = new ArrayList<>();
}
