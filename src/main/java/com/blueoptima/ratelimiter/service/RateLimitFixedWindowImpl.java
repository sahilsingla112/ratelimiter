package com.blueoptima.ratelimiter.service;

import org.springframework.stereotype.Service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public class RateLimitFixedWindowImpl implements RateLimitService{

	@Override public boolean allow(String userId, String apiUrl) {
		return false;
	}
}
