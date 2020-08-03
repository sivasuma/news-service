package com.sabre.demo.productservice.tasks;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabre.demo.productservice.json.CommentsJson;
import com.sabre.demo.productservice.json.StoryJson;
import com.sabre.demo.productservice.util.RestUtils;

public class CreateCommentsTask extends StoryJson implements Callable<CommentsJson> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private long commentId;
	private String commetURL;

	public CreateCommentsTask(Integer commentId) {
		this.commentId = commentId;
		commetURL = "https://hacker-news.firebaseio.com/v0/item/" + this.commentId + ".json?print=pretty";

	}

	@Override
	public CommentsJson call() throws Exception {
		CommentsJson comment = new CommentsJson();
		try {
			comment = new ObjectMapper().readValue(RestUtils.getResponse(commetURL), CommentsJson.class);
		} catch (JsonProcessingException e) {
			logger.info("Exception occured while retriving the story  ", e);
		}
		return comment;
	}

}
