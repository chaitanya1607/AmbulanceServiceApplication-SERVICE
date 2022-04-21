package com.mindtree.ambulanceserviceapplication.modules.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.entity.Customer;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerException;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.customer.repository.CustomerRepository;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private Mapper mapper;

	@Override
	public List<CustomerDto> getAllCustomerList() throws CustomerException {
		List<CustomerDto> customers = new ArrayList<>();
		customerRepository.findAll()
				.forEach(customer -> customers.add(mapper.modelMapper().map(customer, CustomerDto.class)));
		if (customers.isEmpty()) {
			throw new CustomerNotFoundException("Customer Details not found");
		} else {
			return customers;
		}
	}

	@Override
	public CustomerDto getCustomerById(long customerId) throws CustomerException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Details not found"));
		return mapper.modelMapper().map(customer, CustomerDto.class);
	}

}
