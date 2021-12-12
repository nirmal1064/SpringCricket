package com.project.cricket.model;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.project.cricket.model.keys.MatchPersonKey;

import lombok.Data;

/**
 * Base class that can be used to represent a match and player
 * @author Nirmal
 *
 */
@Data
@MappedSuperclass
@IdClass(MatchPersonKey.class)
public abstract class MatchPerson implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private PlayerOrTeam player;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Id
	private Integer objectId;
}
