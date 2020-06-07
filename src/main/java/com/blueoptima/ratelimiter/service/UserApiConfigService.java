package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.exception.UserApiKeyNotFound;
import com.blueoptima.ratelimiter.model.UserApiKey;
import com.blueoptima.ratelimiter.model.UserApiLimit;
import com.blueoptima.ratelimiter.repository.UserApiLimitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 06-06-2020
 */

@Service
public class UserApiConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserApiConfigService.class);
	// wild card for all users
	private static final String ALL_USERS = "allusers*";

	@Autowired
	private UserApiLimitRepository userApiLimitRepository;

	private Map<UserApiKey, Integer> userApiLimitMap = new ConcurrentHashMap<>();

	@Value("${default.rate.limit:100}")
	private Integer globalDefaultLimit;

	// Load all config to make it hot cache.
	public void initConfig(){
		final List<UserApiLimit> all = userApiLimitRepository.findAll();
		for (UserApiLimit userApiLimit: all){
			userApiLimitMap.put(userApiLimit.getUserApiKey(), userApiLimit.getRatelimit());
		}
	}
	/**
	 * First try user + api combination, then api specific, then default limit
	 * @param user
	 * @param api
	 * @return
	 */
	public Integer getRateLimit(String user, String api){
		UserApiKey userApiKey = new UserApiKey(user, api);
		Integer limit = globalDefaultLimit;


		if (!userApiLimitMap.containsKey(userApiKey)) {
			UserApiKey userApiKey1 = new UserApiKey(ALL_USERS, userApiKey.getApi());

			if (userApiLimitMap.containsKey(userApiKey1))
				limit = userApiLimitMap.get(userApiKey1);
		}else
			limit = userApiLimitMap.get(userApiKey);

		return limit;
	}


}
