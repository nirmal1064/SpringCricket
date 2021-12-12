package com.project.cricket.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DismissalFielder implements Serializable {

	private static final long serialVersionUID = 1L;

	private PlayerOrTeam player;
	private Integer isKeeper;
	private Integer isSubstitute;

}
