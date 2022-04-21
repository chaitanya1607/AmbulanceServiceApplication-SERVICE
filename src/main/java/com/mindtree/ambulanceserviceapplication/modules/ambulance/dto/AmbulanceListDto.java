package com.mindtree.ambulanceserviceapplication.modules.ambulance.dto;

public class AmbulanceListDto {
	
	private long ambulanceId;
	private String vehicalNumber;
	private String ownerName;
	public AmbulanceListDto() {
		super();
	}
	public String getVehicalNumber() {
		return vehicalNumber;
	}
	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public long getAmbulanceId() {
		return ambulanceId;
	}
	public void setAmbulanceId(long ambulanceId) {
		this.ambulanceId = ambulanceId;
	}
   
}
