package com.project.cricket.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherScores {

	private List<OtherScore> domestic;

	private List<OtherScore> international;

	private List<OtherScore> others;

}
