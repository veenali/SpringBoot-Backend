package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PatientRepo;
import com.example.demo.model.Patient;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {
	@Autowired
	PatientRepo repo;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/patient")
	public List<Patient> getAllPatients() {
		List<Patient> patients = repo.findAll();
		System.out.println(patients);
		return patients;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/patient/{name}")
	public List<Patient> getPatientByName(@PathVariable String name) {
		List<Patient> patients = repo.findByName(name);
		if (!patients.isEmpty()) {
			return patients;
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/patient", consumes = "application/json")
	public String addPatient(@RequestBody Patient patient) {
		repo.save(patient);
		return "Patient has been successfully added";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/patient/{id}")
	public String updatePatient(@RequestBody Patient patient, @PathVariable int id) {
		Patient newPatient = repo.findById(id).orElse(null);
		if (newPatient != null) {
			newPatient.setName(patient.getName());
			newPatient.setVisitedDoctor(patient.getVisitedDoctor());
			newPatient.setDateOfVisit(patient.getDateOfVisit());
			newPatient.setAge(patient.getAge());
			newPatient.setGender(patient.getGender());
			System.out.println("New patient saved version=" + newPatient);
			repo.save(newPatient);
			return "Patient record has been updated in the record";
		}
		// repo.save(patient);
		return "Not able to update the record";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/patient/{id}")
	public String deletePatient(@PathVariable int id) {
		repo.deleteById(id);
		return "Patient deleted successfully";
	}
}
