package com.mindtree.ambulanceserviceapplication.modules.driver.exception;

@SuppressWarnings("serial")
public class DriverAlreadyExistsException extends DriverException {

	public DriverAlreadyExistsException() {
	}

	public DriverAlreadyExistsException(String message) {
		super(message);
	}

	public DriverAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public DriverAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public DriverAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
