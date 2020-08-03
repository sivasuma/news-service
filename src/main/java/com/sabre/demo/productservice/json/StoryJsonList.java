package com.sabre.demo.productservice.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StoryJsonList {

    private List<StoryJson> storyJsonList;

    public List<StoryJson> getStoryJsonList() {
        return storyJsonList;
    }

    public void setStoryJsonList(List<StoryJson> storyJsonList) {
        this.storyJsonList = storyJsonList;
    }
}
