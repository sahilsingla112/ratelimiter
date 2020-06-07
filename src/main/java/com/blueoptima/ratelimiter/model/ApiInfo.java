package com.blueoptima.ratelimiter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */

@Entity
public class ApiInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String url;

	private Integer ratelimit;

	public ApiInfo() {
	}

	public ApiInfo(String url, Integer ratelimit) {
		this.url = url;
		this.ratelimit = ratelimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRatelimit() {
		return ratelimit;
	}

	public void setRatelimit(Integer ratelimit) {
		this.ratelimit = ratelimit;
	}
}
