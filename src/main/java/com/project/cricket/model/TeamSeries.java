package com.project.cricket.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSeries implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("host_team")
	private int hostTeam;

	@SerializedName("object_id")
	private int objectId;

	@SerializedName("primary_team")
	private int primaryTeam;

	@SerializedName("series_id")
	private String seriesId;

	@SerializedName("series_result_name")
	private String seriesResultName;

	@SerializedName("site_id")
	private int siteId;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_filename")
	private String teamFilename;

	@SerializedName("team_id")
	private int teamId;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;

	@SerializedName("url_component")
	private String urlComponent;

}