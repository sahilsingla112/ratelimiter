package com.blueoptima.ratelimiter.exception;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public class ApiIdNotFoundException extends Exception {
	public ApiIdNotFoundException() {
		super();
	}

	public ApiIdNotFoundException(String message) {
		super(message);
	}
}
