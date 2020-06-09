package com.blueoptima.ratelimiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Much better than naive Fixed window algorithm
 * Traffic burst is smoothened by extrapolating the number of requests from the previous window.
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service("fixedwindow")
public class RateLimitFixedWindowImpl implements RateLimitService{

	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitFixedWindowImpl.class);

	@Autowired
	private JedisPool jedisPool;

	@Override public boolean allowRequest(String userId, Long apiId, Integer maxRateLimit) {
		int totalCount = 0;
		LOGGER.info("maxRateLimit: " + maxRateLimit);
		try(Jedis jedis = jedisPool.getResource()) {
			final int millisToMinute = 60 * 1000;
			final int millisToSec = 1000;

			// Remove milliseconds from the time
			final long currentTime = System.currentTimeMillis();
			final long currentTimeInSec =(currentTime / millisToSec) * millisToSec;
			final long currentTimeInMinute = (currentTime / millisToMinute) * millisToMinute;

			// Check the timestamp of previous window
			final long prevTimeInMinute = currentTimeInMinute - millisToMinute;

			String currentKey = userId + "_" + apiId + "_" + currentTimeInMinute;
			String prevKey = userId + "_" + apiId + "_" + prevTimeInMinute;

			String currentCountStr = jedis.get(currentKey);
			int currentCount  = 0;

			if (currentCountStr != null)
				currentCount = Integer.parseInt(currentCountStr);

			String prevCountStr = jedis.get(prevKey);
			int extrapolatedPrevCount = 0;

			if (prevCountStr != null){
				int secondsOfPrevWindow = (60000 - (int)(currentTimeInSec - currentTimeInMinute))/1000;
				int prevCount = Integer.parseInt(prevCountStr);
				extrapolatedPrevCount = (prevCount * secondsOfPrevWindow)/60;
			}

			totalCount = 1 + currentCount + extrapolatedPrevCount;

			jedis.incr(currentKey);

			// Key didn't exist before
			if (currentCountStr == null) {
				jedis.expire(currentKey, 120);
			}

			LOGGER.info("Count in the current window + : " + 1+currentCount);
			LOGGER.info("Extrapolated count from the previous window: " + extrapolatedPrevCount);
		}
		return totalCount <= maxRateLimit;
	}
}
