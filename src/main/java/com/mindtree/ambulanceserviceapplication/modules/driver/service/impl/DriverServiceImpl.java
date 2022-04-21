package com.mindtree.ambulanceserviceapplication.modules.driver.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.mindtree.ambulanceserviceapplication.configuration.email.EmailCfg;
import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.ApplicantDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverListDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDetailsDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.DriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.NoDriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.repository.DriverRepository;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;
import com.mindtree.ambulanceserviceapplication.utility.User;
import com.mindtree.ambulanceserviceapplication.utility.UserRepository;

@Service
@Validated
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverRepository driverRepository;
	@Autowired
	private Mapper mapper;
	@Autowired
	private EmailCfg emailCfg;

	private String notFound = "Driver not found";
	private String success = "success";
	private String message = "message";
	private String dear = "Dear ";
	private String regards = "\n\nRegards,\nAmbulance Service Team";
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<DriverListDto> getAllDrivers() throws NoDriverException {
		/*
		List<Driver> drivers = new ArrayList<>();
		driverRepository.findAvailableDriver().forEach(drivers::add);
		*/
		List<Driver> drivers = new ArrayList<>();
		driverRepository.findAvailableDriver().forEach(drivers::add);
		List<DriverListDto> driverDto = new ArrayList<>();
		
		for (Driver driver : drivers)
			driverDto.add(mapper.modelMapper().map(driver, DriverListDto.class));
		if (driverDto.isEmpty()) {
			throw new NoDriverException("Drivers not present in database");
		}
		return driverDto;
	}

	@Override
	public DriverDto getDriverById(long id) throws DriverException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException(notFound));
		return mapper.modelMapper().map(driver, DriverDto.class);
	}

	@Override
	public DriverDto updateDriver(long id, @Valid DriverDto driverDto) throws DriverException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException(notFound));
		if (driverDto.getAadhar() == null) {
			driverDto.setAadhar(driver.getAadhar());
			driverDto.setAadharFileType(driver.getAadharFileType());
		}
		if (driverDto.getLicense() == null) {
			driverDto.setLicense(driver.getLicense());
			driverDto.setLicenseFileType(driver.getLicenseFileType());
		}
		if (driverDto.getPhoto() == null) {
			driverDto.setPhoto(driver.getPhoto());
			driverDto.setPhotoFileType(driver.getPhotoFileType());
		}
		driver = mapper.modelMapper().map(driverDto, Driver.class);
		driverRepository.save(driver);
		return driverDto;
	}

	
	

	@Override
	public ResponseEntity<Object> addDriver(@Valid DriverDto driverDto) {
		Map<String, Object> response = new HashMap<>();
		Driver driver = driverRepository.findByUserName(driverDto.getUserName()).orElse(null);
		Driver addedDriver = mapper.modelMapper().map(driverDto, Driver.class);
		Driver email = driverRepository.findByEmail(driverDto.getEmail());
		Driver aadhar = driverRepository.findByAadharNumber(driverDto.getAadharNumber());
		Driver license = driverRepository.findByDrivingLicenceNumber(driverDto.getDrivingLicenceNumber());
		response.put(success, false);
		if (aadhar != null) {
			response.put(message, "Aadhar already present");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		if (license != null) {
			response.put(message, "Driving license already present");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		if (driver != null) {
			response.put(message, "Username already taken.Try new Username");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else if (email != null) {
			response.put(message, "Email ID is already present.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		driverRepository.save(addedDriver);
		response.put(success, true);
		response.put(message, "Please check your email for further details");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public DriverDto deleteDriver(long id) throws DriverException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException("Driver Not Found"));
		DriverDto driverDto = mapper.modelMapper().map(driver, DriverDto.class);
		driver.setDeleted(true);
		driverRepository.save(driver);
		if (driver.isDeleted()) {

			User user = userRepository.findByUserName(driver.getUserName())
					.orElseThrow(() -> new NoDriverException("Driver is not present"));
			userRepository.delete(user);
		}
		return driverDto;
	}

	@Override
	public List<ApplicantDto> getApplicants() {
		List<ApplicantDto> drivers = new ArrayList<>();
		driverRepository.findAllByIsActivated(false).forEach(driver->drivers.add(mapper.modelMapper().map(driver, ApplicantDto.class)));
		return drivers;
	}

	@Override
	public String sendMail(long id) throws DriverException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException("No driver found"));
		emailCfg.sendMail(driver.getEmail(),
				dear + driver.getFullName()
						+ "\n\nWe are happy to add you to our Ambulance Service\nYour login credentials\n Username : "
						+ driver.getUserName() + "\n Password : " + driver.getPassword() + regards,
				"Welcome To Saving lives");
		driver.setActivated(true);
		driverRepository.save(driver);
		if (driver.isActivated()) {
			User user = new User(driver.getUserName(), driver.getPassword(), "ROLE_DRIVER", driver.isActivated());
			userRepository.save(user);
		}
		return "Mail Sent";
	}

	@Override
	public String sendRejectMail(long id) throws DriverException {
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException("No driver found"));
		emailCfg.sendMail(driver.getEmail(), dear + driver.getFullName()
				+ "\n\nWe are so sorry to inform that your application is rejected.Hope you have a bright future "
				+ driver.getFullName() + regards, "Information Regarding Ambulance Driver Application");
		driverRepository.delete(driver);
		return "Mail Sent";
	}

	@Override
	public ResponseEntity<Object> recoverMail(String email) {
		List<Driver> accepted = new ArrayList<>();
		driverRepository.findAllByIsActivated(true).forEach(accepted::add);
		Map<String, Object> response = new HashMap<>();
		for (Driver driver : accepted) {
			if (driver.getEmail().equals(email)) {
				emailCfg.sendMail(driver.getEmail(),
						dear + driver.getFullName() + "\n\nAmbulance Service\nYour login credentials\n Username : "
								+ driver.getUserName() + "" + "\nPassword : " + driver.getPassword() + regards,
						"Welcome To Saving lives");
				response.put(success, true);
				response.put(message, "Credentials has been sent to your mail");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}
		response.put(success, false);
		response.put(message, "Email not registered please check your Email");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public Driver getDriverByName(String name) throws NoDriverException {
		return driverRepository.findByUserName(name)
				.orElseThrow(() -> new NoDriverException("Driver not available with this user name"));
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateDriverCoords(long driverId, Double latitude, Double longitude)
			throws NoDriverException {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new NoDriverException("Driver Not Found"));
		driver.setLatitude(latitude);
		driver.setLongitude(longitude);
		driverRepository.save(driver);
		Map<String, Object> response = new HashMap<>();
		response.put("Message", "Coordinates updated");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override

	public int getApplicantCount() {
		int count=driverRepository.getApplicantCount();
		return count;
	}
	public int getDriversCount() {
		return driverRepository.getDriversCount();
	}

	@Override
	public DriverDetailsDto getDriverDetailsById(long id) throws NoDriverException{
		Driver driver = driverRepository.findById(id).orElseThrow(() -> new NoDriverException(notFound));
		return mapper.modelMapper().map(driver, DriverDetailsDto.class);
	}

	
}
