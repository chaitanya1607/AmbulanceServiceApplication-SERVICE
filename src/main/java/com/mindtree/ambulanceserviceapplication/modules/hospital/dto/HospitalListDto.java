package com.mindtree.ambulanceserviceapplication.modules.hospital.dto;

public class HospitalListDto {
	
	private long hospitalId;
	private String hospitalName;
	private String phoneNumber;
	
	
	public HospitalListDto() {
		super();
	}
	public HospitalListDto(long hospitalId, String hospitalName,String phoneNumber) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.phoneNumber = phoneNumber;
		
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
	

}
