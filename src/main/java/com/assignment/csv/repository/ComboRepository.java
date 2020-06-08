package com.assignment.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.csv.model.UserRoleLink;

public interface ComboRepository extends JpaRepository<UserRoleLink, Long> {
}
