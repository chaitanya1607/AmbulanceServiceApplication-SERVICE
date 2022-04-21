package com.mindtree.ambulanceserviceapplication.modules.customer.service.impl;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
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

import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.entity.Customer;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerException;
import com.mindtree.ambulanceserviceapplication.modules.customer.repository.CustomerRepository;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Test
	void testGetAllCustomerList() throws CustomerException {
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setBillAmount(100.0);
		customer.setMobileNumber("9642092464");
		customer.setName("Mindtree");
		customer.setServiceDate(new Date(05 - 01 - 2021));
		customer.setLatitude(13.069087);
		customer.setLongitude(80.273783);
		customers.add(customer);
		doReturn(customers).when(customerRepository).findAll();
		List<CustomerDto> expected = customerService.getAllCustomerList();
		Assertions.assertEquals(expected.size(), customers.size());

	}

	@Test
	void testGetCustomerById() throws CustomerException {

		Customer customer = new Customer();
		customer.setBillAmount(100.0);
		customer.setMobileNumber("9642092464");
		customer.setName("Mindtree");
		customer.setServiceDate(new Date(05 - 01 - 2021));
		customer.setLatitude(13.069087);
		customer.setLongitude(80.273783);
		customer.setCustomerId(2);
		doReturn(Optional.of(customer)).when(customerRepository).findById((long) 2);
		CustomerDto expected = customerService.getCustomerById(2);
		Assertions.assertEquals(expected.getCustomerId(), customer.getCustomerId());
	}
}
