package com.mindtree.ambulanceserviceapplication.modules.driver.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class DriverControllerTest {

	@MockBean
	private DriverService driverService;

	@MockBean
	private CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "enoch1", password = "passWord@1", roles = "DRIVER")
	void testHelloDriver() throws Exception {

		mockMvc.perform(get("/secure/driver")).andExpect(status().is(200)).andExpect(content().string("driver"));

	}

	@Test
	void testGetDriverByName() throws Exception {
		Driver driver = new Driver();
		driver.setFullName("Yashoda");
		driver.setAddress("Tirumala");
		driver.setActivated(true);
		driver.setAssigned(false);
		driver.setBillAmount(100.0);
		doReturn(driver).when(driverService).getDriverByName("Yashoda");
		Driver expected = driverService.getDriverByName("Yashoda");
		mockMvc.perform(get("/get-driver-by-name/Yashoda")).andExpect(status().is(200));
		Assertions.assertEquals(expected, driver);
	}

	@Test
	void testGetCustomerById() throws Exception {
		CustomerDto customer = new CustomerDto();
		customer.setCustomerId(12);
		customer.setBillAmount(100.0);
		customer.setMobileNumber("9642092464");
		customer.setName("Mindtree");
		customer.setServiceDate(new Date(05 - 01 - 2021));
		customer.setLatitude(13.069087);
		customer.setLongitude(80.273783);

		doReturn(customer).when(customerService).getCustomerById(12);
		CustomerDto expected = customerService.getCustomerById(12);

		mockMvc.perform(get("/customers/12")).andExpect(status().is(200));
		Assertions.assertEquals(expected, customer);

	}

	@Test
	void testUpdateDriverCoords() throws Exception {
		Map<String, Object> response = new HashMap<>();
		response.put("Message", "Coordinates updated");
		ResponseEntity<Map<String, Object>> responses = new ResponseEntity<Map<String, Object>>(response,
				HttpStatus.OK);
		doReturn(responses).when(driverService).updateDriverCoords(10, 23.5657, 12.2313);
		mockMvc.perform(put("/updateDriveCoords/10").param("latitude", "23.5657").param("longitude", "12.2313"))
				.andExpect(status().is(200));
	}

}
