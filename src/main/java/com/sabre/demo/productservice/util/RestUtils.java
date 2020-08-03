package com.sabre.demo.productservice.util;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestUtils {

	private static HttpEntity<String> httpEntity;
	private static RestTemplate restTemplate = new RestTemplate();

	static {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpEntity = new HttpEntity<>(headers);

	}

	public static String getResponse(String url) {
		return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
	}

}
