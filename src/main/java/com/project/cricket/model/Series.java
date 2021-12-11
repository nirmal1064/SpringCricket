package com.project.cricket.model;

import static com.project.cricket.utils.Constants.TEXT;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.model.keys.SeriesKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "series")
@ToString
@IdClass(SeriesKey.class)
@EqualsAndHashCode
public class Series implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@SerializedName("class_id")
	private Integer classId;

	@SerializedName("class_name")
	@Column(length = 30)
	private String className;

	@SerializedName("content_id")
	private Integer contentId;

	@Id
	@SerializedName("core_recreation_id")
	private Integer coreRecreationId;

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
	private Integer majorTrophy;

	@SerializedName("match_number")
	private Integer matchNumber;

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
	private Integer numberOfHosts;

	@SerializedName("number_of_matches")
	private Integer numberOfMatches;

	@SerializedName("number_of_teams")
	private Integer numberOfTeams;

	@Id
	@SerializedName("object_id")
	private Integer objectId;

	private Integer points;

	@SerializedName("primary_series")
	@Column(length = 10)
	private String primarySeries;

	@SerializedName("replayed_date")
	@Column(length = 20)
	private String replayedDate;

	@SerializedName("schedule_note")
	private String scheduleNote;

	@SerializedName("score_module_position")
	private Integer scoreModulePosition;

	@Column(length = 15)
	private String season;

	@SerializedName("series_abbreviation")
	@Column(length = 15)
	private String seriesAbbreviation;

	@SerializedName("series_category_id")
	private Integer seriesCategoryId;

	@SerializedName("series_category_name")
	@Column(length = 15)
	private String seriesCategoryName;

	@SerializedName("series_filename")
	@Column(length = 15)
	private String seriesFilename;

	@SerializedName("series_lead_abandoned")
	private Integer seriesLeadAbandoned;

	@SerializedName("series_lead_cancelled")
	private Integer seriesLeadCancelled;

	@SerializedName("series_lead_how_won_name")
	@Column(length = 20)
	private String seriesLeadHowWonName;

	@SerializedName("series_lead_lost")
	private Integer seriesLeadLost;

	@SerializedName("series_lead_result_name")
	@Column(length = 20)
	private String seriesLeadResultName;

	@SerializedName("series_lead_team_name")
	@Column(length = 50)
	private String seriesLeadTeamName;

	@SerializedName("series_lead_total")
	private Integer seriesLeadTotal;

	@SerializedName("series_lead_type_name")
	@Column(length = 20)
	private String seriesLeadTypeName;

	@SerializedName("series_lead_won")
	private Integer seriesLeadWon;

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
	private Integer seriesTypeId;

	@SerializedName("series_type_name")
	@Column(length = 30)
	private String seriesTypeName;

	@SerializedName("short_alternate_name")
	@Column(length = 30)
	private String shortAlternateName;

	@SerializedName("site_id")
	private Integer siteId;

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
	private Integer tiebreakerId;

	@SerializedName("tiebreaker_name")
	private String tiebreakerName;

	@SerializedName("trophy_abbreviation")
	@Column(length = 30)
	private String trophyAbbreviation;

	@SerializedName("trophy_class_id")
	private Integer trophyClassId;

	@SerializedName("trophy_country_id")
	private Integer trophyCountryId;

	@SerializedName("trophy_id")
	private Integer trophyId;

	@SerializedName("trophy_name")
	private String trophyName;

	@SerializedName("trophy_short_name")
	@Column(length = 30)
	private String trophyShortName;

	@SerializedName("url_component")
	@Column(length = 30)
	private String urlComponent;

}
