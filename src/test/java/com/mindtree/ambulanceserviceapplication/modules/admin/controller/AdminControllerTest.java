package com.mindtree.ambulanceserviceapplication.modules.admin.controller;
stapackage com.mindtree.ambulanceserviceapplication.modules.admin.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceListDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceUpdateDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.service.AmbulanceService;
import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverListDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalListDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.service.HospitalService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class AdminControllerTest {

	@MockBean
	private AmbulanceService ambulanceService;

	@MockBean
	private HospitalService hospitalService;

	@MockBean
	private DriverService driverService;

	@MockBean
	private CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testSecuredHello() throws Exception {

		mockMvc.perform(get("/secure/admin")).andExpect(status().is(200)).andExpect(content().string("admin"));

	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetAllAmbulanceList() throws Exception {
		List<AmbulanceListDto> ambulances = new ArrayList<AmbulanceListDto>();
		List<AmbulanceListDto> expectedAmbulances;
		AmbulanceListDto ambulanceDto = new AmbulanceListDto();
		ambulanceDto.setVehicalNumber("AP 03 AZ 9999");
		ambulanceDto.setOwnerName("Chaitanya");
		ambulances.add(ambulanceDto);
		doReturn(ambulances).when(ambulanceService).getAllAmbulanceList();
		expectedAmbulances = ambulanceService.getAllAmbulanceList();
		mockMvc.perform(get("/ambulances")).andExpect(status().is(200));
		Assertions.assertEquals(expectedAmbulances, ambulances);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetAmbulanceByVehicalNumber() throws Exception {
		AmbulanceUpdateDto ambulanceDto = new AmbulanceUpdateDto();
		AmbulanceUpdateDto expectedAmbulance = new AmbulanceUpdateDto();
		ambulanceDto.setVehicalNumber("AP 03 AZ 9999");
		ambulanceDto.setOwnerName("Chaitanya");
		doReturn(ambulanceDto).when(ambulanceService).getAmbulanceByVehicalNumber("AP 03 AZ 9999");
		expectedAmbulance = (AmbulanceUpdateDto) ambulanceService.getAmbulanceByVehicalNumber("AP 03 AZ 9999");
		mockMvc.perform(get("/ambulances/AP 03 AZ 9999")).andExpect(status().is(200));
		Assertions.assertEquals(expectedAmbulance, ambulanceDto);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testCreateAmbulance() throws JsonProcessingException, Exception {
		AmbulanceDto ambulanceDto = new AmbulanceDto();
		ambulanceDto.setAssigned(false);
		ambulanceDto.setDeleted(false);
		ambulanceDto.setInsuranceValidity(new Date(2021 - 12 - 30));
		ambulanceDto.setOwnerAadharNumber("2345 6789 1029");
		ambulanceDto.setOwnerAddress("7-7 chittoor, andhra");
		ambulanceDto.setOwnerName("Chaitanya Reddy N G");
		ambulanceDto.setVehicalNumber("AP 04 AZ 86953");
		ambulanceDto.setVehicalRegistrationDate(new Date(2020 - 12 - 22));

		byte[] aadharFile;
		byte[] insuranceFile;
		byte[] rcFile;

		FileInputStream fin = new FileInputStream(new File("src/test/resources/3.pdf"));
		FileInputStream fin1 = new FileInputStream(new File("src/test/resources/1.pdf"));
		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/2.pdf"));

		MockMultipartFile ownerAaadharFile = new MockMultipartFile("ownerAadhar", fin);
		MockMultipartFile vehicleInsuranceFile = new MockMultipartFile("vehicalInsurance", fin1);
		MockMultipartFile rcBookFile = new MockMultipartFile("rCBook", fin2);

		aadharFile = ownerAaadharFile.getBytes();
		insuranceFile = vehicleInsuranceFile.getBytes();
		rcFile = rcBookFile.getBytes();
		ambulanceDto.setOwnerAadhar(aadharFile);
		ambulanceDto.setOwnerAadharFileType(ownerAaadharFile.getContentType());
		ambulanceDto.setVehicalInsurance(insuranceFile);
		ambulanceDto.setVehicalInsuranceFileType(vehicleInsuranceFile.getContentType());
		ambulanceDto.setrCBook(rcFile);
		ambulanceDto.setrCBookFileType(rcBookFile.getContentType());

		String actualString = "Ambulance saved into database";
		doReturn(actualString).when(ambulanceService).createAmbulance(ambulanceDto);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/ambulance").file(ownerAaadharFile).file(vehicleInsuranceFile)
				.file(rcBookFile).contentType(MediaType.MULTIPART_FORM_DATA)
				.param("ambulance", new ObjectMapper().writeValueAsString(ambulanceDto))).andExpect(status().is(200));
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testUpdateAmbulance() throws JsonProcessingException, Exception {
		AmbulanceDto ambulanceDto = new AmbulanceDto();
		ambulanceDto.setAssigned(false);
		ambulanceDto.setDeleted(false);
		ambulanceDto.setInsuranceValidity(new Date(2021 - 12 - 30));
		ambulanceDto.setOwnerAadharNumber("2345 6789 1029");
		ambulanceDto.setOwnerAddress("7-7 chittoor, andhra");
		ambulanceDto.setOwnerName("Chaitanya Reddy N G");
		ambulanceDto.setVehicalNumber("AP 03 AZ 68953");
		ambulanceDto.setVehicalRegistrationDate(new Date(2020 - 12 - 22));

		byte[] aadharFile;
		byte[] insuranceFile;
		byte[] rcFile;

		FileInputStream fin = new FileInputStream(new File("src/test/resources/3.pdf"));

		FileInputStream fin1 = new FileInputStream(new File("src/test/resources/1.pdf"));
		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/2.pdf"));

		MockMultipartFile ownerAaadharFile = new MockMultipartFile("ownerAadhar", fin);
		MockMultipartFile vehicleInsuranceFile = new MockMultipartFile("vehicalInsurance", fin1);
		MockMultipartFile rcBookFile = new MockMultipartFile("rCBook", fin2);

		aadharFile = ownerAaadharFile.getBytes();
		insuranceFile = vehicleInsuranceFile.getBytes();
		rcFile = rcBookFile.getBytes();
		ambulanceDto.setOwnerAadhar(aadharFile);
		ambulanceDto.setOwnerAadharFileType(ownerAaadharFile.getContentType());
		ambulanceDto.setVehicalInsurance(insuranceFile);
		ambulanceDto.setVehicalInsuranceFileType(vehicleInsuranceFile.getContentType());
		ambulanceDto.setrCBook(rcFile);
		ambulanceDto.setrCBookFileType(rcBookFile.getContentType());

		String actualString = "Ambulance updated successfully";
		doReturn(actualString).when(ambulanceService).updateAmbulance("AP 03 AZ 68953", ambulanceDto);
		mockMvc.perform(put("/ambulances/AP 03 AZ 68953").contentType(MediaType.MULTIPART_FORM_DATA).param("ambulance",
				new ObjectMapper().writeValueAsString(ambulanceDto))).andExpect(status().is(200));
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testDeleteAmbulance() throws Exception {
		AmbulanceUpdateDto ambulanceDto = new AmbulanceUpdateDto();
		AmbulanceUpdateDto expectedAmbulance = new AmbulanceUpdateDto();
		ambulanceDto.setVehicalNumber("AP 03 AZ 9999");
		ambulanceDto.setOwnerName("Chaitanya");
		doReturn(ambulanceDto).when(ambulanceService).getAmbulanceByVehicalNumber("AP 03 AZ 9999");
		expectedAmbulance = (AmbulanceUpdateDto) ambulanceService.getAmbulanceByVehicalNumber("AP 03 AZ 9999");
		mockMvc.perform(delete("/ambulances/AP 03 AZ 9999")).andExpect(status().is(200));
		Assertions.assertEquals(expectedAmbulance, ambulanceDto);
	}

	@Test
	void testRecoverMail() throws Exception {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Please check your email for further details");
		response.put("success", true);
		ResponseEntity<Object> actualResponse = new ResponseEntity<Object>(response, HttpStatus.OK);
		doReturn(actualResponse).when(driverService).recoverMail("email@domain.com");
		ResponseEntity<Object> expected = driverService.recoverMail("email@domain.com");
		mockMvc.perform(get("/recovery-mail/email@domain.com")).andExpect(status().is(200));
		Assertions.assertEquals(expected, actualResponse);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetHospitalsList() throws Exception {
		List<HospitalDto> hospitals = new ArrayList<HospitalDto>();
		List<HospitalListDto> expectedHospitals;
		HospitalDto hospitalDto = new HospitalDto();
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		hospitals.add(hospitalDto);
		doReturn(hospitals).when(hospitalService).getHospitalsList();
		expectedHospitals = hospitalService.getHospitalsList();
		mockMvc.perform(get("/hospitalsList")).andExpect(status().is(200));
		Assertions.assertEquals(expectedHospitals, hospitals);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetHospitalById() throws Exception {
		HospitalDto hospitalDto = new HospitalDto();
		HospitalDto expectedHospital = new HospitalDto();
		hospitalDto.setHospitalId(100);
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		doReturn(hospitalDto).when(hospitalService).getHospitalById(100);
		expectedHospital = (HospitalDto) hospitalService.getHospitalById(100);
		mockMvc.perform(get("/getHospital/100")).andExpect(status().is(200));
		Assertions.assertEquals(expectedHospital, hospitalDto);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testAddHospital() throws Exception {

		HospitalDto hospitalDto = new HospitalDto();
		hospitalDto.setHospitalName("Yashoda");
		hospitalDto.setHospitalId(100);
		hospitalDto.setPhoneNumber("9878651234");
		hospitalDto.setAddress("Andhra pradesh");
		hospitalDto.setSpeciality("general");
		hospitalDto.setBill(0.0);
		hospitalDto.setLatitude(13.069087);
		hospitalDto.setLongitude(80.273783);

		byte[] regFile;
		FileInputStream fin = new FileInputStream(new File("src/test/resources/Happy anniversary.pdf"));

		MockMultipartFile mutliFile = new MockMultipartFile("registrationFile", fin);

		regFile = mutliFile.getBytes();
		hospitalDto.setRegistrationFile(regFile);
		hospitalDto.setRegistrationFileType(mutliFile.getContentType());
		String responses = "Hospital is added Successfully";

		doReturn(responses).when(hospitalService).addHospital(hospitalDto);

		String expectedHospital = hospitalService.addHospital(hospitalDto);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/addHospital").file(mutliFile)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("hospital", new ObjectMapper().writeValueAsString(hospitalDto))).andExpect(status().is(200));

		Assertions.assertEquals(expectedHospital, responses);

	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testUpdateHospital() throws Exception {

		HospitalDto hospitalDto = new HospitalDto();
		hospitalDto.setHospitalId(10);
		hospitalDto.setHospitalName("Yashoda");

		byte[] regFile;
		FileInputStream fin = new FileInputStream(new File("src/test/resources/3.pdf"));
		MockMultipartFile mutliFile = new MockMultipartFile("registrationFile", fin);
		regFile = mutliFile.getBytes();
		hospitalDto.setRegistrationFile(regFile);
		hospitalDto.setRegistrationFileType(mutliFile.getContentType());

		hospitalDto.setPhoneNumber("9878651234");
		hospitalDto.setAddress("Andhra pradesh");
		hospitalDto.setSpeciality("general");

		hospitalDto.setBill(0.0);
		hospitalDto.setLatitude(13.069087);
		hospitalDto.setLongitude(80.273783);
		doReturn("Hospital details Updated successfully").when(hospitalService).updateHospital(10, hospitalDto);

		mockMvc.perform(put("/updateHospital/10").contentType(MediaType.MULTIPART_FORM_DATA).param("hospital",
				new ObjectMapper().writeValueAsString(hospitalDto))).andExpect(status().is(200));

	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testRemoveHospital() throws Exception {
		HospitalDto hospitalDto = new HospitalDto();

		hospitalDto.setHospitalId(100);
		hospitalDto.setHospitalName("Hanu hospital");
		hospitalDto.setPhoneNumber("9876567223");
		hospitalDto.setLatitude(23.12213);
		hospitalDto.setLongitude(12.332222);
		hospitalDto.setAddress("Thanjavur, TamilNadu");
		hospitalDto.setBill(400.0);
		String actual = "Hospital Deleted Successfully";
		doReturn(actual).when(hospitalService).removeHospital(100);
		String expected = hospitalService.removeHospital(100);
		mockMvc.perform(delete("/deleteHospital/100")).andExpect(status().is(200));
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetAllDrivers() throws Exception {
		List<DriverDto> drivers = new ArrayList<DriverDto>();
		List<DriverListDto> expectedDrivers;
		DriverDto driverDto = new DriverDto();
		driverDto.setEmail("rajarajan@gmail.com");
		driverDto.setAadharNumber("4521 9866 8283");
		driverDto.setActivated(true);
		drivers.add(driverDto);
		doReturn(drivers).when(driverService).getAllDrivers();
		expectedDrivers = driverService.getAllDrivers();
		mockMvc.perform(get("/drivers")).andExpect(status().is(200));
		Assertions.assertEquals(expectedDrivers, drivers);
	}

	@Test

	void testRegisterDriver() throws JsonProcessingException, Exception {
		DriverDto driverDto = new DriverDto();
		driverDto.setDriverId(99999);
		driverDto.setDrivingLicenceNumber("TN99 20876543213");
		driverDto.setDateOfBirth("1995-01-01");
		driverDto.setEmail("email@domain.com");
		driverDto.setFullName("FullName");
		driverDto.setGender("male");
		driverDto.setMobileNumber("999999999999");
		driverDto.setUserName("username1");
		driverDto.setPassword("passWord1@");
		driverDto.setPaymentRecieved(true);
		FileInputStream fin = new FileInputStream(new File("src/test/resources/Happy.pdf"));
		MockMultipartFile aadharFile = new MockMultipartFile("aadhar", fin);

		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/Lazy loading Of Angular Modules.pdf"));
		MockMultipartFile licenseFile = new MockMultipartFile("license", fin2);

		FileInputStream img = new FileInputStream(new File("src/test/resources/applicants.png"));
		MockMultipartFile photoFile = new MockMultipartFile("photo", img);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Please check your email for further details");
		response.put("success", true);

		ResponseEntity<Object> actualResponse = new ResponseEntity<Object>(response, HttpStatus.OK);

		doReturn(actualResponse).when(driverService).addDriver(driverDto);

		ResponseEntity<Object> expectedResponse = driverService.addDriver(driverDto);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/add-driver").file(aadharFile).file(photoFile)
				.file(licenseFile).contentType(MediaType.MULTIPART_FORM_DATA)
				.param("driver", new ObjectMapper().writeValueAsString(driverDto))).andExpect(status().is(200));

		System.out.println("Expected" + expectedResponse.getBody());
		Assertions.assertEquals(expectedResponse.getBody(), actualResponse.getBody());

	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetDriverById() throws Exception {
		DriverDto driver = new DriverDto();
		DriverDto expectedDriver = new DriverDto();
		driver.setDriverId(99999);
		driver.setFullName("Enoch Ribin");
		doReturn(driver).when(driverService).getDriverById(99999);
		expectedDriver = (DriverDto) driverService.getDriverById(99999);
		mockMvc.perform(get("/get-driver/99999")).andExpect(status().is(200));
		Assertions.assertEquals(expectedDriver, driver);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testUpdateDriver() throws Exception {
		DriverDto driverDto = new DriverDto();
		driverDto.setDriverId(99999);
		driverDto.setDrivingLicenceNumber("TN99 20876543213");
		driverDto.setDateOfBirth("1995-01-01");
		driverDto.setEmail("email@domain.com");
		driverDto.setFullName("FullName");
		driverDto.setGender("male");
		driverDto.setMobileNumber("999999999999");
		driverDto.setUserName("username1");
		driverDto.setPassword("passWord1@");
		FileInputStream fin = new FileInputStream(new File("src/test/resources/applicants.png"));
		FileInputStream fin1 = new FileInputStream(new File("src/test/resources/1.pdf"));
		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/2.pdf"));
		MockMultipartFile aadhar = new MockMultipartFile("aadhar", fin1);
		MockMultipartFile photo = new MockMultipartFile("photo", fin);
		MockMultipartFile license = new MockMultipartFile("license", fin2);
		byte[] aadharFile = aadhar.getBytes();
		byte[] image = photo.getBytes();
		byte[] licenseFile = license.getBytes();
		driverDto.setAadhar(aadharFile);
		driverDto.setPhoto(image);
		driverDto.setLicense(licenseFile);
		doReturn(driverDto).when(driverService).getDriverById(99999);
		driverDto.setAadharNumber("4747 4747 4747");
		driverDto.setAddress("address1");
		driverDto.setEvocCourse(true);
		driverDto.setCriminalRecord(false);
		doReturn(driverDto).when(driverService).updateDriver(99999, driverDto);
		mockMvc.perform(put("/update-driver/9999").contentType(MediaType.MULTIPART_FORM_DATA).param("driver",
				new ObjectMapper().writeValueAsString(driverDto))).andExpect(status().is(200));
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testDeleteDriver() throws Exception {
		DriverDto driver = new DriverDto();
		DriverDto expectedDriver = new DriverDto();
		driver.setDriverId(99999);
		driver.setFullName("Enoch Ribin");
		doReturn(driver).when(driverService).deleteDriver(99999);
		expectedDriver = (DriverDto) driverService.deleteDriver(99999);
		mockMvc.perform(delete("/delete-driver/99999")).andExpect(status().is(200));
		Assertions.assertEquals(expectedDriver, driver);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetApplicantList() throws Exception {
		List<DriverDto> drivers = new ArrayList<DriverDto>();
		List<DriverListDto> expectedDrivers;
		DriverDto driverDto = new DriverDto();
		driverDto.setEmail("rajarajan@gmail.com");
		driverDto.setAadharNumber("4521 9866 8283");
		driverDto.setActivated(false);
		drivers.add(driverDto);
		doReturn(drivers).when(driverService).getAllDrivers();
		expectedDrivers = driverService.getAllDrivers();
		mockMvc.perform(get("/drivers")).andExpect(status().is(200));
		Assertions.assertEquals(expectedDrivers, drivers);
	}

	@Test
	void testSendAcceptMail() throws Exception {
		String actual = "Mail Sent";
		doReturn(actual).when(driverService).sendMail(1);
		String expect = driverService.sendMail(1);
		mockMvc.perform(get("/accept/1")).andExpect(status().is(200));
		Assertions.assertEquals(expect, actual);
	}

	@Test
	void testSendMailReject() throws Exception {
		String actual = "Mail Sent";
		doReturn(actual).when(driverService).sendRejectMail(1);
		String expect = driverService.sendRejectMail(1);
		mockMvc.perform(get("/reject/1")).andExpect(status().is(200));
		Assertions.assertEquals(expect, actual);
	}

	@Test
	@WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
	void testGetAllCustomerList() throws Exception {
		List<CustomerDto> customers = new ArrayList<CustomerDto>();
		CustomerDto customer = new CustomerDto();
		customer.setBillAmount(100.0);
		customer.setMobileNumber("9642092464");
		customer.setName("Mindtree");
		customer.setServiceDate(new Date(05 - 01 - 2021));
		customer.setLatitude(13.069087);
		customer.setLongitude(80.273783);
		customers.add(customer);
		doReturn(customers).when(customerService).getAllCustomerList();
		List<CustomerDto> expected = customerService.getAllCustomerList();
		mockMvc.perform(get("/customers")).andExpect(status().is(200));
		Assertions.assertEquals(expected, customers);

	}

	@Test
	void testAllotAmbulanceToDriver() throws Exception {

		DriverDto driverDto = new DriverDto();
		driverDto.setDriverId(99999);
		driverDto.setDrivingLicenceNumber("TN99 20876543213");
		driverDto.setDateOfBirth("1995-01-01");
		driverDto.setEmail("email@domain.com");
		driverDto.setFullName("FullName");
		driverDto.setGender("male");
		driverDto.setMobileNumber("999999999999");
		driverDto.setUserName("username1");
		driverDto.setPassword("passWord1@");
		FileInputStream fin = new FileInputStream(new File("src/test/resources/applicants.png"));
		FileInputStream fin1 = new FileInputStream(new File("src/test/resources/1.pdf"));
		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/2.pdf"));
		MockMultipartFile aadhar = new MockMultipartFile("aadhar", fin1);
		MockMultipartFile photo = new MockMultipartFile("photo", fin);
		MockMultipartFile license = new MockMultipartFile("license", fin2);
		byte[] aadharFile = aadhar.getBytes();
		byte[] image = photo.getBytes();
		byte[] licenseFile = license.getBytes();
		driverDto.setAadhar(aadharFile);
		driverDto.setPhoto(image);
		driverDto.setLicense(licenseFile);
		doReturn(driverDto).when(driverService).getDriverById(99999);
		driverDto.setAadharNumber("4747 4747 4747");
		driverDto.setAddress("address1");
		driverDto.setEvocCourse(true);
		driverDto.setCriminalRecord(false);
		doReturn(driverDto).when(driverService).updateDriver(99999, driverDto);
		mockMvc.perform(get("/allotAmbulance/99999")).andExpect(status().is(200));
	}

	@Test
	void testSubmitAmbulanceByDriver() throws Exception {
		DriverDto driverDto = new DriverDto();
		driverDto.setDriverId(99999);
		driverDto.setDrivingLicenceNumber("TN99 20876543213");
		driverDto.setDateOfBirth("1995-01-01");
		driverDto.setEmail("email@domain.com");
		driverDto.setFullName("FullName");
		driverDto.setGender("male");
		driverDto.setMobileNumber("999999999999");
		driverDto.setUserName("username1");
		driverDto.setPassword("passWord1@");
		FileInputStream fin = new FileInputStream(new File("src/test/resources/applicants.png"));
		FileInputStream fin1 = new FileInputStream(new File("src/test/resources/1.pdf"));
		FileInputStream fin2 = new FileInputStream(new File("src/test/resources/2.pdf"));
		MockMultipartFile aadhar = new MockMultipartFile("aadhar", fin1);
		MockMultipartFile photo = new MockMultipartFile("photo", fin);
		MockMultipartFile license = new MockMultipartFile("license", fin2);
		byte[] aadharFile = aadhar.getBytes();
		byte[] image = photo.getBytes();
		byte[] licenseFile = license.getBytes();
		driverDto.setAadhar(aadharFile);
		driverDto.setPhoto(image);
		driverDto.setLicense(licenseFile);
		doReturn(driverDto).when(driverService).getDriverById(99999);
		Ambulance ambulance = new Ambulance();
		driverDto.setAmbulance(ambulance);
		driverDto.setAadharNumber("4747 4747 4747");
		driverDto.setAddress("address1");
		driverDto.setEvocCourse(true);
		driverDto.setCriminalRecord(false);
		doReturn(driverDto).when(driverService).updateDriver(99999, driverDto);
		mockMvc.perform(get("/submitAmbulance/99999")).andExpect(status().is(200));
	}

}
