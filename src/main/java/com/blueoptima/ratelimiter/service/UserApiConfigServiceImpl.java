package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.ApiInfo;
import com.blueoptima.ratelimiter.model.UserApiKey;
import com.blueoptima.ratelimiter.model.UserApiLimit;
import com.blueoptima.ratelimiter.repository.ApiInfoRepository;
import com.blueoptima.ratelimiter.repository.UserApiLimitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 06-06-2020
 */

/**
 * Thread safe
 */
@Service
public class UserApiConfigServiceImpl implements UserApiConfigService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserApiConfigServiceImpl.class);

	@Autowired
	private UserApiLimitRepository userApiLimitRepository;

	@Autowired
	private ApiInfoRepository apiInfoRepository;

	private Map<UserApiKey, Integer> userApiLimitMap = new ConcurrentHashMap<>();
	private Map<Long, Integer> apiLimitMap = new ConcurrentHashMap<>();
	private Map<String, Long> apiMap = new ConcurrentHashMap<>();

	@Value("${default.rate.limit:100}")
	private Integer globalDefaultLimit;

	// Store config in memory, .
	@Override
	public void loadAllConfig(){
		final List<UserApiLimit> all = userApiLimitRepository.findAll();
		for (UserApiLimit userApiLimit: all){
			userApiLimitMap.put(userApiLimit.getUserApiKey(), userApiLimit.getRatelimit());
		}

		final List<ApiInfo> apiInfos = apiInfoRepository.findAll();
		for (ApiInfo apiInfo: apiInfos){
			apiLimitMap.put(apiInfo.getId(), apiInfo.getRatelimit());
			apiMap.put(apiInfo.getUrl(), apiInfo.getId());
		}
	}

	@Override
	public void addApiInfo(String apiUrl, Integer limit){
		ApiInfo apiInfo = new ApiInfo(apiUrl, limit);
		final ApiInfo saved = apiInfoRepository.save(apiInfo);
		apiMap.put(apiUrl, saved.getId());
	}

	@Override
	public void addUserApiInfo(String apiUrl, String userId, Integer limit){
		final Long apiId = apiMap.get(apiUrl);
		userApiLimitMap.put(new UserApiKey(userId, apiId), limit);
	}

	/**
	 * First try user+api combination, then api default limit, finally global limit
	 * @param user
	 * @param url
	 * @return
	 */
	@Override
	public Integer getRateLimit(String user, String url){
		final Long apiId = apiMap.get(url);
		UserApiKey userApiKey = new UserApiKey(user, apiId);

		Integer limit = userApiLimitMap.get(userApiKey);

		if (limit == null) {
			limit = apiLimitMap.get(apiId);
			if (limit == null)
				limit = globalDefaultLimit;
		}

		return limit;
	}
}
