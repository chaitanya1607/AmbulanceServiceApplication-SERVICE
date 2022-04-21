package com.mindtree.ambulanceserviceapplication.modules.driver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.ambulanceserviceapplication.exception.AmbulanceApplicationException;
import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerException;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.DriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.NoDriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController

@CrossOrigin(origins = "https://jyoti-ambulanceservice-sep-2020-client-dev.azurewebsites.net")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("")
public class DriverController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	DriverService driverService;

	@Operation(summary = "This method authenticates the driver", description = "takes driver credentials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })

	@PreAuthorize("hasRole('DRIVER')")
	@GetMapping("/secure/driver")
	public String helloDriver() {
		return "driver";

	}

	@Operation(summary = "This method returns the driver by Username", description = "gets the driver based on the driver UserName")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@GetMapping("/get-driver-by-name/{name}")
	public Driver getDriverByName(@PathVariable(value = "name") String name) throws AmbulanceApplicationException {
		try {
			return driverService.getDriverByName(name);
		} catch (NoDriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}

	@Operation(summary = "This method fetches the customer details from the DataBase", description = "This method takes customarId as a parameter")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched the customer from DataBase", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data Not Found", content = @Content)

	})

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = "customerId") long customerId)
			throws AmbulanceApplicationException {
		CustomerDto customerDto;
		try {
			customerDto = customerService.getCustomerById(customerId);
		} catch (CustomerException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}

	@Operation(summary = "This method updates the driver coordinates", description = "This method periodically update the driver position")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })

	@PutMapping("/updateDriveCoords/{driverId}")
	public ResponseEntity<Map<String, Object>> updateDriverCoords(@PathVariable(value = "driverId") long driverId,
			@RequestParam(value = "latitude") Double latitude, @RequestParam(value = "longitude") Double longitude) {
		try {
			return driverService.updateDriverCoords(driverId, latitude, longitude);
		} catch (DriverException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("Message", "Coordinates not updated");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}
