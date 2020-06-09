package com.blueoptima.ratelimiter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
		try(Jedis jedis = jedisPool.getResource()) {
			String key = userId + "_id:" + apiId;

			jedis.set(key, "32");
			jedis.expire(key, 2);
			long start = System.nanoTime();
			String cachedResponse = jedis.get(key);
			LOGGER.info(cachedResponse + " in time: " + (System.nanoTime() - start));
			Thread.sleep(1900);
			final String s = jedis.get(key);
			LOGGER.info(s);
		}catch (InterruptedException e){
			e.printStackTrace();
		}

		return true;
	}
}
