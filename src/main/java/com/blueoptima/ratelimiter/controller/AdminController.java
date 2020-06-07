package com.blueoptima.ratelimiter.controller;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import com.blueoptima.ratelimiter.model.ApiRegistrationResp;
import com.blueoptima.ratelimiter.model.UserRegistrationReq;
import com.blueoptima.ratelimiter.model.UserRegistrationResp;
import com.blueoptima.ratelimiter.service.AdminRegistrationService;
import com.blueoptima.ratelimiter.service.UserApiConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminRegistrationService adminRegistrationService;

	@PostMapping(value = "/api")
	public ResponseEntity<ApiRegistrationResp> register(@RequestBody ApiRegistrationReq request){
		final ApiRegistrationResp response = adminRegistrationService.register(request);
		if (response != null){
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(new ApiRegistrationResp("Error: Registration is unsuccessful", -1L), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<UserRegistrationResp> register(@RequestBody UserRegistrationReq request){
		final UserRegistrationResp response = adminRegistrationService.register(request);
		if (response != null){
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(new UserRegistrationResp("Error: Registration is unsuccessful"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
