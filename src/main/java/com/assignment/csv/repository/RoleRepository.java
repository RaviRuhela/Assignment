package com.assignment.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.csv.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
