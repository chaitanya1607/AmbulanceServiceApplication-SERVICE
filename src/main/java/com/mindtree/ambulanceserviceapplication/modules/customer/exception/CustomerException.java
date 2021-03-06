package com.mindtree.ambulanceserviceapplication.modules.customer.exception;

import com.mindtree.ambulanceserviceapplication.exception.AmbulanceApplicationException;

@SuppressWarnings("serial")
public class CustomerException extends AmbulanceApplicationException {

	public CustomerException() {
	}

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
