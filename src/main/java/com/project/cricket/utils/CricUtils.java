package com.project.cricket.utils;

import static com.project.cricket.utils.Constants.ATAG;
import static com.project.cricket.utils.Constants.HREF;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CricUtils {

	public String cleanString(String string) {
		return string.trim();
	}

	public String getIdFromElement(Element element) {
		String idUrl = element.selectFirst(ATAG).absUrl(HREF);
		String[] split = idUrl.split("/");
		String idHtml = split[split.length - 1];
		String id = idHtml.replace(".html", "");
		return cleanString(id);
	}

	public <T> ResponseEntity<List<T>> getListResponse(List<T> response) {
		if (!CollectionUtils.isEmpty(response)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public <T> ResponseEntity<T> getIntegerResponse(T response) {
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public static <T> List<T> emptyIfNull(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<>();
		}
		return list;
	}

}
