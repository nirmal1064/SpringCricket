package com.project.cricket.entity.keys;

import java.io.Serializable;

import com.project.cricket.entity.Match;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class PlayerKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Match match;
	private Integer objectId;
	private Integer teamId;

}
