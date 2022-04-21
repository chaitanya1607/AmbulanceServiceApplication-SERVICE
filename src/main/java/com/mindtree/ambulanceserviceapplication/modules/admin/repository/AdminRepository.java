package com.mindtree.ambulanceserviceapplication.modules.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ambulanceserviceapplication.modules.admin.entity.Admin;

/**
 * @author priya
 *
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
