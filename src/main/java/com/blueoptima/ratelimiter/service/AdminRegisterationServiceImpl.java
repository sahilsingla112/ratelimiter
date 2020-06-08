package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.exception.ApiIdNotFoundException;
import com.blueoptima.ratelimiter.exception.ApiInfoNotSavedException;
import com.blueoptima.ratelimiter.exception.ZuulConfigNotUpdatedException;
import com.blueoptima.ratelimiter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public class AdminRegisterationServiceImpl implements AdminRegistrationService{

	private static final String SUCCESSFUL_MESSAGE = "Registration is successul";

	@Autowired
	private UserApiConfigService userApiConfigService;

	@Autowired
	private ZuulRouteConfigService zuulRouteConfigService;

	@Override public ApiRegistrationResp register(ApiRegistrationReq registrationReq) throws ApiInfoNotSavedException,
			ZuulConfigNotUpdatedException {
		// Add new route to Zuul Configuration
		zuulRouteConfigService.addRouteToZuulConfig(registrationReq);

		// Add the new api to configuration
		ApiInfo saved = userApiConfigService.addApiInfo(registrationReq.getDownStreamApiUri(), registrationReq.getDefaultLimitPerMinute(), registrationReq.getRateLimitAccuracy());

		if (saved == null)
			throw new ApiInfoNotSavedException("Error in saving API info to configuration");

		// Dynamic refresh of route configuration for immediate effect
		if (registrationReq.isRefresh())
			zuulRouteConfigService.refreshZuulConfig(registrationReq.getName());

		return new ApiRegistrationResp(SUCCESSFUL_MESSAGE, saved.getId());
	}

	@Override public UserRegistrationResp register(UserRegistrationReq userRegistrationReq) throws
			ApiIdNotFoundException {
		final Integer rateLimitPerSecond = userRegistrationReq.getRateLimitPerSecond();
		final String username = userRegistrationReq.getUsername();
		userApiConfigService.addUserApiInfo(userRegistrationReq.getApiId(), username, rateLimitPerSecond);
		return new UserRegistrationResp(SUCCESSFUL_MESSAGE);
	}
}
