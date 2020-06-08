package com.blueoptima.ratelimiter.service;

import org.springframework.stereotype.Service;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service("slidingwindow")
public class RateLimitSlidingWindowImpl implements RateLimitService{

	@Override public boolean allowRequest(String userId, Long apiId, Integer maxRateLimit) {
		return false;
	}

}
