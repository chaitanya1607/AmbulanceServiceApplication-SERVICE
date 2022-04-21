package com.mindtree.ambulanceserviceapplication.modules.driver.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mindtree.ambulanceserviceapplication.modules.driver.entity.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {

	Optional<Driver> findByUserName(String userName);

	Driver findByEmail(String email);

	List<Driver> findAllByIsDeleted(boolean isNotDeleted);

	Driver findByAadharNumber(String aadharNumber);

	Driver findByDrivingLicenceNumber(String drivingLicenceNumber);

	List<Driver> findAllByIsActivated(boolean isActivated);

	@Query(value = "SELECT * FROM jyoti_ambulanceservice_sep_20_dev.driver_table where is_activated = 1 and deleted = 0", nativeQuery = true)
	List<Driver> findAvailableDriver();

	
	@Query("select count(*) from Driver as d where d.isActivated=false")
    int getApplicantCount();

	@Query(value= "SELECT COUNT(driver_id) FROM jyoti_ambulanceservice_sep_20_dev.driver_table where is_activated = 1 and deleted=0", nativeQuery = true)
	int getDriversCount();
	
	
}
