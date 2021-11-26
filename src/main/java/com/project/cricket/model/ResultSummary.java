package com.project.cricket.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "results_summary")
public class ResultSummary {
	@Id
	private Integer matchId;
	private int classId;
	private String team1;
	private String team2;
	private String winner;
	private String margin;
	private String ground;
	private int groundId;
	private String matchDate;
	private String scorecard;
	private int year;
	private String matchUrl;
	private String matchJsonUrl;
	private int matchNumber;

	/**
	 * @return the matchId
	 */
	public Integer getMatchId() {
		return matchId;
	}
	/**
	 * @param matchId the matchId to set
	 */
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	/**
	 * @return the classId
	 */
	public int getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(int classId) {
		this.classId = classId;
	}
	/**
	 * @return the team1
	 */
	public String getTeam1() {
		return team1;
	}
	/**
	 * @param team1 the team1 to set
	 */
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	/**
	 * @return the team2
	 */
	public String getTeam2() {
		return team2;
	}
	/**
	 * @param team2 the team2 to set
	 */
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}
	/**
	 * @return the margin
	 */
	public String getMargin() {
		return margin;
	}
	/**
	 * @param margin the margin to set
	 */
	public void setMargin(String margin) {
		this.margin = margin;
	}
	/**
	 * @return the ground
	 */
	public String getGround() {
		return ground;
	}
	/**
	 * @param ground the ground to set
	 */
	public void setGround(String ground) {
		this.ground = ground;
	}
	/**
	 * @return the groundId
	 */
	public int getGroundId() {
		return groundId;
	}
	/**
	 * @param groundId the groundId to set
	 */
	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}
	/**
	 * @return the matchDate
	 */
	public String getMatchDate() {
		return matchDate;
	}
	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	/**
	 * @return the scorecard
	 */
	public String getScorecard() {
		return scorecard;
	}
	/**
	 * @param scorecard the scorecard to set
	 */
	public void setScorecard(String scorecard) {
		this.scorecard = scorecard;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the matchUrl
	 */
	public String getMatchUrl() {
		return matchUrl;
	}
	/**
	 * @param matchUrl the matchUrl to set
	 */
	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}
	/**
	 * @return the matchJsonUrl
	 */
	public String getMatchJsonUrl() {
		return matchJsonUrl;
	}
	/**
	 * @param matchJsonUrl the matchJsonUrl to set
	 */
	public void setMatchJsonUrl(String matchJsonUrl) {
		this.matchJsonUrl = matchJsonUrl;
	}
	/**
	 * @return the matchNumber
	 */
	public int getMatchNumber() {
		return matchNumber;
	}
	/**
	 * @param matchNumber the matchNumber to set
	 */
	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}
}
