package com.project.cricket.model;

import java.util.List;

import lombok.Data;

@Data
public class Scorecard {
	private List<ScorecardInnings> innings;
}
