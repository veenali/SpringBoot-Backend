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

import com.example.demo.dao.DoctorRepo;
import com.example.demo.model.Doctor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DoctorController {
	@Autowired
	DoctorRepo repo;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/doctor", consumes = { "application/json" })
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = repo.findAll();
		System.out.println(doctors);
		return doctors;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/doctor", consumes = { "application/json" })
	public String addDoctor(@RequestBody Doctor doctor) {
		repo.save(doctor);
		return "Doctor has been successfully added";
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(path = "/doctor/{id}", consumes = { "application/json" })
	public String updateDoctor(@RequestBody Doctor doctor, @PathVariable int id) {
		Doctor newDoc = repo.findById(id).orElse(null);
		if (newDoc != null) {
			newDoc.setName(doctor.getName());
			newDoc.setAge(doctor.getAge());
			newDoc.setGender(doctor.getGender());
			newDoc.setSpecializedField(doctor.getSpecializedField());
			newDoc.setNoOfPatientsAttended(doctor.getNoOfPatientsAttended());
			System.out.println("New doctor saved version=" + newDoc);
			repo.save(newDoc);
			return "Doctor record has been updated in the record";
		}
		// repo.save(doctor);
		return "Not able to update the record";
	}

	@DeleteMapping("/doctor/{id}")
	public String deleteDoctor(@PathVariable int id) {
		repo.deleteById(id);
		return "Doctor successfully deleted";
	}
}
