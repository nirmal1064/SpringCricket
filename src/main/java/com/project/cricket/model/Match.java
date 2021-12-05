package com.project.cricket.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "match_summary")
public class Match {

	@Id
	@Column(name = "match_id")
	private Integer matchId;

	@SerializedName("actual_days")
	private int actualDays;

	private int adjusted;

	private int amount;

	@SerializedName("amount_balls")
	private int amountBalls;

	@SerializedName("amount_name")
	private String amountName;

	@SerializedName("amount_type")
	private int amountType;

	@SerializedName("away_team_id")
	private int awayTeamId;

	@SerializedName("ballbyball_source")
	private String ballbyballSource;

	@SerializedName("batting_first_team_id")
	private int battingFirstTeamId;

	@SerializedName("bitly_hash")
	private String bitlyHash;

	private int bpo;

	@SerializedName("cancelled_match")
	private String cancelledMatch;

	@SerializedName("cms_match_title")
	private String cmsMatchTitle;

	@SerializedName("commentary_source")
	private String commentarySource;

	@SerializedName("continent_id")
	private int continentId;

	@SerializedName("continent_name")
	private String continentName;

	@SerializedName("country_abbreviation")
	private String countryAbbreviation;

	@SerializedName("country_filename")
	private String countryFilename;

	@SerializedName("country_id")
	private int countryId;

	@SerializedName("country_name")
	private String countryName;

	private String date;

	@SerializedName("date_string")
	private String dateString;

	@SerializedName("days_extended")
	private int daysExtended;

	@SerializedName("end_date")
	private String endDate;

	@SerializedName("end_date_raw")
	private String endDateRaw;

	private int floodlit;

	@SerializedName("floodlit_name")
	private String floodlitName;

	private int followon;

	@SerializedName("general_class_card")
	private String generalClassCard;

	@SerializedName("general_class_id")
	private int generalClassId;

	@SerializedName("general_class_name")
	private String generalClassName;

	@SerializedName("general_number")
	private String generalNumber;

	@SerializedName("general_valid")
	private int generalValid;

	@SerializedName("gmt_difference")
	private String gmtDifference;

	@SerializedName("ground_id")
	private int groundId;

	@SerializedName("ground_latitude")
	private String groundLatitude;

	@SerializedName("ground_longitude")
	private String groundLongitude;

	@SerializedName("ground_name")
	private String groundName;

	@SerializedName("ground_object_id")
	private int groundObjectId;

	@SerializedName("ground_small_name")
	private String groundSmallName;

	@SerializedName("hawkeye_source")
	private String hawkeyeSource;

	@SerializedName("head2head_source")
	private String head2headSource;

	@SerializedName("home_team_id")
	private int homeTeamId;

	@SerializedName("hours_string")
	private String hoursString;

	@SerializedName("international_class_card")
	private String internationalClassCard;

	@SerializedName("international_class_id")
	private int internationalClassId;

	@SerializedName("international_class_name")
	private String internationalClassName;

	@SerializedName("international_number")
	private int internationalNumber;

	@SerializedName("international_valid")
	private int internationalValid;

	@SerializedName("legacy_url")
	private String legacyUrl;

	@SerializedName("live_commentator")
	private String liveCommentator;

	@SerializedName("live_companion")
	private String liveCompanion;

	@SerializedName("live_day_number")
	private int liveDayNumber;

	@SerializedName("live_innings_number")
	private int liveInningsNumber;

	@SerializedName("live_match")
	private String liveMatch;

	@SerializedName("live_note")
	private String liveNote;

	@SerializedName("live_overs_remaining")
	private double liveOversRemaining;

	@SerializedName("live_overs_unique")
	private double liveOversUnique;

	@SerializedName("live_scorer")
	private String liveScorer;

	@SerializedName("live_session_number")
	private int liveSessionNumber;

	@SerializedName("live_state")
	private String liveState;

	@SerializedName("match_clock")
	private String matchClock;

	@SerializedName("match_day_countdown")
	private int matchDayCountdown;

	@SerializedName("match_minute_countdown")
	private int matchMinuteCountdown;

	@SerializedName("match_path")
	private String matchPath;

	@SerializedName("match_status")
	private String matchStatus;

	@SerializedName("neutral_match")
	private int neutralMatch;

	@SerializedName("next_datetime_gmt")
	private LocalDateTime nextDatetimeGmt;

	@SerializedName("next_datetime_local")
	private LocalDateTime nextDatetimeLocal;

	@SerializedName("player_rating")
	private String playerRating;

	@SerializedName("present_date_gmt")
	private LocalDate presentDateGmt;

	@SerializedName("present_date_local")
	private LocalDate presentDateLocal;

	@SerializedName("present_datetime_gmt")
	private LocalDateTime presentDatetimeGmt;

	@SerializedName("present_datetime_local")
	private LocalDateTime presentDatetimeLocal;

	@SerializedName("present_time_gmt")
	private LocalTime presentTimeGmt;

	@SerializedName("present_time_local")
	private LocalTime presentTimeLocal;

	@SerializedName("rain_rule")
	private int rainRule;

	@SerializedName("rain_rule_name")
	private String rainRuleName;

	@SerializedName("rating_promo")
	private String ratingPromo;

	private int reduced;

	@SerializedName("reserve_days_used")
	private int reserveDaysUsed;

	private int result;

	@SerializedName("result_name")
	private String resultName;

	@SerializedName("result_short_name")
	private String resultShortName;

	@SerializedName("schedule_note")
	private String scheduleNote;

	@SerializedName("scheduled_days")
	private int scheduledDays;

	@SerializedName("scheduled_innings")
	private int scheduledInnings;

	@SerializedName("scheduled_overs")
	private double scheduledOvers;

	@SerializedName("scorecard_source")
	private String scorecardSource;

	@SerializedName("scribble_id")
	private int scribbleId;

	private String season;

	@SerializedName("site_id")
	private int siteId;

	@SerializedName("site_name")
	private String siteName;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("start_date_gmt_offset")
	private String startDateGmtOffset;

	@SerializedName("start_date_raw")
	private LocalDate startDateRaw;

	@SerializedName("start_datetime_gmt")
	private LocalDateTime startDatetimeGmt;

	@SerializedName("start_datetime_gmt_raw")
	private LocalDateTime startDatetimeGmtRaw;

	@SerializedName("start_datetime_local")
	private LocalDateTime startDatetimeLocal;

	@SerializedName("start_time_gmt")
	private String startTimeGmt;

	@SerializedName("start_time_local")
	private String startTimeLocal;

	@SerializedName("sub_class_id")
	private int subClassId;

	@SerializedName("team1_abbreviation")
	private String team1Abbreviation;

	@SerializedName("team1_class_id")
	private String team1ClassId;

	@SerializedName("team1_country_id")
	private String team1CountryId;

	@SerializedName("team1_filename")
	private String team1Filename;

	@SerializedName("team1_id")
	private int team1Id;

	@SerializedName("team1_logo_alt_id")
	private String team1LogoAltId;

	@SerializedName("team1_logo_espncdn")
	private String team1LogoEspncdn;

	@SerializedName("team1_logo_object_id")
	private int team1LogoObjectId;

	@SerializedName("team1_name")
	private String team1Name;

	@SerializedName("team1_object_id")
	private String team1ObjectId;

	@SerializedName("team1_short_name")
	private String team1ShortName;

	@SerializedName("team2_abbreviation")
	private String team2Abbreviation;

	@SerializedName("team2_class_id")
	private int team2ClassId;

	@SerializedName("team2_country_id")
	private int team2CountryId;

	@SerializedName("team2_filename")
	private String team2Filename;

	@SerializedName("team2_id")
	private int team2Id;

	@SerializedName("team2_logo_alt_id")
	private String team2LogoAltId;

	@SerializedName("team2_logo_espncdn")
	private String team2LogoEspncdn;

	@SerializedName("team2_logo_object_id")
	private int team2LogoObjectId;

	@SerializedName("team2_name")
	private String team2Name;

	@SerializedName("team2_object_id")
	private int team2ObjectId;

	@SerializedName("team2_short_name")
	private String team2ShortName;

	@SerializedName("tiebreaker_name")
	private String tiebreakerName;

	@SerializedName("tiebreaker_team_id")
	private int tiebreakerTeamId;

	@SerializedName("tiebreaker_type")
	private int tiebreakerType;

	@SerializedName("time_zone")
	private String timeZone;

	@SerializedName("toss_choice_team_id")
	private int tossChoiceTeamId;

	@SerializedName("toss_decision")
	private String tossDecision;

	@SerializedName("toss_decision_name")
	private String tossDecisionName;

	@SerializedName("toss_winner_team_id")
	private int tossWinnerTeamId;

	@SerializedName("town_aka")
	private String townAka;

	@SerializedName("town_area")
	private String townArea;

	@SerializedName("town_id")
	private int townId;

	@SerializedName("town_name")
	private String townName;

	@SerializedName("tz_short_name")
	private String tzShortName;

	@SerializedName("url_component")
	private String urlComponent;

	@SerializedName("watch_espn_id")
	private int watchEspnId;

	@SerializedName("weather_location_code")
	private String weatherLocationCode;

	@SerializedName("winner_team_id")
	private int winnerTeamId;

}