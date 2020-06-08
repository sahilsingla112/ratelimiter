package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.exception.ApiIdNotFoundException;
import com.blueoptima.ratelimiter.model.ApiInfo;
import com.blueoptima.ratelimiter.model.RateLimitAccuracy;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
public interface UserApiConfigService {

	void loadAllConfig();

	ApiInfo getApiInfo(String apiUri);

	ApiInfo addApiInfo(String apiUri, Integer limit, RateLimitAccuracy accuracy);

	void addUserApiInfo(Long apiId, String userId, Integer limit) throws ApiIdNotFoundException;

	Integer getRateLimit(String user, String uri);

}
