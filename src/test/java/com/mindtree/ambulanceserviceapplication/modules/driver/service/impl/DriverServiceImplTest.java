package com.mindtree.ambulanceserviceapplication.modules.driver.service.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverListDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.DriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.NoDriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.repository.DriverRepository;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;
import com.mindtree.ambulanceserviceapplication.utility.User;
import com.mindtree.ambulanceserviceapplication.utility.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class DriverServiceImplTest {

	@MockBean
	private DriverRepository driverRepository;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private DriverService driverService;

	@Test
	void testGetAllDrivers() throws NoDriverException {
		List<Driver> drivers = new ArrayList<Driver>();
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setFullName("Chaitanya");
		drivers.add(driver);
		doReturn(drivers).when(driverRepository).findAvailableDriver();
		List<DriverListDto> expectedDrivers = driverService.getAllDrivers();
		Assertions.assertEquals(expectedDrivers.size(), drivers.size());
	}

	@Test
	void testGetDriverById() throws DriverException {
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setFullName("Chaitanya");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 1);
		DriverDto expectedDriver = driverService.getDriverById(1);
		Assertions.assertEquals(expectedDriver.getDriverId(), driver.getDriverId());
	}

	@Test
	void testUpdateDriver() throws DriverException {
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setFullName("Chaitanya");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 1);
		doReturn(driver).when(driverRepository).save(driver);
		DriverDto driver1 = new DriverDto();
		driver1.setDriverId(1);
		driver1.setFullName("Chaitanya");
		driver1 = driverService.updateDriver(1, driver1);
		Assertions.assertEquals(driver1.getDriverId(), driver.getDriverId());

	}

	@Test
	void testAddDriver() {
		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setFullName("Chaitanya");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 1);
		doReturn(driver).when(driverRepository).save(driver);
		DriverDto driver1 = new DriverDto();
		driver1.setDriverId(1);
		driver1.setFullName("Chaitanya");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Please check your email for further details");
		response.put("success", true);
		ResponseEntity<Object> actualResponse = new ResponseEntity<Object>(response, HttpStatus.OK);
		ResponseEntity<Object> expectedResponseEntity = driverService.addDriver(driver1);
		Assertions.assertEquals(expectedResponseEntity, actualResponse);

	}

	@Test
	void testDeleteDriver() throws DriverException {

		Driver driver = new Driver();
		driver.setDriverId(1);
		driver.setFullName("Chaitanya");
		driver.setUserName("Hello");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 1);
		User user = new User();
		doReturn(Optional.of(user)).when(userRepository).findByUserName(driver.getUserName());
		DriverDto expectedDriver = driverService.getDriverById(1);
		Assertions.assertEquals(expectedDriver.getDriverId(), driver.getDriverId());
	}

	@Test
	void testGetApplicants() {

	}

	@Test
	void testSendMail() throws DriverException {
		String actual = "Mail Sent";
		Driver driver = new Driver();
		driver.setDriverId(22);
		driver.setFullName("Chaitanya");
		driver.setEmail("email@domain.com");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 22);
		driver.setActivated(true);
		User user = new User();
		user.setActive(true);
		user.setUserName("Chaitanya");
		user.setPassword("12345");
		user.setRoles("ROLE_DRIVER");
		doReturn(actual).when(userRepository).save(user);
		doReturn(driver).when(driverRepository).save(driver);
		String expected = driverService.sendMail(22);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testSendRejectMail() throws DriverException {
		String actual = "Mail Sent";
		Driver driver = new Driver();
		driver.setDriverId(22);
		driver.setFullName("Chaitanya");
		driver.setEmail("email@domain.com");
		doReturn(Optional.of(driver)).when(driverRepository).findById((long) 22);
		String expected = driverService.sendRejectMail(22);
		doNothing().when(driverRepository).delete(driver);
		Assertions.assertEquals(expected, actual);

	}

	@Test
	void testRecoverMail() {

	}

	@Test
	void testGetDriverByName() {

	}

	@Test
	void testUpdateDriverCoords() {

	}

}
