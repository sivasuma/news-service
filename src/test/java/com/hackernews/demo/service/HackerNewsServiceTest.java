//package com.hackernews.demo.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sabre.demo.productservice.dto.CommentDTO;
//import com.sabre.demo.productservice.dto.StoryDTO;
//import com.sabre.demo.productservice.json.CommentsJson;
//import com.sabre.demo.productservice.json.StoryJson;
//import com.sabre.demo.productservice.repository.NewsRepository;
//import com.sabre.demo.productservice.service.NewsServiceImpl;
//import com.sabre.demo.productservice.util.HackerServiceUtils;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//
//public class HackerNewsServiceTest {
//    @InjectMocks
//    private NewsServiceImpl hackerNewsService;
//
//
//    @Mock
//    private NewsRepository hackerNewsSRepository;
//
//    @Mock
//    private HackerServiceUtils hackerServiceUtils;
//
//    @Before
//    public void setUp() {
//        hackerNewsSRepository = mock(NewsRepository.class);
//        hackerServiceUtils = mock(HackerServiceUtils.class);
//        hackerNewsService = new NewsServiceImpl(hackerNewsSRepository, mock(RestTemplate.class), hackerServiceUtils);
//    }
//
//    @Test
//    public void gettopStroies() {
//        ReflectionTestUtils.setField(hackerNewsService, "hackerNewsSRepository", hackerNewsSRepository);
//        Mockito.when(hackerServiceUtils.getStringResponseEntity(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(getListosStoryIds());
//        //     Mockito.when(hackerServiceUtils.getJsonStoryList(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).then(getSring());
//        doReturn(getSring()).when(hackerServiceUtils).getJsonStoryList(Mockito.anyObject(), Mockito.anyObject());
//
//        List<StoryDTO> topStroies = hackerNewsService.getTopStories();
//
//        Assert.assertEquals(5, topStroies.size());
//
//    }
//
//    @Test
//    public void gettopStroiesWhenmoreStories() {
//        ReflectionTestUtils.setField(hackerNewsService, "hackerNewsSRepository", hackerNewsSRepository);
//        Mockito.when(hackerServiceUtils.getStringResponseEntity(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(getListosStoryIds());
//        //     Mockito.when(hackerServiceUtils.getJsonStoryList(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).then(getSring());
//
//        List<StoryJson> storyJsons = getSring();
//        StoryJson storyJson = storyJsons.get(0);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//        storyJsons.add(storyJson);
//
//        doReturn(storyJsons).when(hackerServiceUtils).getJsonStoryList(Mockito.anyObject(), Mockito.anyObject());
//
//        List<StoryDTO> topStroies = hackerNewsService.getTopStories();
//
//        Assert.assertEquals(10, topStroies.size());
//
//    }
//
//    @Test
//    public void getTopTenComments() {
//        ReflectionTestUtils.setField(hackerNewsService, "hackerNewsSRepository", hackerNewsSRepository);
//        Mockito.when(hackerServiceUtils.getStringResponseEntity(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(getCommentsJson());
//        //     Mockito.when(hackerServiceUtils.getJsonStoryList(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).then(getSring());
//        doReturn(getCommentsSring()).when(hackerServiceUtils).getJsoncommentsList(Mockito.anyObject(), Mockito.anyObject());
//
//        List<CommentDTO> topStroies = hackerNewsService.getTopTenComments(new Long(8683));
//
//        Assert.assertEquals(10, topStroies.size());
//
//    }
//
//    private List<CommentsJson> getCommentsSring() {
//        List<CommentsJson> commentsJsons = new LinkedList<>();
//
//        CommentsJson commentsJson = new CommentsJson();
//
//        String jsonValue =
//                "    {\n" +
//                        "        \"text\": \"Drew,<p>I saw your short demo at BarCamp and I must say Dropbox looks great! Are you planning on having a Linux port as well, or is too early to talk about that?<p>Also, as another SFP applicant I have to tell you that I really hope you get the funding - you deserve it.\\n\",\n" +
//                        "        \"age\": 13,\n" +
//                        "        \"noOfChidComments\": 3,\n" +
//                        "        \"handle\": null\n" +
//                        "    }";
//
//        try {
//            commentsJson = new ObjectMapper().readValue(jsonValue, CommentsJson.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//        commentsJsons.add(commentsJson);
//
//        return commentsJsons;
//    }
//
//    private ResponseEntity<String> getCommentsJson() {
//
//        String json = "{\n" +
//                "  \"by\" : \"dhouston\",\n" +
//                "  \"descendants\" : 71,\n" +
//                "  \"id\" : 8863,\n" +
//                "  \"kids\" : [ 9224, 8917, 8952, 8884, 8887, 8958, 8869, 8940, 8908, 9005, 8873, 9671, 9067, 9055, 8865, 8881, 8872, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8870, 8878, 8980, 8934, 8943, 8876 ],\n" +
//                "  \"score\" : 104,\n" +
//                "  \"time\" : 1175714200,\n" +
//                "  \"title\" : \"My YC app: Dropbox - Throw away your USB drive\",\n" +
//                "  \"type\" : \"story\",\n" +
//                "  \"url\" : \"http://www.getdropbox.com/u/2/screencast.html\"\n" +
//                "}";
//
//
//        return new ResponseEntity<>(json, HttpStatus.OK);
//    }
//
//    private ResponseEntity<String> getListosStoryIds() {
//        String ids = "[ 24022751, 24024841, 24013200, 24026416, 24026270, 24023979, 24026778, 24024876, 24024239, 24019013, 24019274, 24024813, 24025143, 24025684, 24023604, 24025478, 24019185, 24021044, 24025075, 24025587, 24011058, 24025034, 24026208, 24018759, 24019778, 24014633, 24023959, 24021025, 24026371, 24025584, 24024230, 24025502, 24024559, 24026680, 24009177, 24024131, 24025747, 24000145, 24020970, 24023727, 24020263, 24020245, 24020906, 24024662, 24025759, 24021103, 24021415, 24020952, 24021046, 24024632, 24020990, 24021525, 24022222, 24021929, 24025920, 24019932, 24019826, 24023566, 24007566, 24020254, 24010152, 24026200, 24015707, 24020183, 24019977, 24026568, 24011939, 24024066, 24020366, 24006150, 24017904, 24026193, 24019540, 24021408, 24020566, 24011505, 24020899, 24025092, 24016718, 24019586, 24018097, 24021004, 24006697, 24023122, 24007186, 24025442, 24022974, 24017555, 24016977, 24018093, 24005047, 23993259, 24024302, 24025663, 24019402, 24005167, 24021128, 24020244, 24021147, 24022388, 23984503, 23999124, 24007274, 24007354, 24019987, 24008244, 24020005, 24026010, 24022131, 24024903, 24006618, 23990960, 24024085, 24000546, 23998793, 24006932, 24017261, 24019861, 24004573, 24022105, 23998115, 24008678, 24003384, 24021956, 23995049, 24001106, 24018978, 24019042, 23996407, 23996412, 23997667, 24017042, 24001894, 24020212, 24024881, 24003385, 24004588, 24023763, 24024219, 24020794, 24004640, 24025100, 23987671, 23987952, 24026286, 24020321, 23987353, 23977375, 24000167, 24018253, 23995648, 24024371, 24018131, 24005629, 23979608, 24016662, 23985140, 23994619, 23983541, 24007929, 23997976, 24016738, 24019726, 23995750, 23998075, 23997236, 23981583, 23981563, 24016938, 24022606, 24019530, 24011573, 23989048, 24012587, 23985825, 24007278, 23999934, 24004346, 24008557, 24002588, 24020456, 23999862, 23994020, 24003734, 23989215, 23989023, 24008516, 24024612, 24024600, 24008108, 23981518, 24008297, 24021558, 23985168, 24002222, 24024485, 23983900, 24018965, 23999179, 23993033, 23992882, 23995921, 23995583, 23989738, 24007908, 23981079, 23998324, 23997301, 23998933, 24019073, 23991221, 23997182, 24003548, 23984102, 24020097, 23997498, 23996505, 23977256, 24008753, 23997891, 23999542, 23989638, 23999065, 23980477, 24018272, 23988269, 23983974, 23996442, 23979388, 24001246, 23990075, 23986773, 23998503, 24004500, 24025732, 24020377, 23997034, 24004071, 23996730, 24009134, 23986934, 24015550, 24000484, 23993251, 23985817, 24015099, 24003946, 23997122, 23987346, 23993225, 23994952, 23983508, 24002566, 23983704, 23996502, 23995843, 23994642, 23997161, 23985352, 24014456, 23992290, 23997444, 23987584, 23992532, 23987264, 23998079, 24023279, 23997998, 23977642, 24013538, 24007538, 23984098, 24003507, 23997361, 23998643, 23984568, 24006236, 23985315, 23986925, 23983548, 24017234, 24003590, 23983749, 24012319, 23985525, 23985772, 23984909, 23997298, 23987779, 24006339, 23997128, 23993954, 23986166, 23983906, 24013304, 23987760, 24018398, 23993716, 24011727, 23992304, 23987562, 24008604, 23997009, 23997271, 24013440, 23977234, 23976356, 23992913, 23998551, 24012496, 23995909, 24008709, 23988239, 23982814, 24005443, 24019362, 24003067, 23985762, 23996097, 24012791, 24017022, 24017165, 24021715, 23990874, 24007605, 23991111, 24014623, 23987650, 23998646, 23979647, 23986451, 23977471, 24007428, 23989035, 24013747, 23994718, 23981998, 24019155, 23984015, 23996733, 23992214, 24017017, 24010504, 24011141, 23992184, 24016998, 23980279, 23983501, 24017136, 24013320, 23984171, 23978294, 23984071, 24009672, 24013047, 24006120, 24013499, 23981846, 24008501, 24002724, 23985374, 23985405, 24018287, 23978237, 24010864, 23983756, 23989189, 24003814, 23988547, 23983483, 23987708, 23985449, 24018648, 23979219, 23990026, 23978664, 23989663, 23998355, 24000520, 24005053, 23997477, 23993227, 23985816, 24002212, 24015845, 23993318, 23982078, 23998201, 24009301, 23979451, 24003300, 23976680, 24019373, 23981795, 23976664, 23988665, 23976322, 23999212, 24015013, 24005522, 23996413, 24004249, 24002808, 23988437, 24004933, 24018269, 24018602, 23986895, 23998541, 24009521, 24025824, 24019769, 24018479, 23990842, 23997524, 23976491, 24022932, 23989538, 23981675, 23979578, 24017185, 23996756, 23986251, 24010365, 24003494, 23976449, 24003044, 24002789, 24012374, 23997614, 23981447, 24013272, 23990688, 23995101, 23982946, 24019915, 23979018, 23981813, 23997176, 23980874, 24003116, 23998834, 23995699, 24002644, 23993141, 23991169, 23985970, 24013045, 23991644, 23976172, 23978092, 23980190, 24020588, 23976463, 24005618, 24002809, 23985148, 24024652, 23999995, 24002062, 24010995, 23991213, 24015789, 24006220, 24010633, 23998371, 24000876, 24016858, 24002845, 23998089, 23996037, 24005918, 24017653, 24005843, 23976502, 24017770, 23979241, 23986408, 23986282, 23986203, 23991397, 24013400, 24001625, 24019857, 24016259, 23997949, 23980833, 23989483, 23989108, 23983390, 23985311, 24004572, 23979069, 23978935, 23993104, 24012633, 23984436, 24005718, 24011961, 24011938 ]";
//
//        return new ResponseEntity<>(ids, HttpStatus.OK);
//    }
//
//    private List<StoryJson> getSring() {
//        List<StoryJson> futures = new LinkedList<>();
//
//        StoryJson story = new StoryJson();
//        String storyList =
//                "    {\n" +
//                        "        \"title\": \"My YC app: Dropbox - Throw away your USB drive\",\n" +
//                        "        \"url\": \"http://www.getdropbox.com/u/2/screencast.html\",\n" +
//                        "        \"score\": 100,\n" +
//                        "        \"time\": 1175714200,\n" +
//                        "        \"by\": \"dhouston\"\n" +
//                        "    }";
//
//        try {
//            story = new ObjectMapper().readValue(storyList, StoryJson.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        futures.add(story);
//        futures.add(story);
//        futures.add(story);
//        futures.add(story);
//        futures.add(story);
//        return futures;
//    }
//
//
//}
