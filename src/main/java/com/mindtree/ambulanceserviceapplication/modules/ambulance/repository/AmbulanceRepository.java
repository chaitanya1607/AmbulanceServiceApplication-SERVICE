package com.mindtree.ambulanceserviceapplication.modules.ambulance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mindtree.ambulanceserviceapplication.modules.ambulance.entity.Ambulance;

public interface AmbulanceRepository extends CrudRepository<Ambulance, Long> {

	Optional<Ambulance> findByVehicalNumber(String vehicalNumber);

	@Query(value = "SELECT c.ambulance_id,c.owner_address,c.vehical_number FROM jyoti_ambulanceservice_sep_20_dev.ambulance c", nativeQuery = true)
	List<Ambulance> getAmbulanceList();

	@Query(value = "SELECT c.* FROM jyoti_ambulanceservice_sep_20_dev.ambulance c where c.is_assigned=false order by c.ambulance_id limit 1", nativeQuery = true)
	Ambulance getUnassignedAmbulance();

	//@Query("select a.vehicalNumber,a.ownerNAme from Ambulance a where a.isDeleted=?1")
	List<Ambulance> findAllByIsDeleted(boolean isNotDeleted);
	
	@Query(value = "SELECT COUNT(a.ambulanceId) from Ambulance a where a.isDeleted=false")
	long findCountOfAmbulances();
}
