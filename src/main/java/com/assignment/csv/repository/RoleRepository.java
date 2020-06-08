package com.assignment.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.csv.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
	@Query(value = "SELECT * FROM roles WHERE role_name = ?1", nativeQuery = true)
	Roles findByRollName(String roleName);
}
