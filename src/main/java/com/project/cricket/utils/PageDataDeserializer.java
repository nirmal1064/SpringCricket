package com.project.cricket.utils;

import static com.project.cricket.utils.Constants.CONTENT;
import static com.project.cricket.utils.Constants.DATA;
import static com.project.cricket.utils.Constants.MATCH;
import static com.project.cricket.utils.Constants.PAGEDATA;
import static com.project.cricket.utils.Constants.PAGEPROPS;
import static com.project.cricket.utils.Constants.PROPS;
import static com.project.cricket.utils.Constants.SCORECARD;
import static com.project.cricket.utils.Constants.SUPPORTINFO;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class PageDataDeserializer<T> implements JsonDeserializer<T> {

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonElement props = json.getAsJsonObject().get(PROPS);
		JsonElement pageProps = props.getAsJsonObject().get(PAGEPROPS);
		JsonElement data = pageProps.getAsJsonObject().get(DATA);
		JsonElement pageData = data.getAsJsonObject().get(PAGEDATA);
		JsonElement match = pageData.getAsJsonObject().get(MATCH);
		JsonElement content = pageData.getAsJsonObject().get(CONTENT);
		JsonElement scorecard = content.getAsJsonObject().get(SCORECARD);
		JsonElement supportInfo = content.getAsJsonObject().get(SUPPORTINFO);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add(MATCH, match);
		jsonObject.add(SCORECARD, scorecard);
		jsonObject.add(SUPPORTINFO, supportInfo);

		return new Gson().fromJson(jsonObject.toString(), typeOfT);
	}

}
