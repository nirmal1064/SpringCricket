package com.project.cricket.model.keys;

import java.io.Serializable;

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
public class InningsKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer matchId;
	private Integer inningsNumber;

}
