package com.project.cricket.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.superclass.Person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "official_summary")
@ToString
public class Official extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		return true;
	}

}