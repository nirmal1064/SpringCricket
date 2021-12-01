package com.project.cricket.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {

	@SerializedName("batsmen_in_side")
	private int batsmenInSide;

	@SerializedName("content_id")
	private int contentId;

	@SerializedName("country_id")
	private int countryId;

	@SerializedName("fielders_in_side")
	private int fieldersInSide;

	@SerializedName("logo_alt_id")
	private String logoAltId;

	@SerializedName("logo_espncdn")
	private String logoEspncdn;

	@SerializedName("logo_height")
	private int logoHeight;

	@SerializedName("logo_image_height")
	private int logoImageHeight;

	@SerializedName("logo_image_path")
	private String logoImagePath;

	@SerializedName("logo_image_width")
	private int logoImageWidth;

	@SerializedName("logo_object_id")
	private int logoObjectId;

	@SerializedName("logo_path")
	private String logoPath;

	@SerializedName("logo_width")
	private int logoWidth;

	@SerializedName("object_id")
	private int objectId;

	private List<Player> player;

	@SerializedName("players_in_side")
	private int playersInSide;

	@SerializedName("site_id")
	private int siteId;

	@SerializedName("team_abbreviation")
	private String teamAbbreviation;

	@SerializedName("team_filename")
	private String teamFilename;

	@SerializedName("team_general_name")
	private String teamGeneralName;

	@SerializedName("team_id")
	private String teamId;

	@SerializedName("team_name")
	private String teamName;

	@SerializedName("team_short_name")
	private String teamShortName;

	@SerializedName("url_component")
	private String urlComponent;

}