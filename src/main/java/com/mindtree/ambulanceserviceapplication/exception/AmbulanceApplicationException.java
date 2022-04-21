package com.mindtree.ambulanceserviceapplication.exception;

@SuppressWarnings("serial")
public class AmbulanceApplicationException extends Exception {

	public AmbulanceApplicationException() {
	}

	public AmbulanceApplicationException(String message) {
		super(message);
	}

	public AmbulanceApplicationException(Throwable cause) {
		super(cause);
	}

	public AmbulanceApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmbulanceApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
