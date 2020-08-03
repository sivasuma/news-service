package com.sabre.demo.productservice.entity;

import javax.persistence.*;

import com.sabre.demo.productservice.dto.StoryDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StroyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1000)
	private String title;

	@Column(length = 1000)
	private String url;

	@Column(length = 1000)
	private int score;

	@Column(length = 1000)
	private long time;

	@Column(length = 1000)
	private String by;

	public StroyEntity(StoryDTO dto) {
		this.title = dto.getTitle();
		this.url = dto.getUrl();
		this.score = dto.getScore();
		this.time = dto.getTime();
		this.by = dto.getBy();
	}

	public StroyEntity(String title, String url, int score, long time, String by) {
		this.title = title;
		this.url = url;
		this.score = score;
		this.time = time;
		this.by = by;
	}
}
