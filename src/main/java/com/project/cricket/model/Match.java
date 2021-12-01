package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

	@SerializedName("actual_days")
	private String actualDays;

	private String adjusted;

	private String amount;

	@SerializedName("amount_balls")
	private String amountBalls;

	@SerializedName("amount_name")
	private String amountName;

	@SerializedName("amount_type")
	private String amountType;

	@SerializedName("away_team_id")
	private String awayTeamId;

	@SerializedName("ballbyball_source")
	private String ballbyballSource;

	@SerializedName("batting_first_team_id")
	private String battingFirstTeamId;

	@SerializedName("bitly_hash")
	private String bitlyHash;

	private String bpo;

	@SerializedName("cancelled_match")
	private String cancelledMatch;

	@SerializedName("cms_match_title")
	private String cmsMatchTitle;

	@SerializedName("commentary_source")
	private String commentarySource;

	@SerializedName("continent_id")
	private String continentId;

	@SerializedName("continent_name")
	private String continentName;

	@SerializedName("country_abbreviation")
	private String countryAbbreviation;

	@SerializedName("country_filename")
	private String countryFilename;

	@SerializedName("country_id")
	private String countryId;

	@SerializedName("country_name")
	private String countryName;

	private String date;

	@SerializedName("date_string")
	private String dateString;

	@SerializedName("days_extended")
	private String daysExtended;

	@SerializedName("end_date")
	private String endDate;

	@SerializedName("end_date_raw")
	private String endDateRaw;

	private String floodlit;

	@SerializedName("floodlit_name")
	private String floodlitName;

	private String followon;

	@SerializedName("general_class_card")
	private String generalClassCard;

	@SerializedName("general_class_id")
	private String generalClassId;

	@SerializedName("general_class_name")
	private String generalClassName;

	@SerializedName("general_number")
	private String generalNumber;

	@SerializedName("general_valid")
	private String generalValid;

	@SerializedName("gmt_difference")
	private String gmtDifference;

	@SerializedName("ground_id")
	private String groundId;

	@SerializedName("ground_latitude")
	private String groundLatitude;

	@SerializedName("ground_longitude")
	private String groundLongitude;

	@SerializedName("ground_name")
	private String groundName;

	@SerializedName("ground_object_id")
	private String groundObjectId;

	@SerializedName("ground_small_name")
	private String groundSmallName;

	@SerializedName("hawkeye_source")
	private String hawkeyeSource;

	@SerializedName("head2head_source")
	private String head2headSource;

	@SerializedName("home_team_id")
	private String homeTeamId;

	@SerializedName("hours_string")
	private String hoursString;

	@SerializedName("international_class_card")
	private String internationalClassCard;

	@SerializedName("international_class_id")
	private String internationalClassId;

	@SerializedName("international_class_name")
	private String internationalClassName;

	@SerializedName("international_number")
	private String internationalNumber;

	@SerializedName("international_valid")
	private String internationalValid;

	@SerializedName("legacy_url")
	private String legacyUrl;

	@SerializedName("live_commentator")
	private String liveCommentator;

	@SerializedName("live_companion")
	private String liveCompanion;

	@SerializedName("live_day_number")
	private String liveDayNumber;

	@SerializedName("live_innings_number")
	private String liveInningsNumber;

	@SerializedName("live_match")
	private String liveMatch;

	@SerializedName("live_note")
	private String liveNote;

	@SerializedName("live_overs_remaining")
	private String liveOversRemaining;

	@SerializedName("live_overs_unique")
	private String liveOversUnique;

	@SerializedName("live_scorer")
	private String liveScorer;

	@SerializedName("live_session_number")
	private String liveSessionNumber;

	@SerializedName("live_state")
	private String liveState;

	@SerializedName("match_clock")
	private String matchClock;

	@SerializedName("match_day_countdown")
	private String matchDayCountdown;

	@SerializedName("match_minute_countdown")
	private String matchMinuteCountdown;

	@SerializedName("match_path")
	private String matchPath;

	@SerializedName("match_status")
	private String matchStatus;

	@SerializedName("neutral_match")
	private String neutralMatch;

	@SerializedName("next_datetime_gmt")
	private String nextDatetimeGmt;

	@SerializedName("next_datetime_local")
	private String nextDatetimeLocal;

	@SerializedName("player_rating")
	private String playerRating;

	@SerializedName("present_date_gmt")
	private String presentDateGmt;

	@SerializedName("present_date_local")
	private String presentDateLocal;

	@SerializedName("present_datetime_gmt")
	private String presentDatetimeGmt;

	@SerializedName("present_datetime_local")
	private String presentDatetimeLocal;

	@SerializedName("present_time_gmt")
	private String presentTimeGmt;

	@SerializedName("present_time_local")
	private String presentTimeLocal;

	@SerializedName("rain_rule")
	private String rainRule;

	@SerializedName("rain_rule_name")
	private String rainRuleName;

	@SerializedName("rating_promo")
	private String ratingPromo;

	private String reduced;

	@SerializedName("reserve_days_used")
	private String reserveDaysUsed;

	private String result;

	@SerializedName("result_name")
	private String resultName;

	@SerializedName("result_short_name")
	private String resultShortName;

	@SerializedName("schedule_note")
	private String scheduleNote;

	@SerializedName("scheduled_days")
	private String scheduledDays;

	@SerializedName("scheduled_innings")
	private String scheduledInnings;

	@SerializedName("scheduled_overs")
	private String scheduledOvers;

	@SerializedName("scorecard_source")
	private String scorecardSource;

	@SerializedName("scribble_id")
	private String scribbleId;

	private String season;

	@SerializedName("site_id")
	private String siteId;

	@SerializedName("site_name")
	private String siteName;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("start_date_gmt_offset")
	private String startDateGmtOffset;

	@SerializedName("start_date_raw")
	private String startDateRaw;

	@SerializedName("start_datetime_gmt")
	private String startDatetimeGmt;

	@SerializedName("start_datetime_gmt_raw")
	private String startDatetimeGmtRaw;

	@SerializedName("start_datetime_local")
	private String startDatetimeLocal;

	@SerializedName("start_time_gmt")
	private String startTimeGmt;

	@SerializedName("start_time_local")
	private String startTimeLocal;

	@SerializedName("sub_class_id")
	private String subClassId;

	@SerializedName("team1_abbreviation")
	private String team1Abbreviation;

	@SerializedName("team1_class_id")
	private String team1ClassId;

	@SerializedName("team1_country_id")
	private String team1CountryId;

	@SerializedName("team1_filename")
	private String team1Filename;

	@SerializedName("team1_id")
	private String team1Id;

	@SerializedName("team1_logo_alt_id")
	private String team1LogoAltId;

	@SerializedName("team1_logo_espncdn")
	private String team1LogoEspncdn;

	@SerializedName("team1_logo_object_id")
	private String team1LogoObjectId;

	@SerializedName("team1_name")
	private String team1Name;

	@SerializedName("team1_object_id")
	private String team1ObjectId;

	@SerializedName("team1_short_name")
	private String team1ShortName;

	@SerializedName("team2_abbreviation")
	private String team2Abbreviation;

	@SerializedName("team2_class_id")
	private String team2ClassId;

	@SerializedName("team2_country_id")
	private String team2CountryId;

	@SerializedName("team2_filename")
	private String team2Filename;

	@SerializedName("team2_id")
	private String team2Id;

	@SerializedName("team2_logo_alt_id")
	private String team2LogoAltId;

	@SerializedName("team2_logo_espncdn")
	private String team2LogoEspncdn;

	@SerializedName("team2_logo_object_id")
	private String team2LogoObjectId;

	@SerializedName("team2_name")
	private String team2Name;

	@SerializedName("team2_object_id")
	private String team2ObjectId;

	@SerializedName("team2_short_name")
	private String team2ShortName;

	@SerializedName("tiebreaker_name")
	private String tiebreakerName;

	@SerializedName("tiebreaker_team_id")
	private String tiebreakerTeamId;

	@SerializedName("tiebreaker_type")
	private String tiebreakerType;

	@SerializedName("time_zone")
	private String timeZone;

	@SerializedName("toss_choice_team_id")
	private String tossChoiceTeamId;

	@SerializedName("toss_decision")
	private String tossDecision;

	@SerializedName("toss_decision_name")
	private String tossDecisionName;

	@SerializedName("toss_winner_team_id")
	private String tossWinnerTeamId;

	@SerializedName("town_aka")
	private String townAka;

	@SerializedName("town_area")
	private String townArea;

	@SerializedName("town_id")
	private String townId;

	@SerializedName("town_name")
	private String townName;

	@SerializedName("tz_short_name")
	private String tzShortName;

	@SerializedName("url_component")
	private String urlComponent;

	@SerializedName("watch_espn_id")
	private String watchEspnId;

	@SerializedName("weather_location_code")
	private String weatherLocationCode;

	@SerializedName("winner_team_id")
	private String winnerTeamId;

}