package com.blueoptima.ratelimiter.model;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
public class UserRegistrationReq {
	private Long apiId;
	private String username;

	// Number of requests allowed per second.
	private Integer rateLimitPerSecond;

	// TODO: Future support: Number of requests allowed per minute.
	// private Integer rateLimitPerMinute;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRateLimitPerSecond() {
		return rateLimitPerSecond;
	}

	public void setRateLimitPerSecond(Integer rateLimitPerSecond) {
		this.rateLimitPerSecond = rateLimitPerSecond;
	}
}
