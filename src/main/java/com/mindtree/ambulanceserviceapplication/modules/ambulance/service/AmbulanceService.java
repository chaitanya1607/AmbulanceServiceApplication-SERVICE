package com.mindtree.ambulanceserviceapplication.modules.ambulance.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceListDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceUpdateDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.AmbulanceNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.DuplicateEntryException;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverBookingDto;

public interface AmbulanceService {

	List<AmbulanceListDto> getAllAmbulanceList() throws AmbulanceNotFoundException;

	AmbulanceUpdateDto getAmbulanceByVehicalNumber(String vehicalNumber) throws AmbulanceNotFoundException;

	String createAmbulance(@Valid AmbulanceDto ambulance) throws IOException, DuplicateEntryException;

	String updateAmbulance(String vehicalNumber, @Valid AmbulanceDto ambulanceDto) throws AmbulanceNotFoundException;

	String deleteAmbulance(String vehicalNumber) throws AmbulanceNotFoundException;

	AmbulanceDto getUnassignedAmbulance();

	long getAmbulanceCount();

	DriverBookingDto getBookings(String vehicalNumber);

}
