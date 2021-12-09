package com.project.cricket.model.keys;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InningsKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer matchId;
	private Integer inningsNumber;
}
