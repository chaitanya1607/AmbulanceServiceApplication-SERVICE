package com.mindtree.ambulanceserviceapplication.modules.ambulance.exception;

@SuppressWarnings("serial")
public class AmbulanceNotFoundException extends AmbulanceServiceException {

	public AmbulanceNotFoundException() {
	}

	public AmbulanceNotFoundException(String message) {
		super(message);
	}

	public AmbulanceNotFoundException(Throwable cause) {
		super(cause);
	}

	public AmbulanceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmbulanceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
