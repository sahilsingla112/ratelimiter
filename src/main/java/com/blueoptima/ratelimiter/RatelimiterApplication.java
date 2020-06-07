package com.blueoptima.ratelimiter;

import com.blueoptima.ratelimiter.service.UserApiConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class RatelimiterApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatelimiterApplication.class);

	@Autowired UserApiConfigService userApiConfigService;

	public static void main(String[] args) {
		SpringApplication.run(RatelimiterApplication.class, args);

	}

	@Override public void run(String... args) throws Exception {
		userApiConfigService.initConfig();
		int ratelimit = userApiConfigService.getRateLimit("user1", "/api/v1/developers");
		LOGGER.info("" + ratelimit);
	}
}
