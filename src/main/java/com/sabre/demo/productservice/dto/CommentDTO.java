package com.sabre.demo.productservice.dto;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.sabre.demo.productservice.json.CommentsJson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO {

	private String text;
	private long age;
	private long noOfChidComments;
	private String handle;

	public CommentDTO(String text, long age, long noOfChidComments) {
		this.text = text;
		this.age = age;
		this.noOfChidComments = noOfChidComments;
	}

//	commentDTO.getText(),
//	commentDTO.getAge(), commentDTO.getNoOfChidComments())

	public CommentDTO(CommentsJson json) {
		this.text = json.getText();
		this.age = getCommentsAge(json.getTime());
		this.noOfChidComments = json.getKids() != null ? json.getKids().size() : 0;
	}

	private Long getCommentsAge(Long time) {
		Instant instant = Instant.ofEpochSecond(time);
		Date commentedDate = Date.from(instant);
		Date currentDate = new Date();
		long noOfDays = TimeUnit.DAYS.convert(currentDate.getTime() - commentedDate.getTime(), TimeUnit.MILLISECONDS);

		return (noOfDays / 365);
	}
}
