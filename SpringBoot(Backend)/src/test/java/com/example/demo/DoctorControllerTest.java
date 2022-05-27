package com.example.demo;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.example.demo.controller.DoctorController;
import com.example.demo.dao.DoctorRepo;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	DoctorRepo doctorRepo;

	Doctor doc1 = new Doctor(1, 32, "Rayven Yor", 'F', "General Physician", 30000);
	Doctor doc2 = new Doctor(2, 20, "David Doe", 'M', "Dermetologist", 400);

	@Test
	public void getAllRecords_success() throws Exception {
		List<Doctor> doctors = new ArrayList<>(Arrays.asList(doc1, doc2));

		Mockito.when(doctorRepo.findAll()).thenReturn(doctors);

		mockMvc.perform(MockMvcRequestBuilders.get("/doctor").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].name", is("David Doe")));
	}
	
	@Test
	public void createRecord_success() throws Exception {
	    Doctor d = new Doctor(1, 52, "Ramesh Thakur", 'M', "General Physician", 90000);
	    
	    Mockito.when(doctorRepo.save(d)).thenReturn(d);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/doctor")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(d));
	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()))
	            .andExpect(jsonPath("$.name", is("Ramesh Thakur")));
	    }

}
