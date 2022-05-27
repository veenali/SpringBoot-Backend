package com.example.demo;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.PatientController;
import com.example.demo.dao.PatientRepo;
import com.example.demo.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	PatientRepo patientRepo;

	Patient p1 = new Patient(1, "Rayven", 32, "F", "Rihan Doe", "23-09-2019");
	Patient p2 = new Patient(2, "David", 20, "M", "Samir", "3-05-2022");

	@Test
	public void getAllRecords_success() throws Exception {
		List<Patient> patients = new ArrayList<>(Arrays.asList(p1, p2));

		Mockito.when(patientRepo.findAll()).thenReturn(patients);
		mockMvc.perform(MockMvcRequestBuilders.get("/patient").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].name", is("David")));
	}
	
	@Test
	public void getPatientByName_success() throws Exception {		
		Mockito.when(patientRepo.findById(p1.getpId())).thenReturn(java.util.Optional.of(p1));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/patient/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())		
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Rayven")));
	}
	
	@Test
	public void createRecord_success() throws Exception {
	    Patient p = new Patient(1, "Ramakant", 32, "M", "Rihan Doe", "07-09-2022");	    

	    Mockito.when(patientRepo.save(p)).thenReturn(p);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(p));
	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()))
	            .andExpect(jsonPath("$.name", is("Ramakant")));
	    }
}
