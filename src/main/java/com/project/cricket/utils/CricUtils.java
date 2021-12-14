package com.project.cricket.utils;

import static com.project.cricket.utils.Constants.ATAG;
import static com.project.cricket.utils.Constants.HREF;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

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
}
