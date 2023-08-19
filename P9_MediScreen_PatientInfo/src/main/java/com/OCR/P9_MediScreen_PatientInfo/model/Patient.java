package com.OCR.P9_MediScreen_PatientInfo.model;

import com.OCR.P9_MediScreen_PatientInfo.model.DTO.PatientDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;

    public Patient() {
    }
    public Patient(int id, String firstName, String lastName,
                   LocalDate dateOfBirth, String gender,
                   String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Patient(PatientDTO patientDto) {
        this.firstName = patientDto.getFirstName();
        this.lastName = patientDto.getLastName();
        this.dateOfBirth = patientDto.getDateOfBirth();
        this.gender = patientDto.getGender();
        this.address = patientDto.getAddress();
        this.phoneNumber = patientDto.getPhoneNumber();
    }

    public int getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
