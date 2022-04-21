package com.mindtree.ambulanceserviceapplication.modules.ambulance.exception;

@SuppressWarnings("serial")
public class DuplicateEntryException extends AmbulanceServiceException {

	public DuplicateEntryException() {
	}

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(Throwable cause) {
		super(cause);
	}

	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEntryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
