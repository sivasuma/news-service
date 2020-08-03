package com.sabre.demo.productservice.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabre.demo.productservice.json.StoryJson;

public class CommonUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

	public static List<String> formatStories(String allStories) {
		return Arrays.asList(allStories.replace("[ ", "").replace(" ]", "").trim().split(", "));
	}

	public static StoryJson parseStory(String commentList) {
		StoryJson storyJson = new StoryJson();
		try {
			storyJson = new ObjectMapper().readValue(commentList, StoryJson.class);
		} catch (JsonProcessingException e) {
			LOGGER.info("Exception occured while Prcoessing Comments object {}", e);
		}
		return storyJson;
	}
}
