package com.blueoptima.ratelimiter.controller;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import com.blueoptima.ratelimiter.model.ApiRegistrationResp;
import com.blueoptima.ratelimiter.model.UserRegistrationReq;
import com.blueoptima.ratelimiter.model.UserRegistrationResp;
import com.blueoptima.ratelimiter.service.AdminRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

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
		try {
			final UserRegistrationResp response = adminRegistrationService.register(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			String message = String.format("Error: Registration is unsuccessful. Possible cause: %s", e.getMessage());
			LOGGER.error(message, e);
			return new ResponseEntity<>(new UserRegistrationResp(message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
