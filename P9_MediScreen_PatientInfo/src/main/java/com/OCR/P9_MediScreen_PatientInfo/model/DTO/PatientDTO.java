package com.OCR.P9_MediScreen_PatientInfo.model.DTO;

import com.OCR.P9_MediScreen_PatientInfo.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PatientDTO {
    private Integer id;
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;
    @NotNull(message = "Date of birth is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    private String address;
    private String phoneNumber;

    public PatientDTO() {
    }

    public PatientDTO(final Patient patient) {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.phoneNumber = patient.getPhoneNumber();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
