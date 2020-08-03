package com.sabre.demo.productservice.service;

import java.util.Collection;
import java.util.List;

import com.sabre.demo.productservice.dto.CommentDTO;
import com.sabre.demo.productservice.dto.StoryDTO;
import com.sabre.demo.productservice.entity.StroyEntity;

public interface NewsService {

	Collection<StoryDTO> getTopStories(Integer storyCount);

	List<CommentDTO> getTopTenComments(Long storyID, Integer storyCount);

	Iterable<StroyEntity> getStories();

}
