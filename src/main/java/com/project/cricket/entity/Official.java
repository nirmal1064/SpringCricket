package com.project.cricket.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.superclass.Person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "official_summary")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Official extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;
}