package com.blueoptima.ratelimiter.service;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
public interface RateLimitService {
	boolean allowRequest(String userId, Long apiId, Integer maxRateLimit);
}
