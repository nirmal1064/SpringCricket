package com.project.cricket.entity.keys;

import java.io.Serializable;

import com.project.cricket.entity.Match;

import lombok.Data;

@Data
public class DebutKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Match match;
	private Integer objectId;
	private Integer classId;
}
