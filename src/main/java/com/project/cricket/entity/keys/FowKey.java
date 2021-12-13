package com.project.cricket.entity.keys;

import java.io.Serializable;

import com.project.cricket.entity.Match;

import lombok.Data;

@Data
public class FowKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Match match;
	private Integer innings;
	private Integer batsmanId;
	private Integer fowWicketNum;

}
