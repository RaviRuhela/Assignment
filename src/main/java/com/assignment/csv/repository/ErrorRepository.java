package com.assignment.csv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.csv.model.Upload_Staging;

public interface ErrorRepository extends JpaRepository<Upload_Staging, String> {
	
	@Query(value = "SELECT * FROM upload_staging WHERE txn_id = ?1", nativeQuery = true)
	List<Upload_Staging> findByStaging(String txnId);
}
