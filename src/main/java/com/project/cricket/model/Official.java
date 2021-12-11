package com.project.cricket.model;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.model.keys.OfficialKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "official_summary")
@ToString
@IdClass(OfficialKey.class)
@EqualsAndHashCode(callSuper = true)
public class Official extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_id")
	private Integer teamId;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;
}