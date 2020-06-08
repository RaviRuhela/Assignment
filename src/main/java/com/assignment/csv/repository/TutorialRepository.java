package com.assignment.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.csv.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
}
