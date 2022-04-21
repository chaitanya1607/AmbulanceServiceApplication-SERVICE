package com.mindtree.ambulanceserviceapplication.modules.driver.exception;

import com.mindtree.ambulanceserviceapplication.exception.AmbulanceApplicationException;

@SuppressWarnings("serial")
public class DriverException extends AmbulanceApplicationException {

	public DriverException() {
	}

	public DriverException(String message) {
		super(message);
	}

	public DriverException(Throwable cause) {
		super(cause);
	}

	public DriverException(String message, Throwable cause) {
		super(message, cause);
	}

	public DriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
