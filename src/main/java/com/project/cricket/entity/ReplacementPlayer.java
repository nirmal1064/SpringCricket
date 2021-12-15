package com.project.cricket.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.superclass.MatchPerson;
import com.project.cricket.model.PlayerOrTeam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@NoArgsConstructor
@Table(name = "replacement_summary")
public class ReplacementPlayer extends MatchPerson {

	private static final long serialVersionUID = 1L;

	@Transient
	private PlayerOrTeam team;

	@Transient
	private PlayerOrTeam replacingPlayer;

	@Column(name = "innings")
	private Integer inning;

	private Integer teamId;

	private Integer replacingPlayerId;

	@SerializedName(value = "over")
	private Double overs;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(inning, overs, replacingPlayer, replacingPlayerId, team, teamId);
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
		if (!(obj instanceof ReplacementPlayer)) {
			return false;
		}
		ReplacementPlayer other = (ReplacementPlayer) obj;
		return Objects.equals(inning, other.inning) && Objects.equals(overs, other.overs)
				&& Objects.equals(replacingPlayer, other.replacingPlayer)
				&& Objects.equals(replacingPlayerId, other.replacingPlayerId) && Objects.equals(team, other.team)
				&& Objects.equals(teamId, other.teamId);
	}
	
}
