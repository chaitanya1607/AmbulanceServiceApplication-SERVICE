package com.mindtree.ambulanceserviceapplication.modules.ambulance.exception;

@SuppressWarnings("serial")
public class AmbulanceServiceException extends AmbulanceException {

	public AmbulanceServiceException() {
	}

	public AmbulanceServiceException(String message) {
		super(message);
	}

	public AmbulanceServiceException(Throwable cause) {
		super(cause);
	}

	public AmbulanceServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmbulanceServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
