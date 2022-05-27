package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;

@Entity
@Builder
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dId;

	public Doctor() {
		super();
	}

	public Doctor(int dId, int age, String name, char gender, String specializedField, int noOfPatientsAttended) {
		super();
		this.dId = dId;
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.specializedField = specializedField;
		this.noOfPatientsAttended = noOfPatientsAttended;
	}

	private int age;
	private String name;
	private char gender;
	private String specializedField;
	private int noOfPatientsAttended;

	public int getNoOfPatientsAttended() {
		return noOfPatientsAttended;
	}

	public void setNoOfPatientsAttended(int noOfPatientsAttended) {
		this.noOfPatientsAttended = noOfPatientsAttended;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getSpecializedField() {
		return specializedField;
	}

	public void setSpecializedField(String specializedField) {
		this.specializedField = specializedField;
	}

	@Override
	public String toString() {
		return "Doctor [dId=" + dId + ", age=" + age + ", name=" + name + ", gender=" + gender + ", specializedField="
				+ specializedField + "]";
	}
}
