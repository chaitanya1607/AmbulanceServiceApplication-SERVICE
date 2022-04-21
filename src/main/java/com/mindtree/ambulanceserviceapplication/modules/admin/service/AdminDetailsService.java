package com.mindtree.ambulanceserviceapplication.modules.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ambulanceserviceapplication.modules.admin.repository.AdminRepository;

/**
 * @author priya
 *
 */
@Service
public class AdminDetailsService {

	@Autowired
	AdminRepository adminRepository;

}
