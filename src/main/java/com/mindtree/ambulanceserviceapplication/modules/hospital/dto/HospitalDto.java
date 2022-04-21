package com.mindtree.ambulanceserviceapplication.modules.hospital.dto;

import java.util.Arrays;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class HospitalDto {

	private long hospitalId;

	@NotNull(message = "hospital name is required")
	@NotBlank
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "hospital name can have only alpabets and spaces")
	@Size(min = 3, max = 20, message = "hospital name can have minimum of 3 characters and maximum of 20 characters")
	private String hospitalName;

	@NotNull(message = "phonenumber is required")
	@Pattern(regexp = "^[0-9]{10}", message = "phonenumber can have only numeric characters")
	@Size(min = 10, max = 10, message = "phonenumber can have only 10 numbers")
	private String phoneNumber;

	@NotNull(message = "address is required")
	@Size(min = 10, max = 100, message = "hospital address can have minimum of 10 characters and maximum of 100 characters")
	private String address;

	@NotNull(message = "speciality type is required")
	@Pattern(regexp = "^(general|superSpeciality)$", message = "this field takes only string 'general' or 'superSpeciality' (case-sensitive)")
	private String speciality;

	private Double bill;

	@NotNull(message = "latitude is required")
	@DecimalMin(value = "-90.0", inclusive = true, message = "latitude can have minimum value of -90.0")
	@DecimalMax(value = "90.0", inclusive = true, message = "latitude can have maximum value of 90.0")
	private Double latitude;

	@NotNull(message = "longitude is required")
	@DecimalMin(value = "-180.0", inclusive = true, message = "longitude can have minimum value of -90.0")
	@DecimalMax(value = "180.0", inclusive = true, message = "longitude can have maximum value of 90.0")
	private Double longitude;

	private byte[] registrationFile;
	private String registrationFileType;

	public HospitalDto() {
		super();

	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
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

	public byte[] getRegistrationFile() {
		return registrationFile;
	}

	public void setRegistrationFile(byte[] registrationFile) {
		this.registrationFile = registrationFile;
	}

	public String getRegistrationFileType() {
		return registrationFileType;
	}

	public void setRegistrationFileType(String registrationFileType) {
		this.registrationFileType = registrationFileType;
	}

	@Override
	public String toString() {
		return "HospitalDto [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", speciality=" + speciality + ", bill=" + bill + ", latitude="
				+ latitude + ", longitude=" + longitude + ", registrationFile=" + Arrays.toString(registrationFile)
				+ ", registrationFileType=" + registrationFileType + "]";
	}

}
