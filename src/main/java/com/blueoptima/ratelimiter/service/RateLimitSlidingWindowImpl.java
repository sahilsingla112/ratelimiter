package com.blueoptima.ratelimiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Implementation based on sliding window algorithm.
 * Window will be divided into 60 parts. Here, 1 minute will be divided 60 windows of 1sec each.
 * Key will be combination of User + API Id.
 * Value will be another Redis hash whose key will be Second timestamp and value will be count of requests.
 *
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service("slidingwindow")
public class RateLimitSlidingWindowImpl implements RateLimitService{

	private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitSlidingWindowImpl.class);

	@Autowired
	private JedisPool jedisPool;

	@Override public boolean allowRequest(String userId, Long apiId, Integer maxRateLimit) {
		int totalCount = 1;
		try (Jedis jedis = jedisPool.getResource()){
			String currentKey = userId + "_api" + apiId;

			final int millisToSec = 1000;

			final long currentTime0 = System.currentTimeMillis();
			final long currentTimeInSec0 = (currentTime0 / millisToSec) * millisToSec;

			final Map<String, String> allWindows = jedis.hgetAll(currentKey);
			for (Map.Entry<String, String> window: allWindows.entrySet()){

				final String key =  window.getKey();
				long time = Long.parseLong(key);

				// Remove all those windows from the hash that are past 1 min.
				if ((currentTime0 - time) > 60000) {
					jedis.hdel(currentKey, key);
				}else{
					long count = Long.parseLong(window.getValue());
					totalCount += count;
				}
			}

			jedis.hincrBy(currentKey, Long.toString(currentTimeInSec0), 1);

		}catch (Exception e){
			LOGGER.error("Exception while calculating rate limit", e);
		}

		LOGGER.info("Total count: " + totalCount);
		return totalCount <= maxRateLimit;
	}

}
