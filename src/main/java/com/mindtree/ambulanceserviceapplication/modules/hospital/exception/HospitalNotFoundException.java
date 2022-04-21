package com.mindtree.ambulanceserviceapplication.modules.hospital.exception;

@SuppressWarnings("serial")
public class HospitalNotFoundException extends HospitalServiceException {

	public HospitalNotFoundException() {

	}

	public HospitalNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HospitalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HospitalNotFoundException(String message) {
		super(message);
	}

	public HospitalNotFoundException(Throwable cause) {
		super(cause);
	}

}
