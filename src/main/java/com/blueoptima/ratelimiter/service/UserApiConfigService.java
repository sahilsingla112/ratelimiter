package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.exception.ApiIdNotFoundException;
import com.blueoptima.ratelimiter.model.ApiInfo;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public interface UserApiConfigService {

	void loadAllConfig();

	Long getApiId(String apiUrl);

	ApiInfo addApiInfo(String apiUrl, Integer limit);

	void addUserApiInfo(Long apiId, String userId, Integer limit) throws ApiIdNotFoundException;

	Integer getRateLimit(String user, String url);

}
