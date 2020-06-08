package com.blueoptima.ratelimiter.model;

import javax.validation.constraints.NotNull;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 09-06-2020
 */
public class ApiInfoUpdateReq {

	@NotNull
	private Long id;
	private Integer defaultLimitPerMinute;
	private RateLimitAccuracy rateLimitAccuracy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDefaultLimitPerMinute() {
		return defaultLimitPerMinute;
	}

	public void setDefaultLimitPerMinute(Integer defaultLimitPerMinute) {
		this.defaultLimitPerMinute = defaultLimitPerMinute;
	}

	public RateLimitAccuracy getRateLimitAccuracy() {
		return rateLimitAccuracy;
	}

	public void setRateLimitAccuracy(RateLimitAccuracy rateLimitAccuracy) {
		this.rateLimitAccuracy = rateLimitAccuracy;
	}
}
