package com.mindtree.ambulanceserviceapplication.modules.ambulance.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Ambulance")
public class Ambulance {

	@Id
	@GeneratedValue
	private long ambulanceId;

	@Schema(description = "Ambulance vehical number", required = true)
	@Column(name = "vehicalNumber")
	private String vehicalNumber;

	@Schema(description = "Ambulance vehicle registration date", required = true)
	@Column(name = "vehicalRegistrationDate")
	private Date vehicalRegistrationDate;

	@Schema(description = "Ambulance vehicle insurance file", required = true)
	@Column(name = "vehicalInsurance", length = 16777215)
	private byte[] vehicalInsurance;

	@Schema(description = "Type of the vehicle insurance file", required = true)
	@Column(name = "vehicalInsuranceFileType")
	private String vehicalInsuranceFileType;

	@Schema(description = " RC book file", required = true)
	@Column(name = "rCBook", length = 16777215)
	private byte[] rCBook;

	@Schema(description = "RC book file type", required = true)
	@Column(name = "rCBookFileType")
	private String rCBookFileType;

	@Schema(description = "List of drivers assigned with the ambulance", required = false)
	@OneToMany(fetch = FetchType.LAZY)
	private List<Driver> drivers;

	@Schema(description = "List of Equipments in ambuance", required = true)
	@ElementCollection
	private List<String> equipments;

	@Schema(description = "vehicle insurance validity date", required = true)
	@Column(name = "insuranceValidity")
	private Date insuranceValidity;

	@Schema(description = "vehicle owner aadhar number", required = true)
	@Column(name = "ownerAadharNumber")
	private String ownerAadharNumber;

	@Schema(description = "vehicle owner aadhar file", required = true)
	@Column(name = "ownerAadhar", length = 16777215)
	private byte[] ownerAadhar;

	@Schema(description = "vehical owner aadhar file type", required = true)
	@Column(name = "ownerAadharFileType")
	private String ownerAadharFileType;

	@Schema(description = "vehicle owner address", required = true)
	@Column(name = "ownerAddress")
	private String ownerAddress;

	@Schema(description = "vehicle owner name", required = true)
	@Column(name = "ownerNAme")
	private String ownerNAme;

	@Column(name = "isAssigned")
	private boolean isAssigned;

	private boolean isDeleted;

	public Ambulance() {
		super();
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Ambulance(String vehicalNumber, String ownerNAme) {
		super();
		this.vehicalNumber = vehicalNumber;
		this.ownerNAme = ownerNAme;
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

	public String getVehicalInsuranceFileType() {
		return vehicalInsuranceFileType;
	}

	public void setVehicalInsuranceFileType(String vehicalInsuranceFileType) {
		this.vehicalInsuranceFileType = vehicalInsuranceFileType;
	}

	public byte[] getrCBook() {
		return rCBook;
	}

	public void setrCBook(byte[] rCBook) {
		this.rCBook = rCBook;
	}

	public String getrCBookFileType() {
		return rCBookFileType;
	}

	public void setrCBookFileType(String rCBookFileType) {
		this.rCBookFileType = rCBookFileType;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
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

	public String getOwnerAadharFileType() {
		return ownerAadharFileType;
	}

	public void setOwnerAadharFileType(String ownerAadharFileType) {
		this.ownerAadharFileType = ownerAadharFileType;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerNAme() {
		return ownerNAme;
	}

	public void setOwnerNAme(String ownerNAme) {
		this.ownerNAme = ownerNAme;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	@Override
	public String toString() {
		return "Ambulance [ambulanceId=" + ambulanceId + ", vehicalNumber=" + vehicalNumber
				+ ", vehicalRegistrationDate=" + vehicalRegistrationDate + ", vehicalInsurance="
				+ Arrays.toString(vehicalInsurance) + ", vehicalInsuranceFileType=" + vehicalInsuranceFileType
				+ ", rCBook=" + Arrays.toString(rCBook) + ", rCBookFileType=" + rCBookFileType + ", drivers=" + drivers
				+ ", equipments=" + equipments + ", insuranceValidity=" + insuranceValidity + ", ownerAadharNumber="
				+ ownerAadharNumber + ", ownerAadhar=" + Arrays.toString(ownerAadhar) + ", ownerAadharFileType="
				+ ownerAadharFileType + ", ownerAddress=" + ownerAddress + ", ownerNAme=" + ownerNAme + ", isAssigned="
				+ isAssigned + ", getAmbulanceId()=" + getAmbulanceId() + ", getVehicalNumber()=" + getVehicalNumber()
				+ ", getVehicalRegistrationDate()=" + getVehicalRegistrationDate() + ", getVehicalInsurance()="
				+ Arrays.toString(getVehicalInsurance()) + ", getVehicalInsuranceFileType()="
				+ getVehicalInsuranceFileType() + ", getrCBook()=" + Arrays.toString(getrCBook())
				+ ", getrCBookFileType()=" + getrCBookFileType() + ", getDrivers()=" + getDrivers()
				+ ", getEquipments()=" + getEquipments() + ", getInsuranceValidity()=" + getInsuranceValidity()
				+ ", getOwnerAadharNumber()=" + getOwnerAadharNumber() + ", getOwnerAadhar()="
				+ Arrays.toString(getOwnerAadhar()) + ", getOwnerAadharFileType()=" + getOwnerAadharFileType()
				+ ", getOwnerAddress()=" + getOwnerAddress() + ", getOwnerNAme()=" + getOwnerNAme() + ", isAssigned()="
				+ isAssigned() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
