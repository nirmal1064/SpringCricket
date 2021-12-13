package com.project.cricket.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.project.cricket.entity.superclass.MatchPerson;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "debuts")
public class Debut extends MatchPerson {
	private static final long serialVersionUID = 1L;
}
