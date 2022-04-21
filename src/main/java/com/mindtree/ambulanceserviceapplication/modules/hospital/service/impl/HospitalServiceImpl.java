package com.mindtree.ambulanceserviceapplication.modules.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalListDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.entity.Hospital;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalServiceException;
import com.mindtree.ambulanceserviceapplication.modules.hospital.repository.HospitalRepository;
import com.mindtree.ambulanceserviceapplication.modules.hospital.service.HospitalService;

@Service
@Validated
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public List<HospitalListDto> getHospitalsList() throws HospitalServiceException {
	/*	List<HospitalDto> hospitals = new ArrayList<>();
		hospitalRepository.findAll()
				.forEach(hospital -> hospitals.add(mapper.modelMapper().map(hospital, HospitalDto.class)));*/
		
		List<HospitalListDto> hospitals = new ArrayList<>();
		hospitalRepository.findAll()
		.forEach(hospital -> hospitals.add(mapper.modelMapper().map(hospital, HospitalListDto.class)));
		
		if (hospitals.isEmpty()) {
			throw new HospitalNotFoundException("Hospitals not found in data base");
		} else {
			return hospitals;
		}
	}

	@Override
	public HospitalDto getHospitalById(long id) throws HospitalServiceException {
		Hospital hospital = null;
		hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id " + id));

		return mapper.modelMapper().map(hospital, HospitalDto.class);
	}

	@Override
	public String addHospital(@Valid HospitalDto hospitalDto) throws HospitalServiceException {
		Hospital hospital = mapper.modelMapper().map(hospitalDto, Hospital.class);
		if (hospital == null) {
			throw new HospitalNotFoundException("Please give hospital details to add in to database");
		} else {
			hospitalRepository.save(hospital);
			return "Hospital is added Successfully";
		}
	}

	@Override
	public String updateHospital(long id, @Valid HospitalDto hospitalDto) throws HospitalServiceException {
		Hospital hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital details not found"));
		hospital.setAddress(hospitalDto.getAddress());
		hospital.setBill(hospitalDto.getBill());
		hospital.setHospitalName(hospitalDto.getHospitalName());
		hospital.setLatitude(hospitalDto.getLatitude());
		hospital.setLongitude(hospitalDto.getLongitude());
		hospital.setPhoneNumber(hospitalDto.getPhoneNumber());
		hospital.setSpeciality(hospitalDto.getSpeciality());
		if (hospitalDto.getRegistrationFile() != null) {
			hospital.setRegistrationFile(hospitalDto.getRegistrationFile());
			hospital.setRegistrationFileType(hospitalDto.getRegistrationFileType());
		}
		hospitalRepository.save(hospital);
		return "Hospital details Updated successfully";

	}

	@Override
	public String removeHospital(long id) throws HospitalServiceException {
		Hospital hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found"));
		hospitalRepository.delete(hospital);
		return "Hospital Deleted Successfully";

	}

	@Override
	public int getHospitalCount() {
		return hospitalRepository.hospitalCount();
	}

}
