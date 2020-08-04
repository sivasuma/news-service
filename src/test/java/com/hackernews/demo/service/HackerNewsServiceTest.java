package com.hackernews.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabre.demo.productservice.dto.CommentDTO;
import com.sabre.demo.productservice.dto.StoryDTO;
import com.sabre.demo.productservice.json.CommentsJson;
import com.sabre.demo.productservice.json.StoryJson;
import com.sabre.demo.productservice.repository.NewsRepository;
import com.sabre.demo.productservice.service.NewsServiceImpl;
import com.sabre.demo.productservice.util.HackerServiceUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class HackerNewsServiceTest {
    @InjectMocks
    private NewsServiceImpl hackerNewsService;
    @Value("${latest.stories.url}")
    private String topStoriesUrl;

    @Value("${latest.story.single.url}")
    private String singleStoryUrl;

    @Mock
    private NewsRepository hackerNewsSRepository;

    @Mock
    private HackerServiceUtils hackerServiceUtils;

    Integer storyCount;

    @Before
    public void setUp() {
        storyCount = 10;
        hackerNewsSRepository = mock(NewsRepository.class);
        hackerServiceUtils = mock(HackerServiceUtils.class);

        hackerNewsService = new NewsServiceImpl(hackerNewsSRepository, mock(RestTemplate.class), hackerServiceUtils);
        ReflectionTestUtils.setField(hackerNewsService, "hackerServiceUtils", hackerServiceUtils);
        ReflectionTestUtils.setField(hackerNewsService, "topStoriesUrl", "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        ReflectionTestUtils.setField(hackerNewsService, "singleStoryUrl", "https://hacker-news.firebaseio.com/v0/item/");

    }

    @Test
    public void gettopStroies() {
        doReturn(getSring()).when(hackerServiceUtils).getJsonStoryList(Mockito.anyObject());

        Collection<StoryDTO> topStroies = hackerNewsService.getTopStories(storyCount);

        Assert.assertEquals(5, topStroies.size());

    }

    @Test
    public void gettopStroiesWhenCountIsMoreThan10() {
        List<StoryJson> storyJsons = getSring();

        StoryJson storyJson = storyJsons.get(0);
        storyJsons.add(storyJson);
        storyJsons.add(storyJson);
        storyJsons.add(storyJson);
        storyJsons.add(storyJson);
        storyJsons.add(storyJson);
        storyJsons.add(storyJson);

        doReturn(storyJsons).when(hackerServiceUtils).getJsonStoryList(Mockito.anyObject());

        Collection<StoryDTO> topStroies = hackerNewsService.getTopStories(storyCount);

        Assert.assertEquals(10, topStroies.size());

    }


    private List<StoryJson> getSring() {
        List<StoryJson> futures = new LinkedList<>();

        StoryJson story = new StoryJson();
        String storyList =
                "    {\n" +
                        "        \"title\": \"My YC app: Dropbox - Throw away your USB drive\",\n" +
                        "        \"url\": \"http://www.getdropbox.com/u/2/screencast.html\",\n" +
                        "        \"score\": 100,\n" +
                        "        \"time\": 1175714200,\n" +
                        "        \"by\": \"dhouston\"\n" +
                        "    }";

        try {
            story = new ObjectMapper().readValue(storyList, StoryJson.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        futures.add(story);
        futures.add(story);
        futures.add(story);
        futures.add(story);
        futures.add(story);
        return futures;
    }

    @Test
    public void getTopTenComments() {

        doReturn(getCommentsSring()).when(hackerServiceUtils).getJsoncommentsList(Mockito.anyObject());
        List<CommentDTO> topStroies = hackerNewsService.getTopTenComments(new Long(8863), storyCount);

        Assert.assertEquals(10, topStroies.size());

    }

    private List<CommentsJson> getCommentsSring() {
        List<CommentsJson> commentsJsons = new LinkedList<>();

        CommentsJson commentsJson = new CommentsJson();

        String jsonValue =
                "    {\n" +
                        "  \"by\" : \"norvig\",\n" +
                        "  \"id\" : 2921983,\n" +
                        "  \"kids\" : [ 2922097, 2922429, 2924562, 2922709, 2922573, 2922140, 2922141 ],\n" +
                        "  \"parent\" : 2921506,\n" +
                        "  \"text\" : \"Aw shucks, guys ... you make me blush with your compliments.<p>Tell you what, Ill make a deal: I'll keep writing if you keep reading. K?\",\n" +
                        "  \"time\" : 1314211127,\n" +
                        "  \"type\" : \"comment\"\n" +
                        "}";

        try {
            commentsJson = new ObjectMapper().readValue(jsonValue, CommentsJson.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);
        commentsJsons.add(commentsJson);

        return commentsJsons;
    }

    private ResponseEntity<String> getCommentsJson() {

        String json = "{\n" +
                "  \"by\" : \"dhouston\",\n" +
                "  \"descendants\" : 71,\n" +
                "  \"id\" : 8863,\n" +
                "  \"kids\" : [ 9224, 8917, 8952, 8884, 8887, 8958, 8869, 8940, 8908, 9005, 8873, 9671, 9067, 9055, 8865, 8881, 8872, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8870, 8878, 8980, 8934, 8943, 8876 ],\n" +
                "  \"score\" : 104,\n" +
                "  \"time\" : 1175714200,\n" +
                "  \"title\" : \"My YC app: Dropbox - Throw away your USB drive\",\n" +
                "  \"type\" : \"story\",\n" +
                "  \"url\" : \"http://www.getdropbox.com/u/2/screencast.html\"\n" +
                "}";


        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    

}
