package com.blueoptima.ratelimiter.model;

/**
 * @author Shashank Goel
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

}
