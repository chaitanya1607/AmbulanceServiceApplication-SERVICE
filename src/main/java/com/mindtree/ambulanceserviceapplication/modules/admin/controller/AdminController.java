package com.mindtree.ambulanceserviceapplication.modules.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.ambulanceserviceapplication.configuration.mapper.Mapper;
import com.mindtree.ambulanceserviceapplication.exception.AmbulanceApplicationException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceListDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.dto.AmbulanceUpdateDto;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.AmbulanceNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.exception.DuplicateEntryException;
import com.mindtree.ambulanceserviceapplication.modules.ambulance.service.AmbulanceService;
import com.mindtree.ambulanceserviceapplication.modules.customer.dto.CustomerDto;
import com.mindtree.ambulanceserviceapplication.modules.customer.exception.CustomerException;
import com.mindtree.ambulanceserviceapplication.modules.customer.service.CustomerService;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.ApplicantDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverBookingDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverListDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.dto.DriverDetailsDto;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.DriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.exception.NoDriverException;
import com.mindtree.ambulanceserviceapplication.modules.driver.service.DriverService;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.dto.HospitalListDto;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalNotFoundException;
import com.mindtree.ambulanceserviceapplication.modules.hospital.exception.HospitalServiceException;
import com.mindtree.ambulanceserviceapplication.modules.hospital.service.HospitalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://jyoti-ambulanceservice-sep-2020-client-dev.azurewebsites.net")
@RequestMapping("")
public class AdminController {

	@Autowired
	private AmbulanceService ambulanceService;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Mapper mapper;

	@Operation(summary = "This method validates the admin credentials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "returns successfull string", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)

	})
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/secure/admin")
	public String securedHello() {
		return "admin";

	}

	@Operation(summary = "This method fetches all the ambulance details from the DataBase", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched all the ambulances from DataBase", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data Not Found", content = @Content)

	})
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ambulances")
	public ResponseEntity<List<AmbulanceListDto>> getAllAmbulanceList() throws AmbulanceApplicationException {
		List<AmbulanceListDto> ambulances = new ArrayList<>();
		try {
			ambulances = ambulanceService.getAllAmbulanceList();
		} catch (AmbulanceNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(ambulances, HttpStatus.OK);
	}

	@Operation(summary = "This method fetches the ambulance details from the DataBase", description = "This method takes accepting vehical number as a parameter", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched the ambulances from DataBase", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data Not Found", content = @Content)

	})
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ambulances/{vehicalNumber}")
	public ResponseEntity<Object> getAmbulanceByVehicalNumber(
			@PathVariable(value = "vehicalNumber") String vehicalNumber) throws AmbulanceApplicationException {
		AmbulanceUpdateDto response = null;
		try {
			response = ambulanceService.getAmbulanceByVehicalNumber(vehicalNumber);
		} catch (AmbulanceNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "This method creates an ambulance ", description = "Takes the json type of ambulance data with ownerAadhar,vehicle insurance and RC book as multipart files", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/ambulance", consumes = "multipart/form-data")
	public ResponseEntity<String> createAmbulance(@RequestParam("ownerAadhar") MultipartFile ownerAadhar,
			@RequestParam("vehicalInsurance") MultipartFile vehicalInsurance,
			@RequestParam("rCBook") MultipartFile rCBook, @RequestParam("ambulance") String ambulance)
			throws IOException, AmbulanceApplicationException {

		AmbulanceDto ambulanceDto = new ObjectMapper().readValue(ambulance, AmbulanceDto.class);
		ambulanceDto.setOwnerAadhar(ownerAadhar.getBytes());
		ambulanceDto.setOwnerAadharFileType(ownerAadhar.getContentType());
		ambulanceDto.setVehicalInsurance(vehicalInsurance.getBytes());
		ambulanceDto.setVehicalInsuranceFileType(vehicalInsurance.getContentType());
		ambulanceDto.setrCBook(rCBook.getBytes());
		ambulanceDto.setrCBookFileType(rCBook.getContentType());
		String response = null;
		try {
			response = ambulanceService.createAmbulance(ambulanceDto);
		} catch (DuplicateEntryException | IOException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "This method updates an ambulance ", description = "Takes the json type of ambulance data with ownerAadhar,vehicle insurance and RC book as multipart files(optional) and vehical number as a parameters")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/ambulances/{vehicalNumber}", consumes = "multipart/form-data")
	public ResponseEntity<String> updateAmbulance(@PathVariable(value = "vehicalNumber") String vehicalNumber,
			@RequestParam(required = false) MultipartFile ownerAadhar,
			@RequestParam(required = false) MultipartFile vehicalInsurance,
			@RequestParam(required = false) MultipartFile rCBook, @RequestParam("ambulance") String ambulance)
			throws AmbulanceApplicationException {
		String response = null;
		try {
			AmbulanceDto ambulanceDto = new ObjectMapper().readValue(ambulance, AmbulanceDto.class);
			if (ownerAadhar != null) {

				ambulanceDto.setOwnerAadhar(ownerAadhar.getBytes());
				ambulanceDto.setOwnerAadharFileType(ownerAadhar.getContentType());
			}
			if (vehicalInsurance != null) {
				ambulanceDto.setVehicalInsurance(vehicalInsurance.getBytes());
				ambulanceDto.setVehicalInsuranceFileType(vehicalInsurance.getContentType());
			}
			if (rCBook != null) {
				ambulanceDto.setrCBook(rCBook.getBytes());
				ambulanceDto.setrCBookFileType(rCBook.getContentType());
			}

			response = ambulanceService.updateAmbulance(vehicalNumber, ambulanceDto);
		} catch (AmbulanceNotFoundException | IOException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "This method delets an ambulance ", description = "Takes vehical number as a parameter to delete the ambulance from the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/ambulances/{vehicalNumber}")
	public ResponseEntity<Object> deleteAmbulance(@PathVariable(value = "vehicalNumber") String vehicalNumber)
			throws AmbulanceApplicationException {
		String response = null;
		try {
			response = ambulanceService.deleteAmbulance(vehicalNumber);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (AmbulanceNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}

	}

	@Operation(summary = "This method sends recovery password username ", description = "Takes vehical number as a parameter to delete the ambulance from the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@GetMapping("/recovery-mail/{email}")
	public ResponseEntity<Object> recoverMail(@PathVariable(name = "email") String email) {
		return driverService.recoverMail(email);
	}

	@Operation(summary = "This method gets the list of hospitals ", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/hospitalsList")
	public List<HospitalListDto> getHospitalsList() throws AmbulanceApplicationException {
		try {
			return hospitalService.getHospitalsList();
		} catch (HospitalNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}

	@Operation(summary = "This method gets the hospital details ", description = "Takes Hospital id as a parameter to fetch the hospital from the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("getHospital/{id}")
	public ResponseEntity<HospitalDto> getHospitalById(@PathVariable("id") long id)
			throws AmbulanceApplicationException {
		HospitalDto hospitalDto = null;
		try {
			hospitalDto = hospitalService.getHospitalById(id);
		} catch (HospitalNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(hospitalDto, HttpStatus.OK);
	}

	@Operation(summary = "This method creates the hospital details", description = "Takes Hospital details as a json and a multipart file for hospital registration certificate as parameters", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/addHospital", consumes = "multipart/form-data")

	public ResponseEntity<String> addHospital(@RequestParam("hospital") String hospital,
			@RequestParam("registrationFile") MultipartFile hospitalRegistrationFile)
			throws IOException, AmbulanceApplicationException {

		HospitalDto hospitalDto = new ObjectMapper().readValue(hospital, HospitalDto.class);
		hospitalDto.setRegistrationFile(hospitalRegistrationFile.getBytes());
		hospitalDto.setRegistrationFileType(hospitalRegistrationFile.getContentType());
		String message = null;
		try {
			message = hospitalService.addHospital(hospitalDto);
		} catch (HospitalServiceException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@Operation(summary = "This method updates the hospital details", description = "Takes Hospital id, Hospital details as a json and a multipart file(optional) for hospital registration certificate as parameters", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "updateHospital/{id}", consumes = "multipart/form-data")
	public ResponseEntity<String> updateHospital(@PathVariable("id") long id, @RequestParam("hospital") String hospital,
			@RequestParam(required = false) MultipartFile hospitalRegistrationFile)
			throws AmbulanceApplicationException {
		HospitalDto hospitalDto;
		String message = null;
		try {
			hospitalDto = new ObjectMapper().readValue(hospital, HospitalDto.class);
			if (hospitalRegistrationFile != null) {
				hospitalDto.setRegistrationFile(hospitalRegistrationFile.getBytes());
				hospitalDto.setRegistrationFileType(hospitalRegistrationFile.getContentType());
			}
			message = hospitalService.updateHospital(id, hospitalDto);
		} catch (IOException | HospitalServiceException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@Operation(summary = "This method delets the hospital details", description = "Takes Hospital id as parameter to delete the hospital to delete hospital from database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("deleteHospital/{id}")
	public ResponseEntity<String> removeHospital(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		String message = null;
		try {
			message = hospitalService.removeHospital(id);
		} catch (HospitalNotFoundException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@Operation(summary = "This method gets all the driver details", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/drivers")
	public ResponseEntity<Object> getAllDrivers() throws AmbulanceApplicationException {
		List<DriverListDto> drivers = new ArrayList<>();
		try {
			drivers = driverService.getAllDrivers();
		} catch (NoDriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(drivers, HttpStatus.OK);
	}

	@Operation(summary = "This method gets all the driver count from database")

	@ApiResponse(responseCode = "200", description = " Operation successful", content = {
			@Content(mediaType = "application/json") })

	@GetMapping(path = "/driver-count")
	public ResponseEntity<Integer> getDriversCount() {
		return new ResponseEntity<Integer>(driverService.getDriversCount(), HttpStatus.OK);
	}

	@Operation(summary = "This method Creates the driver", description = "Takes driver details as json,photo,aadhar,licence as multipart files as a paramters to create the driver details in the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PostMapping(path = "/add-driver", consumes = "multipart/form-data")
	public ResponseEntity<Object> registerDriver(@RequestParam(value = "aadhar", required = true) MultipartFile aadhar,
			@RequestParam(value = "photo", required = true) MultipartFile photo,
			@RequestParam(value = "license", required = true) MultipartFile license,
			@RequestParam("driver") String driver) throws AmbulanceApplicationException {
		try {
			DriverDto driverDto = new ObjectMapper().readValue(driver, DriverDto.class);
			driverDto.setAadhar(aadhar.getBytes());
			driverDto.setAadharFileType(aadhar.getContentType());
			driverDto.setLicense(license.getBytes());
			driverDto.setLicenseFileType(license.getContentType());
			driverDto.setPhoto(photo.getBytes());
			driverDto.setPhotoFileType(photo.getContentType());
			return driverService.addDriver(driverDto);
		} catch (IOException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}

	}

	@Operation(summary = "This method gets the driver details", description = "Takes driver id as a paramter to fetch the driver details from the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
	@GetMapping("get-driver/{id}")
	public ResponseEntity<Object> getDriverById(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		try {
			DriverDto driver = driverService.getDriverById(id);
			return new ResponseEntity<>(driver, HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}
	
	@Operation(summary = "This method gets the driver details for display purpose", description = "Takes driver id as a paramter to fetch the driver details from the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasAnyRole('ADMIN','DRIVER')")
	@GetMapping("get-driver-details/{id}")
	public ResponseEntity<Object> getDriverDetailsById(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		try {
			DriverDetailsDto driver = driverService.getDriverDetailsById(id);
			return new ResponseEntity<>(driver, HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}


	@Operation(summary = "This method updates the driver", description = "Takes driver details as json,photo,aadhar,licence as multipart files(optional) as a paramters to update the driver details in the database", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "update-driver/{driverId}", consumes = "multipart/form-data")
	public ResponseEntity<Object> updateDriver(@PathVariable(value = "driverId") long id,
			@RequestParam(required = false) MultipartFile aadhar, @RequestParam(required = false) MultipartFile license,
			@RequestParam(required = false) MultipartFile photo, @Valid @RequestParam("driver") String driver)
			throws AmbulanceApplicationException {
		try {
			DriverDto driverDto = new ObjectMapper().readValue(driver, DriverDto.class);
			if (aadhar != null) {
				driverDto.setAadhar(aadhar.getBytes());
				driverDto.setAadharFileType(aadhar.getContentType());
			}
			if (license != null) {
				driverDto.setLicense(license.getBytes());
				driverDto.setLicenseFileType(license.getContentType());
			}
			if (photo != null) {
				driverDto.setPhoto(photo.getBytes());
				driverDto.setPhotoFileType(photo.getContentType());
			}
			DriverDto driverReturn = driverService.updateDriver(id, driverDto);
			return new ResponseEntity<>(driverReturn, HttpStatus.OK);
		} catch (IOException | DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}


	@Operation(summary = "This method delets the driver", description = "Takes driver id as parameter to delete the driver details from the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete-driver/{id}")
	public ResponseEntity<Object> deleteDriver(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		try {
			DriverDto driverDto = driverService.deleteDriver(id);
			return new ResponseEntity<>(driverDto, HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}

	@Operation(summary = "This method gets all the driver applicants", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/applicants-list")
	public ResponseEntity<Object> getApplicantList() {
		List<ApplicantDto> applicants = driverService.getApplicants();
		return new ResponseEntity<>(applicants, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/bookings/{vehicalNumber}")
	public ResponseEntity<DriverBookingDto> getBookings(@PathVariable(value = "vehicalNumber") String vehicalNumber) {
		DriverBookingDto booking = ambulanceService.getBookings(vehicalNumber);
		return new ResponseEntity<DriverBookingDto>(booking, HttpStatus.OK);
	}

	@GetMapping("/applicants")
	public ResponseEntity<Integer> getApplicantCount() {
		int count = driverService.getApplicantCount();
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@Operation(summary = "This method sends the Accept driver mail to the driver", description = "Takes driver id as parameter to send the mail to the driver applicant")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successfull", content = {

			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@GetMapping("/accept/{id}")
	public ResponseEntity<Object> sendAcceptMail(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		try {
			String email = driverService.sendMail(id);
			return new ResponseEntity<>(email, HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}

	@Operation(summary = "This method sends the Reject driver mail to the driver", description = "Takes driver id as parameter to send the mail to the driver applicant")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Operation successfull", content = {

			@Content(mediaType = "application/json") }),

			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@GetMapping("/reject/{id}")
	public ResponseEntity<Object> sendMailReject(@PathVariable(value = "id") long id)
			throws AmbulanceApplicationException {
		try {
			String email = driverService.sendRejectMail(id);
			return new ResponseEntity<>(email, HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
	}

	@Operation(summary = "This method fetches all the customer details from the DataBase", security = {
			@SecurityRequirement(name = "basic-auth") })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetched all the customers from DataBase", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "404", description = "Data Not Found", content = @Content) })
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomerList() throws AmbulanceApplicationException {
		List<CustomerDto> customerDtos;
		try {
			customerDtos = customerService.getAllCustomerList();
		} catch (CustomerException e) {
			throw new AmbulanceApplicationException(e.getLocalizedMessage());
		}
		return new ResponseEntity<>(customerDtos, HttpStatus.OK);

	}

	@Operation(summary = "This method fetches the unassigned ambulance from the database and allots it to the driver")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })

	@GetMapping("/allotAmbulance/{driverId}")
	public ResponseEntity<Map<String, Object>> allotAmbulanceToDriver(@PathVariable(value = "driverId") long driverId)
			throws AmbulanceApplicationException {
		String header = "header";
		String error = "Error";
		String message = "message";
		String httpStatus = "HttpStatus";
		String body = "Body";
		String unassigned = "Fetching Unassigned Ambulance";
		Map<String, Object> responses = new HashMap<>();
		DriverDto driverdto = driverService.getDriverById(driverId);
		driverdto.setLogedIn(true);
		if (!driverdto.isAssigned()) {
			AmbulanceDto ambulancedto = ambulanceService.getUnassignedAmbulance();
			if (ambulancedto == null) {
				responses.put(header, unassigned);
				responses.put(error, true);
				responses.put(message, "Unassigned Ambulance Not found.\nPlease Wait!!");
				responses.put(httpStatus, HttpStatus.BAD_REQUEST);
			} else {
				ambulancedto.setAssigned(true);
				driverdto.setAmbulance(mapper.modelMapper().map(ambulancedto, Ambulance.class));
				driverdto.setAssigned(true);
				driverService.updateDriver(driverId, driverdto);
				responses.put(header, unassigned);
				responses.put(error, false);
				responses.put(message, "Ambulance fetched successfully");
				responses.put(body, ambulancedto);
				responses.put(httpStatus, HttpStatus.OK);
			}

		} else {

			responses.put(header, unassigned);
			responses.put(error, false);
			responses.put(message, "Ambulance fetched successfully");
			responses.put(body, mapper.modelMapper().map(driverdto.getAmbulance(), AmbulanceDto.class));
			responses.put(httpStatus, HttpStatus.OK);

		}

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@Operation(summary = "This method submits the assigned ambulance to the driver")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@GetMapping("/submitAmbulance/{driverId}")

	public ResponseEntity<Map<String, Object>> submitAmbulanceByDriver(@PathVariable(value = "driverId") long driverId)
			throws AmbulanceApplicationException {
		DriverDto driverdto = null;
		Map<String, Object> responses = new HashMap<>();
		try {
			driverdto = driverService.getDriverById(driverId);
			driverdto.setLogedIn(false);
			driverdto.setAssigned(false);

			Ambulance ambulance = driverdto.getAmbulance();
			ambulance.setAssigned(false);
			driverdto.setAmbulance(ambulance);

			driverService.updateDriver(driverId, driverdto);

			responses.put("header", "Driver Logout and submit Ambulance");
			responses.put("Error", false);
			responses.put("message", "Driver logged out successfully");
			responses.put("HttpStatus", HttpStatus.OK);
		} catch (DriverException e) {
			throw new AmbulanceApplicationException(e.getMessage());
		}

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@Operation(summary = "This method submits the number of hospital present in the databaase")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation successfull", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content), })
	@GetMapping("/hospital-count")
	public ResponseEntity<Integer> hospitalCount() {
		int hospitalCount = hospitalService.getHospitalCount();
		return new ResponseEntity<>(hospitalCount, HttpStatus.OK);
	}

	@GetMapping("/ambulance-count")
	public ResponseEntity<Long> getAmbulanceCount() {
		long ambulanceCount = 0;
		ambulanceCount = ambulanceService.getAmbulanceCount();
		return new ResponseEntity<>(ambulanceCount, HttpStatus.OK);
	}

}
