package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.exception.ApiIdNotFoundException;
import com.blueoptima.ratelimiter.exception.ApiInfoNotSavedException;
import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import com.blueoptima.ratelimiter.model.ApiRegistrationResp;
import com.blueoptima.ratelimiter.model.UserRegistrationReq;
import com.blueoptima.ratelimiter.model.UserRegistrationResp;
import org.springframework.stereotype.Service;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public interface AdminRegistrationService {

	ApiRegistrationResp register(ApiRegistrationReq registrationReq) throws ApiInfoNotSavedException;
	UserRegistrationResp register(UserRegistrationReq userRegistrationReq) throws ApiIdNotFoundException;

}
