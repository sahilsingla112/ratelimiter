package com.blueoptima.ratelimiter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Spring Configuration class to declare beans
 * @author Sahil Singla
 * @version 1.0
 * @since 04-06-2020
 */
@Configuration
public class RateLimiterConfig {

	private static final Logger LOG = LoggerFactory.getLogger(RateLimiterConfig.class);

	private ConfigurationParser parser;

	public RateLimiterConfig(ConfigurationParser configurationParser){
		this.parser = configurationParser;
	}

	@PostConstruct
	public void init(){
		//final Integer limit = parser.getRateLimit("user1", "/api/v1/developer");
		//LOG.info("Rate limit %d", limit);
	}
}

