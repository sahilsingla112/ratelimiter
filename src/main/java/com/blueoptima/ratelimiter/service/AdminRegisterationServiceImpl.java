package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import com.blueoptima.ratelimiter.model.ApiRegistrationResp;
import com.blueoptima.ratelimiter.model.UserRegistrationReq;
import com.blueoptima.ratelimiter.model.UserRegistrationResp;
import org.springframework.stereotype.Service;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public class AdminRegisterationServiceImpl implements AdminRegistrationService{

	@Override public ApiRegistrationResp register(ApiRegistrationReq registrationReq) {
		return null;
	}

	@Override public UserRegistrationResp register(UserRegistrationReq userRegistrationReq) {
		return null;
	}
}
