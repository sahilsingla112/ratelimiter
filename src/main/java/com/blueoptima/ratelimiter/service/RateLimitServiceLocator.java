package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.RateLimitAccuracy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 08-06-2020
 */

@Component
public class RateLimitServiceLocator {

	@Autowired
	@Qualifier("slidingwindow")
	private RateLimitService slidingWindowBasedService;

	@Autowired
	@Qualifier("fixedwindow")
	private RateLimitService fixedWindowBasedService;

	public RateLimitService getRateLimitService(RateLimitAccuracy accuracy){
		switch (accuracy){
			case HIGH:
				return slidingWindowBasedService;
			case LOW:
				return fixedWindowBasedService;
			default:
					return fixedWindowBasedService;
		}
	}
}
