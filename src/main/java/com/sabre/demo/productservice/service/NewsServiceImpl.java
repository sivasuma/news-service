package com.sabre.demo.productservice.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.EvictingQueue;
import com.sabre.demo.productservice.dto.CommentDTO;
import com.sabre.demo.productservice.dto.StoryDTO;
import com.sabre.demo.productservice.entity.StroyEntity;
import com.sabre.demo.productservice.json.CommentsJson;
import com.sabre.demo.productservice.repository.NewsRepository;
import com.sabre.demo.productservice.tasks.CreateCommentsTask;
import com.sabre.demo.productservice.tasks.CreateStoryTask;
import com.sabre.demo.productservice.util.CommonUtil;
import com.sabre.demo.productservice.util.HackerServiceUtils;
import com.sabre.demo.productservice.util.RestUtils;

@Service
public class NewsServiceImpl implements NewsService {

	private static final String JSON = ".json?print=pretty";

	@Value("${latest.stories.url}")
	private String topStoriesUrl;

	@Value("${latest.story.single.url}")
	private String singleStoryUrl;

	private NewsRepository newsRepository;

	private HackerServiceUtils hackerServiceUtils;

	private EvictingQueue<StoryDTO> last10News = com.google.common.collect.EvictingQueue.create(10);

	private LocalDateTime lastUpdatedTime = LocalDateTime.now().minusMinutes(100);

	public NewsServiceImpl(NewsRepository newsRepository, RestTemplate restTemplate,
			HackerServiceUtils hackerServiceUtils) {
		this.newsRepository = newsRepository;
		this.hackerServiceUtils = hackerServiceUtils;
	}

	@Override
	public Collection<StoryDTO> getTopStories(Integer storyCount) {

		// Read from cache if for the last 10 mins
		if (Duration.between(lastUpdatedTime, LocalDateTime.now()).toMinutes() < 10) {
			return last10News;
		}

		List<StoryDTO> topTenStories = getTopTenStories(storyCount);

		topTenStories.stream().forEach((StoryDTO dto) -> {
			newsRepository.save(new StroyEntity(dto));
		});

		last10News.addAll(topTenStories);
		lastUpdatedTime = LocalDateTime.now();
		return topTenStories;

	}

	@Override
	public Iterable<StroyEntity> getStories() {
		return newsRepository.findAll();

	}

	@Override
	public List<CommentDTO> getTopTenComments(Long storyID, Integer storyCount) {

		List<CommentDTO> commentsDto = new ArrayList<>();

		List<Integer> comments = CommonUtil.parseStory(RestUtils.getResponse(singleStoryUrl(storyID))).getKids();

		if (null != comments) {

			List<CommentsJson> results = getAllComments(comments);

			List<CommentDTO> allComments = results.stream().map(json -> new CommentDTO(json))
					.collect(Collectors.toList()).stream()
					.sorted(Comparator.comparing(CommentDTO::getNoOfChidComments).reversed())
					.collect(Collectors.toList());

			if (allComments.size() >= storyCount) {
				commentsDto = allComments.subList(0, storyCount);
			} else {
				commentsDto = allComments.stream().collect(Collectors.toList());

			}
		}

		return commentsDto;
	}

	// All private methods --

	private List<CommentsJson> getAllComments(List<Integer> commentsList) {
		List<CreateCommentsTask> tasks = new ArrayList<>();

		commentsList.forEach((Integer commentId) -> {
			tasks.add(new CreateCommentsTask(commentId));
		});

		List<CommentsJson> results = hackerServiceUtils.getJsoncommentsList(tasks);
		return results;
	}

	private List<StoryDTO> getTopTenStories(Integer storyCount) {

		List<StoryDTO> finalStories;
		List<StoryDTO> storiesByScore = getAllStories();

		// If no stories in last 10 minutes - display the last 10 stories
		if (storiesByScore.size() > storyCount) {

			finalStories = storiesByScore.stream().filter(storyDTO -> isLastTenMinute(storyDTO))
					.collect(Collectors.toList());

			if (finalStories.size() >= storyCount) {
				return finalStories.subList(0, storyCount);

			}

			return storiesByScore.subList(0, storyCount);
		}

		return storiesByScore;
	}

	private List<StoryDTO> getAllStories() {
		String allStoriesStringResponse = RestUtils.getResponse(topStoriesUrl);
		List<String> storyIds = CommonUtil.formatStories(allStoriesStringResponse);

		List<CreateStoryTask> storyTasks = new ArrayList<>();

		storyIds.forEach(storyId -> {
			String url = singleStoryUrl + storyId + JSON;
			storyTasks.add(new CreateStoryTask(url));
		});

		List<StoryDTO> allStories = hackerServiceUtils.getJsonStoryList(storyTasks).stream()
				.map(story -> new StoryDTO(story)).collect(Collectors.toList());

		List<StoryDTO> storiesByScore = allStories.stream().sorted(Comparator.comparing(StoryDTO::getScore).reversed())
				.collect(Collectors.toList());
		return storiesByScore;
	}

	private boolean isLastTenMinute(StoryDTO storyDTO) {
		return storyDTO.getTime() > Instant.now().minusSeconds(600).getEpochSecond();
	}

	private String singleStoryUrl(Long storyID) {
		return singleStoryUrl + storyID + JSON;
	}

}
