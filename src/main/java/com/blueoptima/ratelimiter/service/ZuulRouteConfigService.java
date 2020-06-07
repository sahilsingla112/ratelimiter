package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public interface ZuulRouteConfigService {
	void addRouteToZuulConfig(ApiRegistrationReq request);

	void refreshZuulConfig();
}
