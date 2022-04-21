package com.mindtree.ambulanceserviceapplication.modules.ambulance.exception;

@SuppressWarnings("serial")
public class AmbulanceException extends Exception {

	public AmbulanceException() {
	}

	public AmbulanceException(String message) {
		super(message);
	}

	public AmbulanceException(Throwable cause) {
		super(cause);
	}

	public AmbulanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmbulanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
