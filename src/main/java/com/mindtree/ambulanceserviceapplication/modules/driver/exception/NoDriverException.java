package com.mindtree.ambulanceserviceapplication.modules.driver.exception;

@SuppressWarnings("serial")
public class NoDriverException extends DriverException {

	public NoDriverException() {
	}

	public NoDriverException(String message) {
		super(message);
	}

	public NoDriverException(Throwable cause) {
		super(cause);
	}

	public NoDriverException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
