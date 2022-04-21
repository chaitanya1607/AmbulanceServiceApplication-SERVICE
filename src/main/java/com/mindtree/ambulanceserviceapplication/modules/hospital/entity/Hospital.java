package com.mindtree.ambulanceserviceapplication.modules.hospital.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Hospitals")
public class Hospital {

	@Id
	@Column(name = "hospital_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long hospitalId;

	@Schema(description = "Hospital name", required = true)
	@Column(name = "hospital_name")
	private String hospitalName;

	@Schema(description = "Hospital phone number", required = true)
	@Column(name = "phone_number")
	private String phoneNumber;

	@Schema(description = "Hospital address", required = true)
	@Column(name = "hospital_address")
	private String address;

	@Schema(description = "Hospital category", required = true)
	@Column(name = "speciality")
	private String speciality;

	@Schema(description = "Hospital bill", required = false)
	@Column(name = "hospital_bill")
	private Double bill;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Schema(description = "Hospital registration certificate", required = true)
	@Column(name = "hospital_registration_file", length = 16777215)
	private byte[] registrationFile;

	@Schema(description = "Hospital registration certificate file type", required = false)
	@Column(name = "file_type")
	private String registrationFileType;

	public Hospital() {
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
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", speciality=" + speciality + ", bill=" + bill + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", registrationFile=" + Arrays.toString(registrationFile)
				+ ", registrationFileType=" + registrationFileType + "]";
	}

}
