package com.project.cricket.model;

import static com.project.cricket.utils.Constants.TEXT;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "series")
public class Series implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int matchId;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("class_name")
	@Column(length = 30)
	private String className;

	@SerializedName("content_id")
	private int contentId;

	@Id
	@SerializedName("core_recreation_id")
	private int coreRecreationId;

	@Column(length = 30)
	private String date;

	@SerializedName("end_date")
	@Column(length = 20)
	private String endDate;

	@SerializedName("end_date_raw")
	private LocalDate endDateRaw;

	@SerializedName("final_type_name")
	@Column(length = 30)
	private String finalTypeName;

	@SerializedName("group_title")
	@Column(length = 30)
	private String groupTitle;

	@SerializedName("major_trophy")
	private int majorTrophy;

	@SerializedName("match_number")
	private int matchNumber;

	@SerializedName("match_title")
	@Column(length = 30)
	private String matchTitle;

	@SerializedName("match_type_name")
	@Column(length = 30)
	private String matchTypeName;

	@SerializedName("multiformat_pointstable")
	@Column(length = 10)
	private String multiformatPointstable;

	@SerializedName("number_of_hosts")
	private int numberOfHosts;

	@SerializedName("number_of_matches")
	private int numberOfMatches;

	@SerializedName("number_of_teams")
	private int numberOfTeams;

	@Id
	@SerializedName("object_id")
	private int objectId;

	private int points;

	@SerializedName("primary_series")
	@Column(length = 10)
	private String primarySeries;

	@SerializedName("replayed_date")
	@Column(length = 20)
	private String replayedDate;

	@SerializedName("schedule_note")
	private String scheduleNote;

	@SerializedName("score_module_position")
	private int scoreModulePosition;

	@Column(length = 15)
	private String season;

	@SerializedName("series_abbreviation")
	@Column(length = 15)
	private String seriesAbbreviation;

	@SerializedName("series_category_id")
	private int seriesCategoryId;

	@SerializedName("series_category_name")
	@Column(length = 15)
	private String seriesCategoryName;

	@SerializedName("series_filename")
	@Column(length = 15)
	private String seriesFilename;

	@SerializedName("series_lead_abandoned")
	private int seriesLeadAbandoned;

	@SerializedName("series_lead_cancelled")
	private int seriesLeadCancelled;

	@SerializedName("series_lead_how_won_name")
	@Column(length = 20)
	private String seriesLeadHowWonName;

	@SerializedName("series_lead_lost")
	private int seriesLeadLost;

	@SerializedName("series_lead_result_name")
	@Column(length = 20)
	private String seriesLeadResultName;

	@SerializedName("series_lead_team_name")
	@Column(length = 50)
	private String seriesLeadTeamName;

	@SerializedName("series_lead_total")
	private int seriesLeadTotal;

	@SerializedName("series_lead_type_name")
	@Column(length = 20)
	private String seriesLeadTypeName;

	@SerializedName("series_lead_won")
	private int seriesLeadWon;

	@SerializedName("series_long_description")
	@Column(columnDefinition = TEXT)
	private String seriesLongDescription;

	@SerializedName("series_name")
	private String seriesName;

	@SerializedName("series_short_name")
	@Column(length = 30)
	private String seriesShortName;

	@SerializedName("series_status")
	@Column(length = 30)
	private String seriesStatus;

	@SerializedName("series_type_id")
	private int seriesTypeId;

	@SerializedName("series_type_name")
	@Column(length = 30)
	private String seriesTypeName;

	@SerializedName("short_alternate_name")
	@Column(length = 30)
	private String shortAlternateName;

	@SerializedName("site_id")
	private int siteId;

	@Column(columnDefinition = TEXT)
	private String slug;

	@SerializedName("start_date")
	@Column(length = 30)
	private String startDate;

	@SerializedName("start_date_raw")
	private LocalDate startDateRaw;

	@SerializedName("team1_points")
	private double team1Points;

	@SerializedName("team2_points")
	private double team2Points;

	@Transient
	private List<TeamSeries> teams;

	@SerializedName("tiebreaker_id")
	private int tiebreakerId;

	@SerializedName("tiebreaker_name")
	private String tiebreakerName;

	@SerializedName("trophy_abbreviation")
	@Column(length = 30)
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
	@Column(length = 30)
	private String trophyShortName;

	@SerializedName("url_component")
	@Column(length = 30)
	private String urlComponent;

}
