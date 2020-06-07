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
public interface AdminRegistrationService {

	ApiRegistrationResp register(ApiRegistrationReq registrationReq);
	UserRegistrationResp register(UserRegistrationReq userRegistrationReq);

}
