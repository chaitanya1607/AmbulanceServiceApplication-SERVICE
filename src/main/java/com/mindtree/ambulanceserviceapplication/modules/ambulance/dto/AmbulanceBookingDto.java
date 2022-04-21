package com.mindtree.ambulanceserviceapplication.modules.ambulance.dto;

import java.util.List;

import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverBookingDto;

public class AmbulanceBookingDto {

	private String vehicalNumber;
	private List<DriverBookingDto> drivers;
	
	public AmbulanceBookingDto() {
		// TODO Auto-generated constructor stub
	}

	public String getVehicalNumber() {
		return vehicalNumber;
	}

	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}

	public List<DriverBookingDto> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverBookingDto> drivers) {
		this.drivers = drivers;
	}
}
