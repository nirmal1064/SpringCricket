package com.project.cricket.entity;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.project.cricket.entity.keys.FowKey;
import com.project.cricket.model.DismissalFielder;
import com.project.cricket.model.PlayerOrTeam;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"match", "innings", "bowlerId", "fowWicketNum"})
@Entity
@Table(name = "fow_summary")
@IdClass(FowKey.class)
public class Fow implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "match_id", referencedColumnName = "match_id")
	private Match match;

	@Transient
	private PlayerOrTeam dismissalBatsman;

	@Transient
	private PlayerOrTeam dismissalBowler;

	@Transient
	private List<DismissalFielder> dismissalFielders = new ArrayList<>();

	@Transient
	private Map<String, String> dismissalText = new HashMap<>();

	@Id
	private Integer innings;

	private String 	battedType;

	private Integer dismissalType;

	@Id
	private Integer batsmanId;

	private Integer	bowlerId;

	private Integer	fielder1Id;

	private Integer	fielder2Id;

	private Integer	fielder3Id;

	private Integer	fielder4Id;

	private String 	dismissalTextShort;

	private String 	dismissalTextLong;

	private Integer fowOrder;

	@Id
	private Integer fowWicketNum;

	private Integer fowRuns;

	private Integer fowBalls;

	private Double 	fowOvers;

	private Integer fowOverNumber;

	private Double 	ballOversActual;

	private Integer ballTotalRuns;

	private Integer ballBatsmanRuns;

	private Integer isKeeper;

	private Integer isSubstitute;

}
