package com.blueoptima.ratelimiter.exception;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 06-06-2020
 */
public class UserApiKeyNotFound extends RuntimeException {
	public UserApiKeyNotFound() {
		super();
	}

	public UserApiKeyNotFound(String message) {
		super(message);
	}
}
