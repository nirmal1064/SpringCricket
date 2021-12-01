package com.project.cricket.model;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Series {

	@SerializedName("class_id")
	private int classId;

	@SerializedName("class_name")
	private String className;

	@SerializedName("content_id")
	private String contentId;

	@SerializedName("core_recreation_id")
	private int coreRecreationId;

	private String date;

	@SerializedName("end_date")
	private String endDate;

	@SerializedName("end_date_raw")
	private LocalDate endDateRaw;

	@SerializedName("final_type_name")
	private String finalTypeName;

	@SerializedName("group_title")
	private String groupTitle;

	@SerializedName("major_trophy")
	private int majorTrophy;

	@SerializedName("match_number")
	private int matchNumber;

	@SerializedName("match_title")
	private String matchTitle;

	@SerializedName("match_type_name")
	private Object matchTypeName;

	@SerializedName("multiformat_pointstable")
	private String multiformatPointstable;

	@SerializedName("number_of_hosts")
	private int numberOfHosts;

	@SerializedName("number_of_matches")
	private int numberOfMatches;

	@SerializedName("number_of_teams")
	private int numberOfTeams;

	@SerializedName("object_id")
	private int objectId;

	private int points;

	@SerializedName("primary_series")
	private String primarySeries;

	@SerializedName("replayed_date")
	private String replayedDate;

	@SerializedName("schedule_note")
	private String scheduleNote;

	@SerializedName("score_module_position")
	private int scoreModulePosition;

	private String season;

	@SerializedName("series_abbreviation")
	private String seriesAbbreviation;

	@SerializedName("series_category_id")
	private int seriesCategoryId;

	@SerializedName("series_category_name")
	private String seriesCategoryName;

	@SerializedName("series_filename")
	private String seriesFilename;

	@SerializedName("series_lead_abandoned")
	private int seriesLeadAbandoned;

	@SerializedName("series_lead_cancelled")
	private int seriesLeadCancelled;

	@SerializedName("series_lead_how_won_name")
	private Object seriesLeadHowWonName;

	@SerializedName("series_lead_lost")
	private int seriesLeadLost;

	@SerializedName("series_lead_result_name")
	private Object seriesLeadResultName;

	@SerializedName("series_lead_team_name")
	private Object seriesLeadTeamName;

	@SerializedName("series_lead_total")
	private int seriesLeadTotal;

	@SerializedName("series_lead_type_name")
	private Object seriesLeadTypeName;

	@SerializedName("series_lead_won")
	private int seriesLeadWon;

	@SerializedName("series_long_description")
	private String seriesLongDescription;

	@SerializedName("series_name")
	private String seriesName;

	@SerializedName("series_short_name")
	private String seriesShortName;

	@SerializedName("series_status")
	private String seriesStatus;

	@SerializedName("series_type_id")
	private int seriesTypeId;

	@SerializedName("series_type_name")
	private String seriesTypeName;

	@SerializedName("short_alternate_name")
	private String shortAlternateName;

	@SerializedName("site_id")
	private int siteId;

	private String slug;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("start_date_raw")
	private LocalDate startDateRaw;

	@SerializedName("team1_points")
	private String team1Points;

	@SerializedName("team2_points")
	private String team2Points;

	private List<TeamSeries> teams;

	@SerializedName("tiebreaker_id")
	private int tiebreakerId;

	@SerializedName("tiebreaker_name")
	private Object tiebreakerName;

	@SerializedName("trophy_abbreviation")
	private String trophyAbbreviation;

	@SerializedName("trophy_class_id")
	private int trophyClassId;

	@SerializedName("trophy_country_id")
	private int trophyCountryId;

	@SerializedName("trophy_id")
	private int trophyId;

	@SerializedName("trophy_name")
	private String trophyName;

	@SerializedName("trophy_short_name")
	private String trophyShortName;

	@SerializedName("url_component")
	private String urlComponent;

}
