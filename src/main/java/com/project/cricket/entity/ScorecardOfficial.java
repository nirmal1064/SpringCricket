package com.project.cricket.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.cricket.entity.superclass.MatchPerson;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "match_officials")
public class ScorecardOfficial extends MatchPerson {
	private static final long serialVersionUID = 1L;

	private String type;
}
