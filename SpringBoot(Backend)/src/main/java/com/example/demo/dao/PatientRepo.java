package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

	List<Patient> findByName(String name);

	

}
