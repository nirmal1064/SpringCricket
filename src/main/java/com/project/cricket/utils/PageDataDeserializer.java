package com.project.cricket.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class PageDataDeserializer<T> implements JsonDeserializer<T> {

//	private final Class mNestedClazz;
//	private final Object mNestedDeserializer;
//
//	public PageDataDeserializer(Class nestedClazz, Object nestedDeserializer) {
//		mNestedClazz = nestedClazz;
//		mNestedDeserializer = nestedDeserializer;
//	}

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonElement props = json.getAsJsonObject().get("props");
		JsonElement pageProps = props.getAsJsonObject().get("pageProps");
		JsonElement data = pageProps.getAsJsonObject().get("data");
		JsonElement pageData = data.getAsJsonObject().get("pageData");
		return new Gson().fromJson(pageData, typeOfT);
	}

}
