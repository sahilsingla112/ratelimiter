package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import org.springframework.stereotype.Service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public class ZuulRouteConfigServiceImpl implements ZuulRouteConfigService{

	@Override public void addRouteToZuulConfig(ApiRegistrationReq request) {
		//TODO
		// zuul.routes.<name>.path=/<name>/**
		// zuul.routes.<name>.url=<downStreamServiceUrl>
		String url =  request.getDownStreamServiceUrl();

		// Execute shell script to add this configuration to git file.
		// TODO: Future implementation may require exploring JAVA API of git instead of invoking shell script.

	}

	// It will refresh the routing information dynamically without restart required.
	@Override public void refreshZuulConfig() {

	}
}
