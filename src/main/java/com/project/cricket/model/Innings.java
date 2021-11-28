package com.project.cricket.model;

import com.google.gson.annotations.SerializedName;

public class Innings {
	@SerializedName("ball_limit")
	public int ballLimit;

	public int balls;

	public int batted;

	@SerializedName("batting_team_id")
	public int batting_team_id;

	@SerializedName("bowling_team_id")
	public int bowling_team_id;

	public int bpo;

	public int byes;

	public int event;

	@SerializedName("event_name")
	public String eventName;

	public int extras;

	@SerializedName("innings_number")
	public String inningsNumber;

	@SerializedName("innings_numth")
	public String inningsNumth;

	public int lead;

	public int legbyes;

	@SerializedName("live_current")
	public int liveCurrent;

	@SerializedName("live_current_name")
	public String liveCurrentName;

	public String minutes;

	public int noballs;

	@SerializedName("old_penalty_or_bonus")
	public int oldPenaltyOrBonus;

	@SerializedName("over_limit")
	public String overLimit;

	@SerializedName("over_limit_run_rate")
	public String overLimitRunRate;

	@SerializedName("over_split_limit")
	public String overSplitLimit;

	public String overs;

	@SerializedName("overs_docked")
	public int oversDocked;

	public int penalties;

	@SerializedName("penalties_field_end")
	public int penaltiesFieldEnd;

	@SerializedName("penalties_field_start")
	public int penaltiesFieldStart;

	@SerializedName("run_rate")
	public String runRate;

	public int runs;

	public int target;

	public int wickets;

	public int wides;

	/**
	 * @return the ballLimit
	 */
	public int getBallLimit() {
		return ballLimit;
	}

	/**
	 * @param ballLimit the ballLimit to set
	 */
	public void setBallLimit(int ballLimit) {
		this.ballLimit = ballLimit;
	}

	/**
	 * @return the balls
	 */
	public int getBalls() {
		return balls;
	}

	/**
	 * @param balls the balls to set
	 */
	public void setBalls(int balls) {
		this.balls = balls;
	}

	/**
	 * @return the batted
	 */
	public int getBatted() {
		return batted;
	}

	/**
	 * @param batted the batted to set
	 */
	public void setBatted(int batted) {
		this.batted = batted;
	}

	/**
	 * @return the batting_team_id
	 */
	public int getBatting_team_id() {
		return batting_team_id;
	}

	/**
	 * @param batting_team_id the batting_team_id to set
	 */
	public void setBatting_team_id(int batting_team_id) {
		this.batting_team_id = batting_team_id;
	}

	/**
	 * @return the bowling_team_id
	 */
	public int getBowling_team_id() {
		return bowling_team_id;
	}

	/**
	 * @param bowling_team_id the bowling_team_id to set
	 */
	public void setBowling_team_id(int bowling_team_id) {
		this.bowling_team_id = bowling_team_id;
	}

	/**
	 * @return the bpo
	 */
	public int getBpo() {
		return bpo;
	}

	/**
	 * @param bpo the bpo to set
	 */
	public void setBpo(int bpo) {
		this.bpo = bpo;
	}

	/**
	 * @return the byes
	 */
	public int getByes() {
		return byes;
	}

	/**
	 * @param byes the byes to set
	 */
	public void setByes(int byes) {
		this.byes = byes;
	}

	/**
	 * @return the event
	 */
	public int getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(int event) {
		this.event = event;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the extras
	 */
	public int getExtras() {
		return extras;
	}

	/**
	 * @param extras the extras to set
	 */
	public void setExtras(int extras) {
		this.extras = extras;
	}

	/**
	 * @return the inningsNumber
	 */
	public String getInningsNumber() {
		return inningsNumber;
	}

	/**
	 * @param inningsNumber the inningsNumber to set
	 */
	public void setInningsNumber(String inningsNumber) {
		this.inningsNumber = inningsNumber;
	}

	/**
	 * @return the inningsNumth
	 */
	public String getInningsNumth() {
		return inningsNumth;
	}

	/**
	 * @param inningsNumth the inningsNumth to set
	 */
	public void setInningsNumth(String inningsNumth) {
		this.inningsNumth = inningsNumth;
	}

	/**
	 * @return the lead
	 */
	public int getLead() {
		return lead;
	}

	/**
	 * @param lead the lead to set
	 */
	public void setLead(int lead) {
		this.lead = lead;
	}

	/**
	 * @return the legbyes
	 */
	public int getLegbyes() {
		return legbyes;
	}

	/**
	 * @param legbyes the legbyes to set
	 */
	public void setLegbyes(int legbyes) {
		this.legbyes = legbyes;
	}

	/**
	 * @return the liveCurrent
	 */
	public int getLiveCurrent() {
		return liveCurrent;
	}

	/**
	 * @param liveCurrent the liveCurrent to set
	 */
	public void setLiveCurrent(int liveCurrent) {
		this.liveCurrent = liveCurrent;
	}

	/**
	 * @return the liveCurrentName
	 */
	public String getLiveCurrentName() {
		return liveCurrentName;
	}

	/**
	 * @param liveCurrentName the liveCurrentName to set
	 */
	public void setLiveCurrentName(String liveCurrentName) {
		this.liveCurrentName = liveCurrentName;
	}

	/**
	 * @return the minutes
	 */
	public String getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the noballs
	 */
	public int getNoballs() {
		return noballs;
	}

	/**
	 * @param noballs the noballs to set
	 */
	public void setNoballs(int noballs) {
		this.noballs = noballs;
	}

	/**
	 * @return the oldPenaltyOrBonus
	 */
	public int getOldPenaltyOrBonus() {
		return oldPenaltyOrBonus;
	}

	/**
	 * @param oldPenaltyOrBonus the oldPenaltyOrBonus to set
	 */
	public void setOldPenaltyOrBonus(int oldPenaltyOrBonus) {
		this.oldPenaltyOrBonus = oldPenaltyOrBonus;
	}

	/**
	 * @return the overLimit
	 */
	public String getOverLimit() {
		return overLimit;
	}

	/**
	 * @param overLimit the overLimit to set
	 */
	public void setOverLimit(String overLimit) {
		this.overLimit = overLimit;
	}

	/**
	 * @return the overLimitRunRate
	 */
	public String getOverLimitRunRate() {
		return overLimitRunRate;
	}

	/**
	 * @param overLimitRunRate the overLimitRunRate to set
	 */
	public void setOverLimitRunRate(String overLimitRunRate) {
		this.overLimitRunRate = overLimitRunRate;
	}

	/**
	 * @return the overSplitLimit
	 */
	public String getOverSplitLimit() {
		return overSplitLimit;
	}

	/**
	 * @param overSplitLimit the overSplitLimit to set
	 */
	public void setOverSplitLimit(String overSplitLimit) {
		this.overSplitLimit = overSplitLimit;
	}

	/**
	 * @return the overs
	 */
	public String getOvers() {
		return overs;
	}

	/**
	 * @param overs the overs to set
	 */
	public void setOvers(String overs) {
		this.overs = overs;
	}

	/**
	 * @return the oversDocked
	 */
	public int getOversDocked() {
		return oversDocked;
	}

	/**
	 * @param oversDocked the oversDocked to set
	 */
	public void setOversDocked(int oversDocked) {
		this.oversDocked = oversDocked;
	}

	/**
	 * @return the penalties
	 */
	public int getPenalties() {
		return penalties;
	}

	/**
	 * @param penalties the penalties to set
	 */
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	/**
	 * @return the penaltiesFieldEnd
	 */
	public int getPenaltiesFieldEnd() {
		return penaltiesFieldEnd;
	}

	/**
	 * @param penaltiesFieldEnd the penaltiesFieldEnd to set
	 */
	public void setPenaltiesFieldEnd(int penaltiesFieldEnd) {
		this.penaltiesFieldEnd = penaltiesFieldEnd;
	}

	/**
	 * @return the penaltiesFieldStart
	 */
	public int getPenaltiesFieldStart() {
		return penaltiesFieldStart;
	}

	/**
	 * @param penaltiesFieldStart the penaltiesFieldStart to set
	 */
	public void setPenaltiesFieldStart(int penaltiesFieldStart) {
		this.penaltiesFieldStart = penaltiesFieldStart;
	}

	/**
	 * @return the runRate
	 */
	public String getRunRate() {
		return runRate;
	}

	/**
	 * @param runRate the runRate to set
	 */
	public void setRunRate(String runRate) {
		this.runRate = runRate;
	}

	/**
	 * @return the runs
	 */
	public int getRuns() {
		return runs;
	}

	/**
	 * @param runs the runs to set
	 */
	public void setRuns(int runs) {
		this.runs = runs;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * @return the wickets
	 */
	public int getWickets() {
		return wickets;
	}

	/**
	 * @param wickets the wickets to set
	 */
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	/**
	 * @return the wides
	 */
	public int getWides() {
		return wides;
	}

	/**
	 * @param wides the wides to set
	 */
	public void setWides(int wides) {
		this.wides = wides;
	}

}
