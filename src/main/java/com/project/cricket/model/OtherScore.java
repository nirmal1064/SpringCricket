package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherScore {

	@SerializedName("object_id")
	private int objectId;

	private String result;

	@SerializedName("start_time")
	private String startTime;

	@SerializedName("team1_desc")
	private String team1Desc;

	@SerializedName("team1_name")
	private String team1Name;

	@SerializedName("team2_desc")
	private String team2Desc;

	@SerializedName("team2_name")
	private String team2Name;

	private String url;

}
