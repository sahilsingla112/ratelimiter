package com.blueoptima.ratelimiter.exception;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 06-06-2020
 */
public class UserApiKeyNotFoundException extends RuntimeException {
	public UserApiKeyNotFoundException() {
		super();
	}

	public UserApiKeyNotFoundException(String message) {
		super(message);
	}
}
