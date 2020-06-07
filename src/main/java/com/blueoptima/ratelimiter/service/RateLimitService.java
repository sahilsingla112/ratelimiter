package com.blueoptima.ratelimiter.service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public interface RateLimitService {
	boolean allow(String userId, String apiUrl);
}
