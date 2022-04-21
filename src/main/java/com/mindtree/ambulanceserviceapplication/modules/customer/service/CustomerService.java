package com.mindtree.ambulanceserviceapplication.modules.customer.service;

import java.util.List;

import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerException;

public interface CustomerService {
	List<CustomerDto> getAllCustomerList() throws CustomerException;

	CustomerDto getCustomerById(long customerId) throws CustomerException;

}
