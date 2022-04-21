package com.mindtree.ambulanceserviceapplication.modules.driver.dto;

import java.util.List;

import com.mindtree.ambulanceserviceapplication.modules.customer.entity.Customer;

public class DriverBookingDto {

	private long driverId;
	private List<Customer> customers;
	public DriverBookingDto() {
		super();
	}
	public long getDriverId() {
		return driverId;
	}
	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
	
	
}
