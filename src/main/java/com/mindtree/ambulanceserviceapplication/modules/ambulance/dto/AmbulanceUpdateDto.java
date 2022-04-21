package com.mindtree.ambulanceserviceapplication.modules.ambulance.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AmbulanceUpdateDto {

	private long ambulanceId;
	@NotNull(message = "Vehicle number required")
	@NotEmpty(message = "Vehicle number required")
	@Pattern(regexp = "[A-Z]{2}[ -][0-9]{1,2}[ -][A-Z]{2}[ -][0-9]{4}/*", message = "Enter a valid vehicle number(eg:DL 01 AA 1111)")
	private String vehicalNumber;
	@NotNull(message = "vehicle registration date cannot be null")
	@Past(message = "vehicle registration date cannot be today or future date")
	private Date vehicalRegistrationDate;
	private byte[] vehicalInsurance;
	private byte[] rCBook;
	private List<String> equipments;
	@NotNull(message = "vehicle insurance validity date is required")
	@Future(message = "vehicle registration date cannot be today of past date")
	private Date insuranceValidity;
	@NotNull(message = "Owner aadhar number is required")
	@Pattern(regexp = "[2-9]{1}[0-9]{3}( )([0-9]{4})( )([0-9]{4})$", message = "Enter a valid AadharNumber(eg:XXXX XXXX XXXX) and it cannot start with 0 or 1")
	private String ownerAadharNumber;
	private byte[] ownerAadhar;
	@NotNull(message = "owner address required")
	@Size(min = 8, max = 100, message = "Owner address must be of length 8-100 characters")
	private String ownerAddress;
	@NotNull(message = "Owner name is required")
	@Size(min = 8, max = 16, message = "Owner name must be of length 8-16 characters")
	@Pattern(regexp = "[a-zA-Z\\s]*", message = "Owner name can have only alphabets and spaces")
	private String ownerName;
	
	
	public AmbulanceUpdateDto() {
		super();
	}
	public long getAmbulanceId() {
		return ambulanceId;
	}
	public void setAmbulanceId(long ambulanceId) {
		this.ambulanceId = ambulanceId;
	}
	public String getVehicalNumber() {
		return vehicalNumber;
	}
	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
	}
	public Date getVehicalRegistrationDate() {
		return vehicalRegistrationDate;
	}
	public void setVehicalRegistrationDate(Date vehicalRegistrationDate) {
		this.vehicalRegistrationDate = vehicalRegistrationDate;
	}
	public byte[] getVehicalInsurance() {
		return vehicalInsurance;
	}
	public void setVehicalInsurance(byte[] vehicalInsurance) {
		this.vehicalInsurance = vehicalInsurance;
	}
	public byte[] getrCBook() {
		return rCBook;
	}
	public void setrCBook(byte[] rCBook) {
		this.rCBook = rCBook;
	}
	public List<String> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<String> equipments) {
		this.equipments = equipments;
	}
	public Date getInsuranceValidity() {
		return insuranceValidity;
	}
	public void setInsuranceValidity(Date insuranceValidity) {
		this.insuranceValidity = insuranceValidity;
	}
	public String getOwnerAadharNumber() {
		return ownerAadharNumber;
	}
	public void setOwnerAadharNumber(String ownerAadharNumber) {
		this.ownerAadharNumber = ownerAadharNumber;
	}
	public byte[] getOwnerAadhar() {
		return ownerAadhar;
	}
	public void setOwnerAadhar(byte[] ownerAadhar) {
		this.ownerAadhar = ownerAadhar;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	
	
	
	
	

}
