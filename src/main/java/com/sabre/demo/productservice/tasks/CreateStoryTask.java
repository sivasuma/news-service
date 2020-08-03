package com.sabre.demo.productservice.tasks;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabre.demo.productservice.json.StoryJson;
import com.sabre.demo.productservice.util.RestUtils;

public class CreateStoryTask extends StoryJson implements Callable<StoryJson> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String storyUrl;

	public CreateStoryTask(String storyUrl) {
		this.storyUrl = storyUrl;
	}

	@Override
	public StoryJson call() throws Exception {
		StoryJson story = new StoryJson();
		try {
			story = new ObjectMapper().readValue(RestUtils.getResponse(storyUrl), StoryJson.class);
		} catch (JsonProcessingException e) {
			logger.info("Exception occured while retriving the story  ", e);
		}
		return story;
	}
}
