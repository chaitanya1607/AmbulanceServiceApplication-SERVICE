package com.mindtree.ambulanceserviceapplication.modules.hospital.exception;

import com.mindtree.ambulanceserviceapplication.exception.AmbulanceApplicationException;

@SuppressWarnings("serial")
public class HospitalServiceException extends AmbulanceApplicationException {

	public HospitalServiceException() {

	}

	public HospitalServiceException(String message) {
		super(message);
	}

	public HospitalServiceException(Throwable cause) {
		super(cause);
	}

	public HospitalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public HospitalServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
