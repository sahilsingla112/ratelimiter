package com.blueoptima.ratelimiter.service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public interface UserApiConfigService {

	void loadAllConfig();

	void addApiInfo(String apiUrl, Integer limit);

	void addUserApiInfo(String apiUrl, String userId, Integer limit);

	Integer getRateLimit(String user, String url);

}
