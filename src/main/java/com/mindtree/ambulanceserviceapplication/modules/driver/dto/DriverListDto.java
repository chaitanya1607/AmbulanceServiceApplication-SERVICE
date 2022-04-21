package com.mindtree.ambulanceserviceapplication.modules.driver.dto;

public class DriverListDto {
	
	private long driverId;
	private String fullName;
	private String drivingLicenceNumber;
	private String mobileNumber;
	public DriverListDto() {
		super();
	}
	public DriverListDto(long driverId, String fullName, String drivingLicenceNumber, String mobileNumber) {
		super();
		this.driverId = driverId;
		this.fullName = fullName;
		this.drivingLicenceNumber = drivingLicenceNumber;
		this.mobileNumber = mobileNumber;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}
	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

}
