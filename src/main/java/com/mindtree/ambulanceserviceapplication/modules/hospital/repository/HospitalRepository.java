package com.mindtree.ambulanceserviceapplication.modules.hospital.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.ambulanceserviceapplication.modules.hospital.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	@Query(value = "SELECT count(*) FROM jyoti_ambulanceservice_sep_20_dev.hospitals",nativeQuery = true)
	int hospitalCount();
	
    
}
