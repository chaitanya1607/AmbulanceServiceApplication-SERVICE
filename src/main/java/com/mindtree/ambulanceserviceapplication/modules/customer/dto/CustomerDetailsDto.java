package com.mindtree.ambulanceserviceapplication.modules.customer.dto;

public class CustomerDetailsDto {
	private long customerId;
	private String name;
	private String landmark;
	private String mobileNumber;
	private Double latitude;
	private Double longitude;

	public CustomerDetailsDto(long customerId, String name, String landmark, String mobileNumber, Double latitude,
			Double longitude) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.landmark = landmark;
		this.mobileNumber = mobileNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public CustomerDetailsDto() {
		super();
	}

}
