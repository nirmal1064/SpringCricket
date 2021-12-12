package com.project.cricket.model;

import static com.project.cricket.utils.Constants.TEXT;
import static javax.persistence.CascadeType.MERGE;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "match_summary")
@EqualsAndHashCode
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "match_id")
	private Integer matchId;

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE)
	private List<Innings> innings = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Player> player = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Series> series = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Official> official = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Batsman> batsmen = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Bowler> bowlers = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Partnership> partnerships = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Fow> fows = new ArrayList<>();

	@Expose(serialize = false, deserialize = false)
	@OneToMany(mappedBy = "match", cascade = MERGE, orphanRemoval = true)
	private List<Debut> debuts = new ArrayList<>();

	@SerializedName("actual_days")
	private Integer actualDays;

	private Integer adjusted;

	private Integer amount;

	@SerializedName("amount_balls")
	private Integer amountBalls;

	@SerializedName("amount_name")
	@Column(length = 30)
	private String amountName;

	@SerializedName("amount_type")
	private Integer amountType;

	@SerializedName("away_team_id")
	private Integer awayTeamId;

	@SerializedName("ballbyball_source")
	@Column(length = 20)
	private String ballbyballSource;

	@SerializedName("batting_first_team_id")
	private Integer battingFirstTeamId;

	@SerializedName("bitly_hash")
	@Column(length = 10)
	private String bitlyHash;

	private Integer bpo;

	@SerializedName("cancelled_match")
	@Column(length = 10)
	private String cancelledMatch;

	@SerializedName("cms_match_title")
	@Column(length = 30)
	private String cmsMatchTitle;

	@SerializedName("commentary_source")
	@Column(length = 30)
	private String commentarySource;

	@SerializedName("continent_id")
	private Integer continentId;

	@SerializedName("continent_name")
	@Column(length = 30)
	private String continentName;

	@SerializedName("country_abbreviation")
	@Column(length = 30)
	private String countryAbbreviation;

	@SerializedName("country_filename")
	@Column(length = 30)
	private String countryFilename;

	@SerializedName("country_id")
	private Integer countryId;

	@SerializedName("country_name")
	private String countryName;

	@Column(length = 30)
	private String date;

	@SerializedName("date_string")
	@Column(length = 40)
	private String dateString;

	@SerializedName("days_extended")
	private Integer daysExtended;

	@SerializedName("end_date")
	@Column(length = 40)
	private String endDate;

	@SerializedName("end_date_raw")
	private LocalDate endDateRaw;

	private Integer floodlit;

	@SerializedName("floodlit_name")
	@Column(length = 30)
	private String floodlitName;

	private Integer followon;

	@SerializedName("general_class_card")
	@Column(length = 20)
	private String generalClassCard;

	@SerializedName("general_class_id")
	private Integer generalClassId;

	@SerializedName("general_class_name")
	@Column(length = 30)
	private String generalClassName;

	@SerializedName("general_number")
	private String generalNumber;

	@SerializedName("general_valid")
	private Integer generalValid;

	@SerializedName("gmt_difference")
	@Column(length = 10)
	private String gmtDifference;

	@SerializedName("ground_id")
	private Integer groundId;

	@SerializedName("ground_latitude")
	@Column(length = 20)
	private String groundLatitude;

	@SerializedName("ground_longitude")
	@Column(length = 20)
	private String groundLongitude;

	@SerializedName("ground_name")
	private String groundName;

	@SerializedName("ground_object_id")
	private Integer groundObjectId;

	@SerializedName("ground_small_name")
	@Column(length = 50)
	private String groundSmallName;

	@SerializedName("hawkeye_source")
	@Column(length = 30)
	private String hawkeyeSource;

	@SerializedName("head2head_source")
	@Column(length = 30)
	private String head2headSource;

	@SerializedName("home_team_id")
	private Integer homeTeamId;

	@SerializedName("hours_string")
	@Column(columnDefinition = TEXT)
	private String hoursString;

	@SerializedName("international_class_card")
	@Column(length = 20)
	private String internationalClassCard;

	@SerializedName("international_class_id")
	private Integer internationalClassId;

	@SerializedName("international_class_name")
	@Column(length = 30)
	private String internationalClassName;

	@SerializedName("international_number")
	private Integer internationalNumber;

	@SerializedName("international_valid")
	private Integer internationalValid;

	@SerializedName("legacy_url")
	@Column(columnDefinition = TEXT)
	private String legacyUrl;

	@SerializedName("live_commentator")
	@Column(length = 30)
	private String liveCommentator;

	@SerializedName("live_companion")
	@Column(length = 30)
	private String liveCompanion;

	@SerializedName("live_day_number")
	private Integer liveDayNumber;

	@SerializedName("live_innings_number")
	private Integer liveInningsNumber;

	@SerializedName("live_match")
	@Column(length = 10)
	private String liveMatch;

	@SerializedName("live_note")
	@Column(length = 40)
	private String liveNote;

	@SerializedName("live_overs_remaining")
	private double liveOversRemaining;

	@SerializedName("live_overs_unique")
	private double liveOversUnique;

	@SerializedName("live_scorer")
	@Column(length = 40)
	private String liveScorer;

	@SerializedName("live_session_number")
	private Integer liveSessionNumber;

	@SerializedName("live_state")
	@Column(length = 40)
	private String liveState;

	@SerializedName("match_clock")
	@Column(length = 40)
	private String matchClock;

	@SerializedName("match_day_countdown")
	private Integer matchDayCountdown;

	@SerializedName("match_minute_countdown")
	private Integer matchMinuteCountdown;

	@SerializedName("match_path")
	@Column(columnDefinition = TEXT)
	private String matchPath;

	@SerializedName("match_status")
	@Column(length = 15)
	private String matchStatus;

	@SerializedName("neutral_match")
	private Integer neutralMatch;

	@SerializedName("next_datetime_gmt")
	private LocalDateTime nextDatetimeGmt;

	@SerializedName("next_datetime_local")
	private LocalDateTime nextDatetimeLocal;

	@SerializedName("player_rating")
	@Column(length = 10)
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
	private Integer rainRule;

	@SerializedName("rain_rule_name")
	private String rainRuleName;

	@SerializedName("rating_promo")
	private String ratingPromo;

	private Integer reduced;

	@SerializedName("reserve_days_used")
	private Integer reserveDaysUsed;

	private Integer result;

	@SerializedName("result_name")
	private String resultName;

	@SerializedName("result_short_name")
	@Column(length = 15)
	private String resultShortName;

	@SerializedName("schedule_note")
	@Column(length = 50)
	private String scheduleNote;

	@SerializedName("scheduled_days")
	private Integer scheduledDays;

	@SerializedName("scheduled_innings")
	private Integer scheduledInnings;

	@SerializedName("scheduled_overs")
	private double scheduledOvers;

	@SerializedName("scorecard_source")
	@Column(length = 15)
	private String scorecardSource;

	@SerializedName("scribble_id")
	private Integer scribbleId;

	private String season;

	@SerializedName("site_id")
	private Integer siteId;

	@SerializedName("site_name")
	@Column(length = 50)
	private String siteName;

	@SerializedName("start_date")
	@Column(length = 20)
	private String startDate;

	@SerializedName("start_date_gmt_offset")
	@Column(length = 20)
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
	private LocalTime startTimeGmt;

	@SerializedName("start_time_local")
	private LocalTime startTimeLocal;

	@SerializedName("sub_class_id")
	private Integer subClassId;

	@SerializedName("team1_abbreviation")
	@Column(length = 15)
	private String team1Abbreviation;

	@SerializedName("team1_class_id")
	private Integer team1ClassId;

	@SerializedName("team1_country_id")
	private Integer team1CountryId;

	@SerializedName("team1_filename")
	@Column(length = 20)
	private String team1Filename;

	@SerializedName("team1_id")
	private Integer team1Id;

	@SerializedName("team1_logo_alt_id")
	@Column(length = 10)
	private String team1LogoAltId;

	@SerializedName("team1_logo_espncdn")
	@Column(length = 10)
	private String team1LogoEspncdn;

	@SerializedName("team1_logo_object_id")
	private Integer team1LogoObjectId;

	@SerializedName("team1_name")
	@Column(length = 50)
	private String team1Name;

	@SerializedName("team1_object_id")
	private Integer team1ObjectId;

	@SerializedName("team1_short_name")
	@Column(length = 20)
	private String team1ShortName;

	@SerializedName("team2_abbreviation")
	@Column(length = 10)
	private String team2Abbreviation;

	@SerializedName("team2_class_id")
	private Integer team2ClassId;

	@SerializedName("team2_country_id")
	private Integer team2CountryId;

	@SerializedName("team2_filename")
	@Column(length = 20)
	private String team2Filename;

	@SerializedName("team2_id")
	private Integer team2Id;

	@SerializedName("team2_logo_alt_id")
	@Column(length = 10)
	private String team2LogoAltId;

	@SerializedName("team2_logo_espncdn")
	@Column(length = 10)
	private String team2LogoEspncdn;

	@SerializedName("team2_logo_object_id")
	private Integer team2LogoObjectId;

	@SerializedName("team2_name")
	@Column(length = 50)
	private String team2Name;

	@SerializedName("team2_object_id")
	private Integer team2ObjectId;

	@SerializedName("team2_short_name")
	@Column(length = 20)
	private String team2ShortName;

	@SerializedName("tiebreaker_name")
	@Column(length = 20)
	private String tiebreakerName;

	@SerializedName("tiebreaker_team_id")
	private Integer tiebreakerTeamId;

	@SerializedName("tiebreaker_type")
	private Integer tiebreakerType;

	@SerializedName("time_zone")
	@Column(length = 30)
	private String timeZone;

	@SerializedName("toss_choice_team_id")
	private Integer tossChoiceTeamId;

	@SerializedName("toss_decision")
	private Integer tossDecision;

	@SerializedName("toss_decision_name")
	@Column(length = 20)
	private String tossDecisionName;

	@SerializedName("toss_winner_team_id")
	private int tossWinnerTeamId;

	@SerializedName("town_aka")
	private String townAka;

	@SerializedName("town_area")
	@Column(length = 30)
	private String townArea;

	@SerializedName("town_id")
	private int townId;

	@SerializedName("town_name")
	@Column(length = 50)
	private String townName;

	@SerializedName("tz_short_name")
	@Column(length = 10)
	private String tzShortName;

	@SerializedName("url_component")
	@Column(length = 20)
	private String urlComponent;

	@SerializedName("watch_espn_id")
	private int watchEspnId;

	@SerializedName("weather_location_code")
	@Column(length = 20)
	private String weatherLocationCode;

	@SerializedName("winner_team_id")
	private int winnerTeamId;

	@Override
	public String toString() {
		return "Match [matchId=" + matchId + "]";
	}
	
}