package com.mindtree.ambulanceserviceapplication.modules.customer.dto;

import java.util.Date;

import com.mindtree.ambulanceserviceapplication.modules.hospital.entity.Hospital;

public class CustomerDto {
	private long customerId;
	private String name;
	private String landmark;
	private Double latitude;
	private Double longitude;
	private Double distance;
	private Double billAmount;
	private Date serviceDate;
	private String modeOfPayment;
	private String mobileNumber;
	private Hospital hospital;

	public CustomerDto() {
		super();
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", name=" + name + ", landmark=" + landmark + ", latitude="
				+ latitude + ", longitude=" + longitude + ", distance=" + distance + ", billAmount=" + billAmount
				+ ", serviceDate=" + serviceDate + ", modeOfPayment=" + modeOfPayment + ", mobileNumber=" + mobileNumber
				+ ", hospital=" + hospital + "]";
	}

}
