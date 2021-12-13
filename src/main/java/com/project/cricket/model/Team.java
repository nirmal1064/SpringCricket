package com.project.cricket.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.project.cricket.entity.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {

	@SerializedName("batsmen_in_side")
	private Integer batsmenInSide;

	@SerializedName("content_id")
	private Integer contentId;

	@SerializedName("country_id")
	private Integer countryId;

	@SerializedName("fielders_in_side")
	private Integer fieldersInSide;

	@SerializedName("logo_alt_id")
	private String logoAltId;

	@SerializedName("logo_espncdn")
	private String logoEspncdn;

	@SerializedName("logo_height")
	private Integer logoHeight;

	@SerializedName("logo_image_height")
	private Integer logoImageHeight;

	@SerializedName("logo_image_path")
	private String logoImagePath;

	@SerializedName("logo_image_width")
	private Integer logoImageWidth;

	@SerializedName("logo_object_id")
	private Integer logoObjectId;

	@SerializedName("logo_path")
	private String logoPath;

	@SerializedName("logo_width")
	private Integer logoWidth;

	@SerializedName("object_id")
	private Integer objectId;

	private List<Player> player;

	@SerializedName("players_in_side")
	private Integer playersInSide;

	@SerializedName("site_id")
	private Integer siteId;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_filename")
	private String teamFilename;

	@SerializedName("team_general_name")
	private String teamGeneralName;

	@SerializedName("team_id")
	private Integer teamId;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;

	@SerializedName("url_component")
	private String urlComponent;

}