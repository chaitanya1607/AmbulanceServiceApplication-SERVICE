package com.mindtree.ambulanceserviceapplication.modules.driver.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mindtree.ambulanceserviceapplication.modules.driver.dto.ApplicantDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverListDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDetailsDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.DriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.NoDriverException;

public interface DriverService {

	List<DriverListDto> getAllDrivers() throws NoDriverException;

	DriverDto getDriverById(long id) throws DriverException;

	DriverDto updateDriver(long id, @Valid DriverDto driverDto) throws DriverException;

	ResponseEntity<Object> addDriver(@Valid DriverDto driverDto);

	DriverDto deleteDriver(long id) throws DriverException;

	List<ApplicantDto> getApplicants();

	String sendMail(long id) throws DriverException;

	String sendRejectMail(long id) throws DriverException;

	ResponseEntity<Object> recoverMail(String email);

	Driver getDriverByName(String name) throws NoDriverException;

	ResponseEntity<Map<String, Object>> updateDriverCoords(long driverId, Double latitude, Double longitude)
			throws NoDriverException;
	
	int getApplicantCount();

	int getDriversCount();

	DriverDetailsDto getDriverDetailsById(long id) throws NoDriverException;
	
	

}
