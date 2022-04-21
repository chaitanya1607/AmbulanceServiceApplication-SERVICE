package com.mindtree.ambulanceserviceapplication.modules.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mindtree.ambulanceserviceapplication.modules.hospital.entity.Hospital;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private long customerId;

	@Column(length = 40)
	@Size(min = 3, max = 40)
	@NotEmpty(message = "Name is required")
	@Pattern(regexp = "[a-zA-Z\\s]*", message = "Invalid name")
	@Schema(description = "Customer's full name", required = true)
	private String name;

	@Column(length = 100)
	@Schema(description = "Customer's current location landmark", required = false)
	private String landmark;

	@Column(name = "latitude")
	@Schema(description = "Latitude of the location of the customer", required = true)
	private Double latitude;

	@Column(name = "longitude")
	@Schema(description = "Longitude of the location of the customer", required = true)
	private Double longitude;

	@Schema(description = "Distance from the customer location to the hospital", required = true)
	private Double distance;

	@Schema(description = "Bill amount for a particular ride", required = false)
	private Double billAmount;

	@NotEmpty(message = "Date is required")
	@Schema(description = "Date when ambulance service is availed ", required = true)
	private Date serviceDate;

	@Schema(description = "Mode of payment made by the customer", required = true)
	private String modeOfPayment;
	@Column(length = 10)
	@NotEmpty(message = "Mobile Number is required")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
	@Schema(description = "Customer's mobile Number", required = true)
	private String mobileNumber;

	@OneToOne
	private Hospital hospital;

	public Customer() {
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
		return "Customer [customerId=" + customerId + ", name=" + name + ", landmark=" + landmark + ", latitude="
				+ latitude + ", longitude=" + longitude + ", distance=" + distance + ", billAmount=" + billAmount
				+ ", serviceDate=" + serviceDate + ", modeOfPayment=" + modeOfPayment + ", mobileNumber=" + mobileNumber
				+ ", hospital=" + hospital + "]";
	}

}