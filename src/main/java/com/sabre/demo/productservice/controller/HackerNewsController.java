package com.sabre.demo.productservice.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sabre.demo.productservice.dto.CommentDTO;
import com.sabre.demo.productservice.dto.StoryDTO;
import com.sabre.demo.productservice.entity.StroyEntity;
import com.sabre.demo.productservice.service.NewsService;

@RestController
@RefreshScope
public class HackerNewsController {

	private NewsService newsService;

	@Value("${latest.stories.count}")
	private Integer storyCount;

	@Value("${latest.story.comments.count}")
	private Integer commentsCount;

	@Autowired
	public HackerNewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping("/top-stories")
	public Collection<StoryDTO> getTopStories() {
		return newsService.getTopStories(storyCount);
	}

	@GetMapping("/comments/{storyId}")
	public List<CommentDTO> getTopComments(@PathVariable("storyId") Long storyId) {
		return newsService.getTopTenComments(storyId, commentsCount);
	}

	@GetMapping("/past-stories")
	public Iterable<StroyEntity> getPastStories() {
		return newsService.getStories();
	}

}
