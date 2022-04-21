package com.mindtree.ambulanceserviceapplication.modules.hospital.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalListDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalServiceException;

@Component
public interface HospitalService {

	List<HospitalListDto> getHospitalsList() throws HospitalServiceException;

	HospitalDto getHospitalById(long id) throws HospitalServiceException;

	String addHospital(@Valid HospitalDto hospitalDto) throws HospitalServiceException;

	String updateHospital(long id, @Valid HospitalDto hospitalDto) throws HospitalServiceException;

	String removeHospital(long id) throws HospitalServiceException;

	int getHospitalCount();

}
