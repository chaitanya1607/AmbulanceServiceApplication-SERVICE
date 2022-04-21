package com.mindtree.ambulanceserviceapplication.modules.driver.dto;

public class DriverDetailsDto {

	private long driverId;
	private String fullName;
	private String email;
	private String mobileNumber;
	private String address;
	private String aadharNumber;
	private byte[] aadhar;
	private String drivingLicenceNumber;
	private byte[] license;
	private byte[] photo;
	private boolean criminalRecord;
	private String dateOfBirth;
	private String gender;
	private boolean evocCourse;
	private boolean blsCourse;
	private boolean cprCourse;
	public DriverDetailsDto() {
		super();
	}
	public DriverDetailsDto(long driverId, String fullName, String email, String mobileNumber, String address,
			String aadharNumber, byte[] aadhar, String drivingLicenceNumber, byte[] license, byte[] photo, boolean criminalRecord) {
		super();
		this.driverId = driverId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.aadharNumber = aadharNumber;
		this.aadhar = aadhar;
		this.drivingLicenceNumber = drivingLicenceNumber;
		this.license = license;
		this.photo = photo;
		this.criminalRecord = criminalRecord;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public byte[] getAadhar() {
		return aadhar;
	}
	public void setAadhar(byte[] aadhar) {
		this.aadhar = aadhar;
	}
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}
	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	public byte[] getLicense() {
		return license;
	}
	public void setLicense(byte[] license) {
		this.license = license;
	}
	public boolean isCriminalRecord() {
		return criminalRecord;
	}
	public void setCriminalRecord(boolean criminalRecord) {
		this.criminalRecord = criminalRecord;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isEvocCourse() {
		return evocCourse;
	}
	public void setEvocCourse(boolean evocCourse) {
		this.evocCourse = evocCourse;
	}
	public boolean isBlsCourse() {
		return blsCourse;
	}
	public void setBlsCourse(boolean blsCourse) {
		this.blsCourse = blsCourse;
	}
	public boolean isCprCourse() {
		return cprCourse;
	}
	public void setCprCourse(boolean cprCourse) {
		this.cprCourse = cprCourse;
	}
	
	
}
