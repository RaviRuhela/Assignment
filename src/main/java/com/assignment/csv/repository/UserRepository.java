package com.assignment.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.csv.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM USER WHERE EMAIL = ?1", nativeQuery = true)
	  User findByEmailAddress(String emailAddress);
}
