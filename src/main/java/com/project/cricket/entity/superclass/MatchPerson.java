package com.project.cricket.entity.superclass;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.project.cricket.entity.Match;
import com.project.cricket.entity.keys.MatchPersonKey;
import com.project.cricket.model.PlayerOrTeam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base class that can be used to represent a match and player
 * @author Nirmal
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"match", "objectId"})
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
