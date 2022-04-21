package com.mindtree.ambulanceserviceapplication.modules.ambulance.service.impl;

import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceListDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceUpdateDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.AmbulanceNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.DuplicateEntryException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.repository.AmbulanceRepository;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.service.AmbulanceService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class AmbulanceServiceImplTest {

	@MockBean
	private AmbulanceRepository ambulanceRepository;

	@Autowired
	private AmbulanceService ambulanceService;

	@Autowired
	Mapper mapper;

	@Test
	void testGetAllAmbulanceList() throws AmbulanceNotFoundException {
		List<AmbulanceListDto> ambulances = new ArrayList<AmbulanceListDto>();
		AmbulanceListDto ambulance = new AmbulanceListDto();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerName("Chaitanya");
		ambulances.add(ambulance);
		List<Ambulance> ambulanceList = new ArrayList<Ambulance>();
		Ambulance newAmbulance = new Ambulance();
		newAmbulance.setVehicalNumber("AP 03 AZ 9999");
		newAmbulance.setOwnerNAme("Chaitanya");
		ambulanceList.add(newAmbulance);
		doReturn(ambulanceList).when(ambulanceRepository).findAllByIsDeleted(false);
		List<AmbulanceListDto> expectedAmbulances = ambulanceService.getAllAmbulanceList();
		Assertions.assertEquals(expectedAmbulances.size(), ambulances.size());

	}

	@Test
	void testGetAmbulanceByVehicalNumber() throws AmbulanceNotFoundException {
		Ambulance ambulance = new Ambulance();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		doReturn(Optional.of(ambulance)).when(ambulanceRepository).findByVehicalNumber("AP 03 AZ 9999");
		AmbulanceUpdateDto expectedAmbulance = ambulanceService.getAmbulanceByVehicalNumber("AP 03 AZ 9999");
		Assertions.assertEquals(expectedAmbulance.getVehicalNumber(), ambulance.getVehicalNumber());
	}

	@Test
	void testCreateAmbulance() throws DuplicateEntryException, IOException {
		Ambulance ambulance = new Ambulance();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		String actual = "Ambulance saved into database";
		doReturn(Optional.of(ambulance)).when(ambulanceRepository).findByVehicalNumber("AP 03 AZ 9999");
		doReturn(actual).when(ambulanceRepository).save(ambulance);

		AmbulanceDto ambulanceDto = new AmbulanceDto();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		String expected = ambulanceService.createAmbulance(ambulanceDto);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testUpdateAmbulance() throws AmbulanceNotFoundException {
		Ambulance ambulance = new Ambulance();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		String actual = "Ambulance details Updated successfully";
		doReturn(Optional.of(ambulance)).when(ambulanceRepository).findByVehicalNumber("AP 03 AZ 9999");
		doReturn(actual).when(ambulanceRepository).save(ambulance);

		AmbulanceDto ambulanceDto = new AmbulanceDto();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		String expected = ambulanceService.updateAmbulance("AP 03 AZ 9999", ambulanceDto);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testDeleteAmbulance() throws AmbulanceNotFoundException {
		Ambulance ambulance = new Ambulance();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		String actual = "Ambulance Deleted successfully";
		doReturn(Optional.of(ambulance)).when(ambulanceRepository).findByVehicalNumber("AP 03 AZ 9999");
		ambulance.setDeleted(true);
		String expected = ambulanceService.deleteAmbulance("AP 03 AZ 9999");
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testGetUnassignedAmbulance() {
		Ambulance ambulance = new Ambulance();
		ambulance.setVehicalNumber("AP 03 AZ 9999");
		ambulance.setOwnerNAme("Chaitanya");
		doReturn(ambulance).when(ambulanceRepository).getUnassignedAmbulance();
		AmbulanceDto expectedAmbulance = ambulanceService.getUnassignedAmbulance();
		Assertions.assertEquals(expectedAmbulance.getVehicalNumber(), ambulance.getVehicalNumber());
	}

}
