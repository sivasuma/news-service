package com.sabre.demo.productservice.dto;

import com.sabre.demo.productservice.json.StoryJson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StoryDTO {
	
	String title;
	String url;
	int score;
	long time;
	String by;

	public StoryDTO(String title, String url, int score, long time, String by) {
		this.title = title;
		this.url = url;
		this.score = score;
		this.time = time;
		this.by = by;
	}

	public StoryDTO(StoryJson story) {
		this.title = story.getTitle();
		this.url = story.getUrl();
		this.score = story.getScore();
		this.time = story.getTime();
		this.by = story.getBy();
	}

}
