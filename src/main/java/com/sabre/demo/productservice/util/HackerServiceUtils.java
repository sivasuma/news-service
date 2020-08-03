package com.sabre.demo.productservice.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sabre.demo.productservice.json.CommentsJson;
import com.sabre.demo.productservice.json.StoryJson;
import com.sabre.demo.productservice.tasks.CreateCommentsTask;
import com.sabre.demo.productservice.tasks.CreateStoryTask;

@Component
public class HackerServiceUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HackerServiceUtils.class);

	ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public List<Future<StoryJson>> getStoryFutures(List<CreateStoryTask> storyTasks) {
		List<Future<StoryJson>> storyResults = new ArrayList<>();
		try {
			storyResults = executorService.invokeAll(storyTasks);

		} catch (InterruptedException e) {
			LOGGER.info("Exception occured while invoking story tasks {}", e);
		}

		return storyResults;
	}

	public List<StoryJson> getJsonStoryList(List<CreateStoryTask> storyTasks) {

		List<Future<StoryJson>> storyResults = getStoryFutures(storyTasks);

		List<StoryJson> results = new ArrayList<>();
		try {
			for (Future<StoryJson> storyResult : storyResults) {
				StoryJson result = storyResult.get();
				results.add(result);
			}
		} catch (ExecutionException | InterruptedException e) {
			LOGGER.info("Exception occurec while getting the stories from Future object :{}", e);
		}

		return results;
	}

	public List<CommentsJson> getJsoncommentsList(List<CreateCommentsTask> commentsTasks) {
		List<Future<CommentsJson>> commentResults = getCommentFutures(commentsTasks);
		List<CommentsJson> results = new ArrayList<>();

		try {
			for (Future<CommentsJson> commentResult : commentResults) {
				CommentsJson result = commentResult.get();
				results.add(result);
			}
		} catch (ExecutionException | InterruptedException e) {
			LOGGER.info("Exception occured while getting the stories from Future object :{}", e);
		}

		return results;
	}

	public List<Future<CommentsJson>> getCommentFutures(List<CreateCommentsTask> commentsTasks) {
		List<Future<CommentsJson>> commentResults = new ArrayList<>();
		try {
			commentResults = executorService.invokeAll(commentsTasks);

		} catch (InterruptedException e) {
			LOGGER.info("Exception occurred while invoking comment tasks {}", e);
		}

		return commentResults;
	}

}
