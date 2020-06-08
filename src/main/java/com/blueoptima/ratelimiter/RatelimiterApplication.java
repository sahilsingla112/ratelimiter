package com.blueoptima.ratelimiter;

import com.blueoptima.ratelimiter.service.UserApiConfigServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class RatelimiterApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatelimiterApplication.class);

	@Autowired UserApiConfigServiceImpl userApiConfigServiceImpl;

	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RatelimiterApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RatelimiterApplication.class, args);

	}

	@Override public void run(String... args) throws Exception {
		userApiConfigServiceImpl.loadAllConfig();
		int ratelimit = userApiConfigServiceImpl.getRateLimit("user1", "/api/v1/developers");
		LOGGER.info("" + ratelimit);
	}
}
