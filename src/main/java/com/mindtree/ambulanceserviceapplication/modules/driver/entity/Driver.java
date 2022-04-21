package com.mindtree.ambulanceserviceapplication.modules.driver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;
import com.mindtree.ambulanceserviceapplication.modules.customer.entity.Customer;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "driver_table")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long driverId;

	@NotEmpty(message = "Aadhar is required")
	@Pattern(regexp = "^([2-9]{1}[0-9]{3})( )([0-9]{4})( )([0-9]{4})$", message = "Invalid Aadhar number")
	@Schema(description = "Driver's/ Applicant's Aadhaar Number", required = true)
	@Column(unique = true)
	private String aadharNumber;

	@Column(length = 200)
	@NotEmpty(message = "Address is required")
	@Schema(description = "Driver's/ Applicant's Current Address", required = true)
	@NotEmpty(message = "Address is required")
	private String address;

	@Schema(description = "Bill amount for a particular ride", required = false)
	@Nullable
	private double billAmount;

	@AssertTrue
	@Schema(description = "Status of Emergency Vehicle Operator course completion", required = true)
	private boolean evocCourse;

	@AssertFalse
	@Schema(description = "Status of Basic life support course completion", required = false)
	private boolean blsCourse;

	@AssertFalse
	@Schema(description = "Status of Cardiopulmonary resuscitation course completion", required = false)
	private boolean cprCourse;

	@AssertFalse
	@Schema(description = "Status of any criminal history", required = true)
	private boolean criminalRecord;

	@NotEmpty(message = "Date is required")
	@Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", message = "Invalid date of birth")
	@Schema(description = "Driver's/ Applicant's Date of Birth", required = true)
	private String dateOfBirth;

	@NotEmpty(message = "Driving License Number is required")
	@Pattern(regexp = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$", message = "Invalid Driving License number")
	@Schema(description = "Driving licence number of the driver/applicant", required = true)
	private String drivingLicenceNumber;

	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	@Schema(description = "Driver's/ Applicant's email address", required = true)
	private String email;

	@Column(length = 40)
	@Size(min = 3, max = 40)
	@NotEmpty(message = "Name is required")
	@Pattern(regexp = "[a-zA-Z\\s]*", message = "Invalid name")
	@Schema(description = "Driver's/ Applicant's full name", required = true)
	private String fullName;

	@Column(length = 6)
	@NotEmpty(message = "Gender is required")
	@Schema(description = "Driver's/ Applicant's gender", required = true)
	private String gender;

	@AssertFalse
	@Schema(description = "Application acceptance or rejection status (by admin)", required = true)
	private boolean isActivated;

	@Column(length = 10)
	@NotEmpty(message = "Mobile Number is required")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
	@Schema(description = "Driver's/ Applicant's Mobile Number", required = true)
	private String mobileNumber;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "Invaild username")
	@Schema(description = "Driver's/ Applicant's username", required = true)
	private String userName;

	@NotNull
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invaild password")
	@Schema(description = "Driver's/ Applicant's password", required = true)
	private String password;

	@AssertFalse
	@Schema(description = "Status of payment received for ride", required = false)
	private boolean paymentRecieved;

	@Schema(description = "Driver's/ Applicant's Aadhaar proof", required = true)
	@Column(length = 16777215)
	private byte[] aadhar;

	@Schema(description = "File type of Aadhaar proof", required = false)
	private String aadharFileType;

	@Schema(description = "Driver's/ Applicant's License Id proof", required = true)
	@Column(length = 16777215)
	private byte[] license;

	@Schema(description = "File type of License Id proof", required = false)
	private String licenseFileType;

	@Schema(description = "Driver's/ Applicant's photo", required = true)
	@Column(length = 16777215)
	private byte[] photo;

	@Schema(description = "File type of photo", required = false)
	private String photoFileType;

	@Schema(description = "List of customers, the driver has served", required = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Customer> customers;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Ambulance ambulance;

	@Column(name = "logedIn")
	private boolean isLogedIn;

	@Column(name = "Deleted")
	private boolean isDeleted;

	private String confirmPassword;

	private boolean isAssigned;

	public Driver() {
		super();
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
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

	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPaymentRecieved() {
		return paymentRecieved;
	}

	public void setPaymentRecieved(boolean paymentRecieved) {
		this.paymentRecieved = paymentRecieved;
	}

	public byte[] getAadhar() {
		return aadhar;
	}

	public void setAadhar(byte[] aadhar) {
		this.aadhar = aadhar;
	}

	public String getAadharFileType() {
		return aadharFileType;
	}

	public void setAadharFileType(String aadharFileType) {
		this.aadharFileType = aadharFileType;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public String getLicenseFileType() {
		return licenseFileType;
	}

	public void setLicenseFileType(String licenseFileType) {
		this.licenseFileType = licenseFileType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoFileType() {
		return photoFileType;
	}

	public void setPhotoFileType(String photoFileType) {
		this.photoFileType = photoFileType;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
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

	public Ambulance getAmbulance() {
		return ambulance;
	}

	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

}