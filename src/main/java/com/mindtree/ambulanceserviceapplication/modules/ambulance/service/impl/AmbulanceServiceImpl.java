package com.mindtree.ambulanceserviceapplication.modules.ambulance.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.*;
import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.AmbulanceNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.DuplicateEntryException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.repository.AmbulanceRepository;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.service.AmbulanceService;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverBookingDto;

@Service
@Validated
public class AmbulanceServiceImpl implements AmbulanceService {

	@Autowired
	private AmbulanceRepository ambulanceRepository;
	@Autowired
	private Mapper mapper;

	private String ambulanceMessage = "Ambulance not found with";

	@Override
	public List<AmbulanceListDto> getAllAmbulanceList() throws AmbulanceNotFoundException {
		List<AmbulanceListDto> ambulancesAvailable = new ArrayList<>();
		ambulanceRepository.findAllByIsDeleted(false)
				.forEach(ambulance -> ambulancesAvailable.add(mapper.modelMapper().map(ambulance, AmbulanceListDto.class)));
		if (ambulancesAvailable.isEmpty()) {
			throw new AmbulanceNotFoundException("Ambulance list is empty in database");
		} else {
			return ambulancesAvailable;
		}
	}

	@Override
	public AmbulanceUpdateDto getAmbulanceByVehicalNumber(String vehicalNumber) throws AmbulanceNotFoundException {
		Ambulance ambulance = null;
		ambulance = ambulanceRepository.findByVehicalNumber(vehicalNumber)
				.orElseThrow(() -> new AmbulanceNotFoundException(ambulanceMessage + vehicalNumber));
		return mapper.modelMapper().map(ambulance, AmbulanceUpdateDto.class);
	}

	@Override
	public String createAmbulance(@Valid AmbulanceDto ambulanceDto) throws IOException, DuplicateEntryException {
		Ambulance ambulance = null;
		ambulance = ambulanceRepository.findByVehicalNumber(ambulanceDto.getVehicalNumber()).orElse(null);
		if (ambulance != null) {
			throw new DuplicateEntryException("Ambulance with " + ambulanceDto.getVehicalNumber() + " already Exist");
		}

		ambulance = mapper.modelMapper().map(ambulanceDto, Ambulance.class);
		ambulanceRepository.save(ambulance);
		return "Ambulance saved into database";
	}

	@Override
	public String updateAmbulance(String vehicalNumber, @Valid AmbulanceDto ambulanceDto)
			throws AmbulanceNotFoundException {

		Ambulance ambulance = ambulanceRepository.findByVehicalNumber(vehicalNumber)
				.orElseThrow(() -> new AmbulanceNotFoundException(ambulanceMessage + vehicalNumber));

		if (ambulanceDto.getOwnerAadhar() == null) {
			ambulanceDto.setOwnerAadhar(ambulance.getOwnerAadhar());
			ambulanceDto.setOwnerAadharFileType(ambulance.getOwnerAadharFileType());
		}
		if (ambulanceDto.getVehicalInsurance() == null) {
			ambulanceDto.setVehicalInsurance(ambulance.getVehicalInsurance());
			ambulanceDto.setVehicalInsuranceFileType(ambulance.getVehicalInsuranceFileType());
		}
		if (ambulanceDto.getrCBook() == null) {
			ambulanceDto.setrCBook(ambulance.getrCBook());
			ambulanceDto.setrCBookFileType(ambulance.getrCBookFileType());
		}
		ambulance = mapper.modelMapper().map(ambulanceDto, Ambulance.class);
		ambulanceRepository.save(ambulance);
		return "Ambulance details Updated successfully";

	}

	@Override
	public String deleteAmbulance(String vehicalNumber) throws AmbulanceNotFoundException {
		Ambulance ambulance = null;
		ambulance = ambulanceRepository.findByVehicalNumber(vehicalNumber).orElseThrow(
				() -> new AmbulanceNotFoundException("Ambulance with " + vehicalNumber + " not found in data base"));
		ambulance.setDeleted(true);
		ambulanceRepository.save(ambulance);
		return "Ambulance Deleted successfully";

	}

	@Override
	public AmbulanceDto getUnassignedAmbulance() {
		Ambulance ambulance = ambulanceRepository.getUnassignedAmbulance();
		if (ambulance == null)
			return null;
		else
			return mapper.modelMapper().map(ambulance, AmbulanceDto.class);

	}

	@Override
	public long getAmbulanceCount() {
		return ambulanceRepository.findCountOfAmbulances();
		
	}

	@Override
	public DriverBookingDto getBookings(String vehicalNumber) {
		return mapper.modelMapper().map(ambulanceRepository.findByVehicalNumber(vehicalNumber),DriverBookingDto.class);
		
	}

}
