package com.mindtree.ambulanceserviceapplication.modules.hospital.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

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

import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalListDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.entity.Hospital;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalServiceException;
import com.mindtree.ambulanceserviceapplication.modules.hospital.repository.HospitalRepository;
import com.mindtree.ambulanceserviceapplication.modules.hospital.service.HospitalService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class HospitalServiceImplTest {

	@MockBean
	private HospitalRepository hospitalRepository;

	@Autowired
	private HospitalService hospitalService;

	@Test
	void testGetHospitalsList() throws HospitalServiceException {
		List<Hospital> hospitals = new ArrayList<Hospital>();
		List<HospitalListDto> expectedHospitals;
		Hospital hospitalDto = new Hospital();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitals.add(hospitalDto);
		doReturn(hospitals).when(hospitalRepository).findAll();
		expectedHospitals = hospitalService.getHospitalsList();
		Assertions.assertEquals(expectedHospitals.size(), hospitals.size());

	}

	@Test
	void testGetHospitalById() throws HospitalServiceException {
		Hospital hospitalDto = new Hospital();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitalDto.setHospitalId(99999);
		doReturn(Optional.of(hospitalDto)).when(hospitalRepository).findById((long) 99999);
		HospitalDto expected = hospitalService.getHospitalById(99999);
		Assertions.assertEquals(expected.getHospitalId(), hospitalDto.getHospitalId());

	}

	@Test
	void testAddHospital() throws HospitalServiceException {
		String actual = "Hospital is added Successfully";
		Hospital hospitalDto = new Hospital();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitalDto.setHospitalId(99999);

		HospitalDto hospital = new HospitalDto();
		hospital.setHospitalName("Hanu hospital");
		hospital.setPhoneNumber("9876567223");
		hospital.setLatitude(23.12213);
		hospital.setLongitude(12.332222);
		hospital.setAddress("Thanjavur, TamilNadu");
		hospital.setBill(400.0);
		hospital.setHospitalId(99999);
		doReturn(actual).when(hospitalRepository).save(hospitalDto);
		String expected = hospitalService.addHospital(hospital);
		Assertions.assertEquals(expected, actual);

	}

	@Test
	void testUpdateHospital() throws HospitalServiceException {
		Hospital hospitalDto = new Hospital();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitalDto.setHospitalId(99999);

		String actual = "Hospital details Updated successfully";

		doReturn(Optional.of(hospitalDto)).when(hospitalRepository).findById((long) 99999);
		doReturn(hospitalDto).when(hospitalRepository).save(hospitalDto);

		HospitalDto hospital = new HospitalDto();
		hospital.setHospitalName("Hanu hospital");
		hospital.setPhoneNumber("9876567223");
		hospital.setLatitude(23.12213);
		hospital.setLongitude(12.332222);
		hospital.setAddress("Thanjavur, TamilNadu");
		hospital.setBill(400.0);
		hospital.setHospitalId(99999);

		String expected = hospitalService.updateHospital(99999, hospital);
		Assertions.assertEquals(expected, actual);

	}

	@Test
	void testRemoveHospital() throws HospitalServiceException {

		String actual = "Hospital Deleted Successfully";
		Hospital hospitalDto = new Hospital();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitalDto.setHospitalId(99999);
		doReturn(Optional.of(hospitalDto)).when(hospitalRepository).findById((long) 99999);
		doNothing().when(hospitalRepository).delete(hospitalDto);

		String expected = hospitalService.removeHospital(99999);
		Assertions.assertEquals(expected, actual);

	}

}
